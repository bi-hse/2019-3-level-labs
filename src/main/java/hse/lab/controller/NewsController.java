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
    private final String GAZETA_URL = "https://www.gazeta.ru/science/?updated";
    private final String TOPWAR_URL = "https://topwar.ru/news/";

    @Autowired
    private NewsParser newsParser;

    @Autowired
    private PageFormer former;

    @RequestMapping(value = "/news", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getNews() {
        return former.createPage(newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL)));
    }

    @RequestMapping(value = "/science", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getAnotherNews() {
        return former.createPage(newsParser.findArticles(newsParser.getHtmlPage(GAZETA_URL)));
    }

    @RequestMapping(value = "/something", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getOneMoreNews() {
        return former.createPage(newsParser.findArticles(newsParser.getHtmlPage(TOPWAR_URL)));
    }
}