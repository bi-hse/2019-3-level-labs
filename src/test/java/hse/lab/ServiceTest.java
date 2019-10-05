package hse.lab;

import com.google.gson.Gson;
import hse.lab.model.Result;
import hse.lab.service.NewsParser;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    private final String TPROGER_URL = "https://tproger.ru/";
    private final Gson gson = new Gson();

    @Autowired
    NewsParser parser;

    @Test
    public void structureTest() throws JSONException {
        String result = parser.publishReport(TPROGER_URL, parser.findArticles(parser.getHtmlPage(TPROGER_URL)));
        Result object = gson.fromJson(result, Result.class);
        Assert.assertNotNull(object);
        Assert.assertEquals(object.getUrl(), TPROGER_URL);
        Assert.assertNotNull(object.getArticles());
    }
}
