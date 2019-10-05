package hse.lab.model;

import java.util.Date;
import java.util.List;

public class Result {
    private String url;
    private Date creationDate;
    private List<String> articles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    public Result(String url, Date creationDate, List<String> articles) {
        this.url = url;
        this.creationDate = creationDate;
        this.articles = articles;
    }
}
