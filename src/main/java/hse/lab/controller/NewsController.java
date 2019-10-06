package hse.lab.controller;

import hse.lab.service.NewsParser;
import hse.lab.service.PageFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewsController {

    private final String TPROGER_URL = "https://tproger.ru/";

    @Autowired
    private NewsParser newsParser;

    @Autowired
    private PageFormer former;

    @RequestMapping(value = "/news", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getNews() {
        return former.createPage(newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL)));
    }
}