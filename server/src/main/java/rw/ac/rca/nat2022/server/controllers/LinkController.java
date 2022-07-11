package rw.ac.rca.nat2022.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.nat2022.server.services.ILinkService;
import rw.ac.rca.nat2022.server.utils.ApiResponse;
import rw.ac.rca.nat2022.server.utils.dtos.LinkDTO;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    private final ILinkService linkService;

    public LinkController(ILinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("")
    public ApiResponse getAllLinks() {
        return new ApiResponse(HttpStatus.OK, true, "All links fetched", linkService.getAllLinks());
    }

    @GetMapping("/{id}")
    public ApiResponse getLinkById(@PathVariable("id") Long id) {
        return new ApiResponse(HttpStatus.OK, true, "Link fetched", linkService.getLinkById(id));
    }

    @PostMapping("")
    public ApiResponse save(@RequestBody LinkDTO link) {
        return new ApiResponse(HttpStatus.OK, true, "Link saved", linkService.save(link));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("id") Long id,@RequestBody LinkDTO link) {
        return new ApiResponse(HttpStatus.OK, true, "Link updated", linkService.update(id, link));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteLinkById(@PathVariable("id") Long id) {
        linkService.deleteLinkById(id);
        return new ApiResponse(HttpStatus.OK, true, "Link deleted", null);
    }

}
