package logic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author André Heinen
 */
public class Newsfeed {

    private transient StringProperty newsProperty;

    public Newsfeed() {
        this.newsProperty = new SimpleStringProperty("");
    }

    public void setNews(String news) {
        newsProperty.set(news);
    }

    public void addNews(String news) {
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
            return "String getNews() Fehler!";
        }
    }

    public StringProperty getNewsProperty() {
        if (newsProperty == null) {
            newsProperty = new SimpleStringProperty("");
        }
        return newsProperty;
    }

    public void reset() {
        newsProperty.set("");
    }

}