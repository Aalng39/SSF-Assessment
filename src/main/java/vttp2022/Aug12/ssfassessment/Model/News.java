package vttp2022.Aug12.ssfassessment.Model;

import java.util.LinkedList;
import java.util.List;


public class News {
    public List<String> savedArticles = new LinkedList<>();
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public News(List<String> savedArticles2) {
    }

    public List<String> getSavedArticles() {
        return savedArticles;
    }

    public void setSavedArticles(List<String> savedArticles) {
        this.savedArticles = savedArticles;
    }
}
