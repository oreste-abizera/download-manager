package rw.ac.rca.nat2022.client.web.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import rw.ac.rca.nat2022.client.dao.Link;
import rw.ac.rca.nat2022.client.dao.Website;
import rw.ac.rca.nat2022.client.utils.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static rw.ac.rca.nat2022.client.utils.Utility.formatURL;

@Controller
@RequestMapping("websites")
public class WebsitesResource {
    @GetMapping("list")
    public String list(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();
        headers.setBearerAuth(token);

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ApiResponse response = restTemplate.exchange(formatURL("/api/websites"), HttpMethod.GET, entity, ApiResponse.class).getBody();
        List<Website> websites = (List<Website>) response.getData();
        model.addAttribute("websites", websites);
        return "websites/list";
    }

    @GetMapping("crawl")
    public String crawl(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        return "websites/crawl";
    }

    @PostMapping("crawl")
    public String crawlHomepage(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        String url = request.getParameter("url");

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            model.addAttribute("error", "URL must start with http:// or https://");
            model.addAttribute("url", url);
            return "websites/crawl";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String token = request.getSession().getAttribute("token").toString();
        headers.setBearerAuth(token);


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("url", url);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        ApiResponse response = restTemplate.exchange(formatURL("/api/websites/crawl?url=" + url), HttpMethod.POST, entity, ApiResponse.class).getBody();
        List<Website> websites = (List<Website>) response.getData();
        model.addAttribute("websites", websites);
        return "websites/list";
    }
}
