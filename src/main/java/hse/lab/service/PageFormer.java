package hse.lab.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageFormer {
    public void createPage(List<String> headers) {
        Document doc = Jsoup.parse("Page.html");
        Element div = doc.select("div").first(); // <div></div>
        div.html("<ul>");
        for (String m : headers) {
            div.append("<li>" + m + "</li>");
        }
        div.html("</ul>");
    }
}