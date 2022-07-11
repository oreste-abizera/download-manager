package rw.ac.rca.nat2022.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.nat2022.server.services.IWebsiteService;
import rw.ac.rca.nat2022.server.utils.ApiResponse;
import rw.ac.rca.nat2022.server.utils.dtos.CrawlDTO;
import rw.ac.rca.nat2022.server.utils.dtos.WebsiteDTO;

@RestController
@RequestMapping("/api/websites")
public class WebsiteController {
    private final IWebsiteService websiteService;

    public WebsiteController(IWebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("")
    public ApiResponse getAllWebsites() {
        return new ApiResponse(HttpStatus.OK, true, "All websites fetched", websiteService.getAllWebsites());
    }

    @PostMapping("/crawl")
    public ApiResponse crawlWebsite(@RequestBody CrawlDTO crawlDTO) {
        return new ApiResponse(HttpStatus.OK, true, "Website crawled", websiteService.crawlWebsite(crawlDTO.getUrl()));
    }

    @GetMapping("/{id}")
    public ApiResponse getWebsiteById(@PathVariable("id") Long id) {
        return new ApiResponse(HttpStatus.OK, true, "Website fetched", websiteService.getWebsiteById(id));
    }

    @PostMapping("")
    public ApiResponse save(@RequestBody WebsiteDTO websiteDTO) {
        return new ApiResponse(HttpStatus.OK, true, "Website saved", websiteService.save(websiteDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("id") Long id,@RequestBody WebsiteDTO websiteDTO) {
        return new ApiResponse(HttpStatus.OK, true, "Website updated", websiteService.update(id, websiteDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteWebsiteById(@PathVariable("id") Long id) {
        websiteService.deleteWebsiteById(id);
        return new ApiResponse(HttpStatus.OK, true, "Website deleted", null);
    }
}
