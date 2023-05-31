package de.pizzaworld.logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author André Heinen
 */
public class Newsfeed {

    private StringProperty newsProperty;

    public Newsfeed() {
        this.newsProperty = new SimpleStringProperty("");
    }

    public void setNews(String news) {
        if (getNews().equals("")) {
            newsProperty.set(news);
        } else {
            newsProperty.set(getNews() + "\n" + news);
        }
    }

    public String getNews() {
        if (newsProperty != null) {
            return newsProperty.get();
        } else {
            return "public String getNews() in class Newsfeed Error";
        }
    }

    public StringProperty getNewsProperty() {
        if (newsProperty == null) {
            newsProperty = new SimpleStringProperty("");
        }
        return newsProperty;
    }

    public void clearNews() {
        newsProperty.set("");
    }
}