package pizzaworld.logic;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author André Heinen
 */
public class Restaurant implements Serializable {
    
    private int maxSeats = 12;
    private transient IntegerProperty usedSeatsProperty;
    private final int[] seatTimes;

    public Restaurant() {
        seatTimes = new int[((24 - Clock.STARTHOUR) * 60) + 20];
        for (int i = 0; i < seatTimes.length; i++) {
            seatTimes[i] = 0;
        }
        usedSeatsProperty = new SimpleIntegerProperty(0);
    }

    public void setMaxSeats(int seats) {
        maxSeats = seats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public IntegerProperty getUsedSeatsProperty() {
        if (usedSeatsProperty == null) {
            usedSeatsProperty = new SimpleIntegerProperty(0);
        }
        return usedSeatsProperty;
    }

    public int getUsedSeats() {
        if (usedSeatsProperty != null) {
            return usedSeatsProperty.get();
        } else {
            return -1;
        }
    }

    public void incUsedSeats(int time) {
        usedSeatsProperty.set(getUsedSeats() + 1);
    }

    public void decUsedSeats(int time) {
        usedSeatsProperty.set(getUsedSeats() - seatTimes[time]);
    }

    public void resetSeatTimes() {
        for (int i = 0; i < seatTimes.length; i++) {
            seatTimes[i] = 0;
        }
        usedSeatsProperty.set(0);
    }

    public void incSeatTime(int time) {
        seatTimes[time + 20]++;
    }

    public boolean isSeatAvailable() {
        return getUsedSeats() < getMaxSeats();
    }

}
