package rw.ac.rca.nat2022.client.web.rest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import rw.ac.rca.nat2022.client.dao.Website;
import rw.ac.rca.nat2022.client.utils.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
//        for (Website website : websites) {
//            website.setF_download_start_date_time(formatTime(website.getDownload_start_date_time()));
//            website.setF_download_end_date_time(formatTime(website.getDownload_end_date_time()));
//        }
        model.addAttribute("websites", websites);
//        model.addAttribute("formatTime", formatTime);
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
    public String crawlHomepage(HttpServletRequest request, Model model) throws IOException {
        if(request.getSession().getAttribute("token") == null || request.getSession().getAttribute("token").toString().isEmpty()){
            return "redirect:/auth/login";
        }
        String url = request.getParameter("url");

        if(!url.endsWith("/")){
            url = url + "/";
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            model.addAttribute("error", "URL must start with http:// or https://");
            model.addAttribute("url", url);
            return "websites/crawl";
        }
        if (!check_URL(url)) {
            model.addAttribute("error", "URL is not valid");
            model.addAttribute("url", url);
            return "websites/crawl";
        }
        if(!exists(url)){
            model.addAttribute("error", "URL does not exist");
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
        List<String> crawledLinks = getLinks(url);
        System.out.println("Crawling website: " + url);
        ApiResponse response = restTemplate.exchange(formatURL("/api/websites/crawl?url=" + url), HttpMethod.POST, entity, ApiResponse.class).getBody();
        List<Website> websites = (List<Website>) response.getData();
//        for (String link : crawledLinks) {
//            System.out.println("Crawling website: " + link);
//            restTemplate.exchange(formatURL("/api/websites/crawl?url=" + link), HttpMethod.POST, entity, ApiResponse.class);
//        }
        model.addAttribute("links", crawledLinks);
        return "links/list-search";
    }

    public boolean check_URL(String urlString) {
        try {
            new URL(urlString).toURI();
            return true;
        } catch (Exception e1) {
            return false;
        }
    }
    public boolean exists(String URLName){
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        return links.eachAttr("abs:href");
    }
}
