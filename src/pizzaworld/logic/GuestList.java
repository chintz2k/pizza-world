package pizzaworld.logic;

import java.io.Serializable;
import java.util.ArrayList;
import pizzaworld.staff.Guest;

/**
 *
 * @author André Heinen
 */
public class GuestList implements Serializable {
    
    private final ArrayList<Guest> guests;
    private final ArrayList<ArrayList<Integer>> guestTimes;
    
    private final int openminutes = (24 - Clock.STARTHOUR) * 60;
    
    public GuestList(Game game) {
        
        guests = new ArrayList<>();
        
        guestTimes = new ArrayList<>();
        for (int i = 0; i < openminutes; i++) {
            guestTimes.add(new ArrayList<>());
        }

        for (int i = 0; i < Game.GUESTCOUNT; i++) {
            guests.add(new Guest(game));
            guestTimes.get(guests.get(i).getTime()).add(i);
        }

    }
    
    /**
     * Let's all guests of the actual daytime use their buy() method
     * @param time the actual daytime
     */
    public void guestsTimeBuy(int time) {
        for (int i = 0; i < guestTimes.get(time).size(); i++) {
            guests.get(guestTimes.get(time).get(i)).buy();
        }
    }
}
