package rw.ac.rca.nat2022.client.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("websites")
public class WebsitesResource {
    @GetMapping("list")
    public String list(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        return "websites/list";
    }

    @GetMapping("crawl")
    public String crawl(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        return "websites/crawl";
    }
}
