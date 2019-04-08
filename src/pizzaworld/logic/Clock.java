package pizzaworld.logic;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author André
 */
public class Clock implements Serializable {

    private final int startHour = 12;

    private int hour;
    private int minute;

    private StringProperty timeProperty;

    public Clock() {
        hour = startHour;
        minute = 0;
        timeProperty = new SimpleStringProperty("Clock-Konstruktor!");
    }

    public final void setTime(String string) {
        timeProperty.set(string);
    }

    public final String getTime() {
        if (timeProperty != null) {
            return timeProperty.get();
        } else {
            return "Fehler!";
        }
    }

    public StringProperty getTimeProperty() {
        if (timeProperty == null) {
            timeProperty = new SimpleStringProperty("timeProperty");
        }
        return timeProperty;
    }

    public void incMinute() {
        if (minute == 59) {
            if (hour == 23) {
                // TODO Tageswechsel
            } else {
                hour++;
                minute = 0;
                setTime(hour + ":0" + minute + " Uhr");
            }
        } else {
            minute++;
            if (minute < 10) {
                setTime(hour + ":0" + minute + " Uhr");
            } else {
                setTime(hour + ":" + minute + " Uhr");
            }
        }
    }

}
