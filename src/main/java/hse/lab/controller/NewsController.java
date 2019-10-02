package hse.lab.controller;

import hse.lab.service.NewsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @Autowired
    private NewsParser newsParser;

    @GetMapping("/news")
    public String getNews() {
        return null;
    }

    @GetMapping("/pageTitle")
    public String getTitle() {
        return newsParser.getHtmlPage().title();
    }
}