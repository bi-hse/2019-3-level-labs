package hse.lab.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PageFormer {
    public String createPage(List<String> headers) {
        try {
            File page = new File("src/main/resources/templates/Page.html");
            Document doc = Jsoup.parse(page, "UTF-8");
            Element div = doc.select("div").first(); // <div></div>
            div.html("<ol>");
            Element list = doc.select("ol").first();
            for (String m : headers) {
                        list.append("<li>" + m + "</li>");
            }

            Path newFile = Paths.get("src/main/resources/templates/new_page.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile.toString(), false));
            if (!Files.exists(newFile)) {
                Files.createFile(newFile);
            }
            bw.write(doc.toString());
            bw.close();
            return "new_page";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}