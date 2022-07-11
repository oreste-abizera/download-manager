package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.Website;
import rw.ac.rca.nat2022.server.repositories.IWebsiteRepository;
import rw.ac.rca.nat2022.server.services.IWebsiteService;
import rw.ac.rca.nat2022.server.utils.dtos.WebsiteDTO;

import java.io.File;
import java.util.List;

@Service
public class WebsiteServiceImpl implements IWebsiteService {
    private final IWebsiteRepository websiteRepository;

    public WebsiteServiceImpl(IWebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
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
    public Website crawlWebsite(String url) {
        System.out.println("Crawling website: " + url);
        // get domain name
        String postFixUrl = url.substring(url.indexOf("://") + 3);
        String domainName = postFixUrl.substring(0, postFixUrl.indexOf("/"));
        // get website name
        String websiteName = domainName.substring(domainName.indexOf(".") + 1);

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
//        String homepageFilename = "C:\\download-manager\\" + domainName + "\\" + websiteName + ".html";
        return null;
    }
}
