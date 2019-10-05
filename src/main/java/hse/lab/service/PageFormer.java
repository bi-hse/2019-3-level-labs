package hse.lab.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PageFormer {
    public Document createPage(List<String> headers) {
        try {
            File page = new File("src/main/resources/templates/Page.html");
            Document doc = Jsoup.parse(page, "UTF-8");
            Element div = doc.select("div").first(); // <div></div>
            div.html("<ul>");
            for (String m : headers) {
                div.append("<li>" + m + "</li>");
            }
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}