package rw.ac.rca.nat2022.client.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("websites")
public class WebsitesResource {
    @GetMapping("list")
    public String list() {
        return "websites/list";
    }

    @GetMapping("crawl")
    public String crawl() {
        return "websites/crawl";
    }
}
