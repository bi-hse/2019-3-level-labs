package hse.lab.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsParser {

    private final String TPROGER_URL = "https://tproger.ru/";

    public void publishReport(Path path, List<String> articles) {

    }

    public List<String> findArticles(Document page) {
        Elements articles = page.select("h2");
        List<String> resultList = new ArrayList<>();
        for (Element h: articles) {
            resultList.add(h.text());
        }
        return resultList;
    }

    public Document getHtmlPage() {
        try {
            return Jsoup.connect(TPROGER_URL).get();
        } catch (IOException e) {
            System.out.println("Could not get the tproger.ru page");
            System.out.println("Cause: " + e.getMessage());
            return null;
        }
    }
}
