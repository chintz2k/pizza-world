package logic;

import java.util.ArrayList;
import staff.Guest;

/**
 *
 * @author André Heinen
 */
public class GuestList {
    
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