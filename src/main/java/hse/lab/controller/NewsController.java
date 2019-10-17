package hse.lab.controller;

import hse.lab.service.NewsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

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
        List<String> articles = newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL));
        newsParser.publishReport(NewsParser.PATH, TPROGER_URL, articles);
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @RequestMapping(value = "/science", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getAnotherNews() {
        ModelAndView modelAndView = new ModelAndView("Page");
        List<String> articles = newsParser.findArticles(newsParser.getHtmlPage(GAZETA_URL));
        newsParser.publishReport(NewsParser.PATH, TPROGER_URL, articles);
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @RequestMapping(value = "/war", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getOneMoreNews() {
        ModelAndView modelAndView = new ModelAndView("Page");
        List<String> articles = newsParser.findArticles(newsParser.getHtmlPage(TOPWAR_URL));
        newsParser.publishReport(NewsParser.PATH, TPROGER_URL, articles);
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "getTprogerJson", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getTprogerJson() {
        return newsParser.publishReport(NewsParser.PATH, TPROGER_URL,
                newsParser.findArticles(newsParser.getHtmlPage(TPROGER_URL))).toJSONString();
    }

    @GetMapping
    public ModelAndView defaultPage() {
        ModelAndView modelAndView = new ModelAndView("PageNotFound");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/task1", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String task1Page() {
        return new Date().toString();
    }
}