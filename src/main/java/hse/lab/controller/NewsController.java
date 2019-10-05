package hse.lab.controller;

import hse.lab.service.NewsParser;
import hse.lab.service.PageFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @Autowired
    private NewsParser newsParser;

    @Autowired
    private PageFormer former;

    @GetMapping("/news")
    public void getNews() {
        // List<String> list = newsParser.getNews();
        former.createPage(newsParser.getNews());
    }

    @GetMapping("/pageTitle")
    public String getTitle() {
        return newsParser.getHtmlPage().title();
    }
}