package hse.lab.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NewsParser {

    private final String TPROGER_URL = "https://tproger.ru/";

    public void parseNews() {

    }

    public List<String> getNews() {
        return null;
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
