package hse.lab.controller;

import hse.lab.service.NewsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {

    private final String TPROGER_URL = "https://tproger.ru/";

    @Autowired
    private NewsParser newsParser;

    @RequestMapping(value = "/news", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getNews() {
        ModelAndView modelAndView = new ModelAndView("Page");
        modelAndView.addObject("articles", newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL)));
        return modelAndView;
    }
}