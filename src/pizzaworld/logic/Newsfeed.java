package pizzaworld.logic;

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
    
    public final void setNews(String news) {
        newsProperty.set(news);
    }
    
    public final void addNews(String news) {
        newsProperty.set(getNews() + news);
    }
    
    public final String getNews() {
        if (newsProperty != null) {
            return newsProperty.get();
        } else {
            return "NewsProperty Fehler!";
        }
    }
    
    public final StringProperty getNewsProperty() {
        if (newsProperty == null) {
            newsProperty = new SimpleStringProperty("");
        }
        return newsProperty;
    }
    
    public void clearNews() {
        newsProperty.set("");
    }
    
}
