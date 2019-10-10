package hse.lab.service;

import com.google.gson.Gson;
import hse.lab.model.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsParser {
    public static final String PATH = "src/main/resources/data/articles.json";

    public String publishReport(String path, String url, List<String> articles) {
        Result result = new Result(url, new Date(), articles);
        Gson gson = new Gson();
        String json = gson.toJson(result);
        JSONObject obj = new JSONObject();
        obj.put("url", result.getUrl());
        obj.put("creationDate", result.getCreationDate());

        JSONArray jsonArticles = new JSONArray();
        jsonArticles.addAll(articles);
        obj.put("articles", jsonArticles);

        try {
            Files.createFile(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter(path)) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
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
