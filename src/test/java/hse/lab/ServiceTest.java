package hse.lab;

import hse.lab.service.NewsParser;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    private final String TPROGER_URL = "https://tproger.ru/";

    @Autowired
    NewsParser parser;

    @Test
    public void structureTest() throws JSONException {
        JSONObject object = parser.publishReport(NewsParser.PATH, TPROGER_URL, parser.findArticles(parser.getHtmlPage(TPROGER_URL)));
        Assert.assertNotNull(object);
        Assert.assertEquals(object.get("url"), TPROGER_URL);
        Assert.assertNotNull(object.get("articles"));
        Assert.assertNotEquals(object.get("articles"), 0);
    }

    @Test
    public void urlTest() throws IOException {
        Connection.Response response = Jsoup.connect(TPROGER_URL).followRedirects(false).execute();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void headersTest() throws IOException {
        File savedPage = new File("src/test/resources/static/tproger.html");
        Document doc = Jsoup.parse(savedPage, "UTF-8", TPROGER_URL);
        List<String> articles = parser.findArticles(doc);
        Assert.assertNotNull(articles);
        System.out.println(articles.size());
        Assert.assertNotEquals(0, articles.size());
    }
}
