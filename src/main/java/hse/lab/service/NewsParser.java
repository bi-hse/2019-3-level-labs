package hse.lab.service;

import com.google.gson.Gson;
import hse.lab.model.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsParser {

    public String publishReport(String path, List<String> articles) {
        Result result = new Result(path, new Date(), articles);
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    public List<String> findArticles(Document page) {
        Elements articles = page.select("h2");
        List<String> resultList = new ArrayList<>();
        for (Element h: articles) {
            String m = h.text();
            if (m != null && !m.trim().isEmpty()) {
                if (!resultList.contains(m)) {
                    if (!(m.equals("По языкам") || m.equals("По брендам") || m.equals("По направлениям"))) {
                        if (!m.toLowerCase().contains("подпишись") && !m.toLowerCase().contains("подпишитесь")) {
                            resultList.add(m);
                        }
                    }
                }
            }
        }
        return resultList;
    }

    public Document getHtmlPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Could not get the page");
            System.out.println("Cause: " + e.getMessage());
            return null;
        }
    }
}
