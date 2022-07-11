package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.Website;
import rw.ac.rca.nat2022.server.repositories.IWebsiteRepository;
import rw.ac.rca.nat2022.server.services.IWebsiteService;
import rw.ac.rca.nat2022.server.utils.dtos.WebsiteDTO;

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
}
