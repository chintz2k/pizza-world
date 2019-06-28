package pizzaworld.logic;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author André Heinen
 */
public class Clock implements Serializable {

    /**
     * the starting hour of the day
     */
    public static final int STARTHOUR = 12;

    private int hour;
    private int minute;
    
    private int daytime;

    private transient StringProperty timeProperty;

    public Clock() {
        hour = STARTHOUR;
        minute = 0;
        daytime = 0;
        timeProperty = new SimpleStringProperty(hour + ":0" + minute + " Uhr");
    }

    public void setTime(String string) {
        timeProperty.set(string);
    }

    public String getTime() {
        if (timeProperty != null) {
            return timeProperty.get();
        } else {
            return "String getTime() Fehler!";
        }
    }

    public StringProperty getTimeProperty() {
        if (timeProperty == null) {
            timeProperty = new SimpleStringProperty(hour + ":0" + minute + " Uhr");
        }
        return timeProperty;
    }

    public int getDaytime() {
        return daytime;
    }

    public int getStartHour() {
        return STARTHOUR;
    }

    public boolean incMinute() {
        daytime++;
        if (minute == 59) {
            if (hour == 23) {
                setTime("00:00 Uhr");
                return false;
            } else {
                hour++;
                minute = 0;
                setTime(hour + ":0" + minute + " Uhr");
                return true;
            }
        } else {
            minute++;
            if (minute < 10) {
                setTime(hour + ":0" + minute + " Uhr");
                return true;
            } else {
                setTime(hour + ":" + minute + " Uhr");
                return true;
            }
        }
    }
    
    public void reset() {
        hour = STARTHOUR;
        minute = 0;
        daytime = 0;
        setTime(hour + ":0" + minute + " Uhr");
    }

}
