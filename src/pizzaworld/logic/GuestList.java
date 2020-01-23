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
    
    public GuestList(Game game) {
        guests = new ArrayList<>();
        for (int i = 0; i < Game.GUESTCOUNT; i++) {
            guests.add(new Guest(game));
        }
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }
}
