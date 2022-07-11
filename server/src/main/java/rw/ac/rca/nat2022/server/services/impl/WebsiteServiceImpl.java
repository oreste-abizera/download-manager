package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.Link;
import rw.ac.rca.nat2022.server.models.Website;
import rw.ac.rca.nat2022.server.repositories.ILinkRepository;
import rw.ac.rca.nat2022.server.repositories.IWebsiteRepository;
import rw.ac.rca.nat2022.server.services.IWebsiteService;
import rw.ac.rca.nat2022.server.utils.dtos.WebsiteDTO;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class WebsiteServiceImpl implements IWebsiteService {
    private final IWebsiteRepository websiteRepository;
    private final ILinkRepository linkRepository;

    public WebsiteServiceImpl(IWebsiteRepository websiteRepository, ILinkRepository linkRepository) {
        this.websiteRepository = websiteRepository;
        this.linkRepository = linkRepository;
    }

    @Override
    public List<Website> getAllWebsites() {
        return websiteRepository.findAll();
    }

    @Override
    public Website save(WebsiteDTO websiteDTO) {
        return websiteRepository.save(new Website(websiteDTO));
    }

    @Override
    public Website update(Long id, WebsiteDTO websiteDTO) {
        Website website = websiteRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Website not found")
        );
        website.setWebsite_name(websiteDTO.getName());
        website.setDownload_start_date_time(websiteDTO.getDownloadStartDateTime());
        website.setDownload_end_date_time(websiteDTO.getDownloadEndDateTime());
        website.setTotal_elapsed_time(websiteDTO.getTotalElapsedTime());
        website.setTotal_downloaded_kilobytes(websiteDTO.getTotalDownloadedKilobytes());
        return websiteRepository.save(website);
    }

    @Override
    public Website getWebsiteById(Long id) {
        return websiteRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Website not found")
        );
    }

    @Override
    public void deleteWebsiteById(Long id) {
        websiteRepository.deleteById(id);
    }

    @Override
    public List<Website> crawlWebsite(String url) {
        System.out.println("Crawling website: " + url);
        // get domain name
        String postFixUrl = url.substring(url.indexOf("://") + 3);
        String domainName = postFixUrl.substring(0, postFixUrl.indexOf("/"));
        // get website name as string after domain name slash
        String websiteName = postFixUrl.substring(postFixUrl.indexOf("/") + 1);
        websiteName = websiteName.replace("/", " ");
        if(websiteName.contains("?")) {
            websiteName = websiteName.substring(0, websiteName.indexOf("?"));
        }
        if(websiteName.contains("#")) {
            websiteName = websiteName.substring(0, websiteName.indexOf("#"));
        }
        if(websiteName.contains(".")) {
            websiteName = websiteName.substring(0, websiteName.indexOf("."));
        }
         if(websiteName.isEmpty()){
             websiteName = "index";
         }

        System.out.println("Domain name: " + domainName);
        System.out.println("Website name: " + websiteName);

        File file = new File("C:\\download-manager\\" + domainName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }else {
            System.out.println("Directory already exists!");
        }

        String homepageUrl = url;

        Long start = System.nanoTime();
        DownloadWebPage(homepageUrl, "C:\\download-manager\\" + domainName + "\\" + websiteName + ".html");
        Long finish = System.nanoTime();

        Long timeElapsed = finish - start;

        // find website by domain name
        List<Website> allWebsites = websiteRepository.findAll();
        Website website = allWebsites.stream()
                .filter(w -> w.getWebsite_name().equals(domainName))
                .findFirst()
                .orElse(null);
        if(website == null) {
            WebsiteDTO websiteDTO = new WebsiteDTO();
            websiteDTO.setName(domainName);
            websiteDTO.setDownloadStartDateTime(start);
            websiteDTO.setDownloadEndDateTime(finish);
            websiteDTO.setTotalElapsedTime(timeElapsed);
            website = new Website(websiteDTO);
            website = websiteRepository.save(website);
        }else {
            website.setDownload_start_date_time(start);
            website.setDownload_end_date_time(finish);
            if(website.getTotal_elapsed_time() == null) {
                website.setTotal_elapsed_time(timeElapsed);
            }else {
                website.setTotal_elapsed_time(website.getTotal_elapsed_time() + timeElapsed);
            }
            Long totalDownloadedKilobytes = getFileSize("C:\\download-manager\\" + domainName + "\\" + websiteName + ".html");
            if (website.getTotal_downloaded_kilobytes() == null) {
                website.setTotal_downloaded_kilobytes(totalDownloadedKilobytes);
            }else {
                website.setTotal_downloaded_kilobytes(website.getTotal_downloaded_kilobytes() + totalDownloadedKilobytes);
            }
            System.out.println("Total downloaded kilobytes: " + website.getTotal_downloaded_kilobytes());
            websiteRepository.save(website);
        }

        // create or update link for homepage
        List<Link> allLinks = linkRepository.findAll();
        String finalWebsiteName = websiteName;
        Website finalWebsite = website;
        Link savedLink = allLinks.stream()
                .filter(l -> l.getLink_name().equals(finalWebsiteName) && l.getWebsite().getId().equals(finalWebsite.getId()))
                .findFirst()
                .orElse(null);

        if(savedLink == null) {
            savedLink = new Link();
        }
        savedLink.setTotal_elapsed_time(timeElapsed);
        savedLink.setLink_name(websiteName);
        savedLink.setWebsite(website);
        savedLink.setTotal_downloaded_kilobytes(getFileSize("C:\\download-manager\\" + domainName + "\\" + websiteName + ".html"));

        savedLink = linkRepository.save(savedLink);
        return websiteRepository.findAll();
    }

    public static void DownloadWebPage(String webpage, String outputFile) {
        try {
            URL url = new URL(webpage);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            FileWriter writer = new FileWriter(outputFile);
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
            writer.close();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getFileSize(String fileName){
        Path path = Paths.get(fileName);

        try {

            // size of a file (in bytes)
            Long bytes = Files.size(path);
            return bytes / 1024;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}
