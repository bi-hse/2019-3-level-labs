package hse.lab.controller;

import hse.lab.service.NewsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {

    private final String TPROGER_URL = "https://tproger.ru/";
    private final String GAZETA_URL = "https://www.gazeta.ru/science/?updated";
    private final String TOPWAR_URL = "https://topwar.ru/news/";

    @Autowired
    private NewsParser newsParser;

    @RequestMapping(value = "/news", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getNews() {
        ModelAndView modelAndView = new ModelAndView("Page");
        modelAndView.addObject("articles", newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL)));
        return modelAndView;
    }

    @RequestMapping(value = "/science", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getAnotherNews() {
        ModelAndView modelAndView = new ModelAndView("Page");
        modelAndView.addObject("articles", newsParser.findArticles(newsParser.getHtmlPage(GAZETA_URL)));
        return modelAndView;
    }

    @RequestMapping(value = "/war", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getOneMoreNews() {
        ModelAndView modelAndView = new ModelAndView("Page");
        modelAndView.addObject("articles", newsParser.findArticles(newsParser.getHtmlPage(TOPWAR_URL)));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "getTprogerJson", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getTprogerJson() {
        return newsParser.publishReport(NewsParser.PATH, TPROGER_URL,
                newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL)));
    }
}