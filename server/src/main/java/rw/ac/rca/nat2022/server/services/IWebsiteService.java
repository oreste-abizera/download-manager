package rw.ac.rca.nat2022.server.services;

import rw.ac.rca.nat2022.server.models.Website;
import rw.ac.rca.nat2022.server.utils.dtos.WebsiteDTO;

import java.util.List;

public interface IWebsiteService {
    List<Website> getAllWebsites();
    Website save(WebsiteDTO websiteDTO);
    Website update(Long id, WebsiteDTO websiteDTO);
    Website getWebsiteById(Long id);
    void deleteWebsiteById(Long id);

    Website crawlWebsite(String url);
}
