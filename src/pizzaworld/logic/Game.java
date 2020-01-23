package pizzaworld.logic;

import java.io.Serializable;
import pizzaworld.dishes.Products;

/**
 *
 * @author andre
 */
public class Game implements Serializable {

    public static final boolean DEBUGGING = true;
    
    private int day = 1;

    private final Products products;
    private final Newsfeed newsfeed;
    private final GuestList guestlist;
    
    public static final int GUESTCOUNT = 1000;
    public static final int PLAYERCOUNT = 4;
    
    private final Player[] players;

    public Game() {

        products = new Products();
        newsfeed = new Newsfeed();
        
        guestlist = new GuestList(this);

        players = new Player[PLAYERCOUNT];
        players[0] = new Player(this);
        for (int i = 1; i < players.length; i++) {
            players[i] = new PlayerAi(this);
        }

        if (DEBUGGING) {
            int pl = -1;
            for (int i = 0; i < 16; i++) {
                if ((i % 4) == 0) {
                    pl++;
                }
                products.getDishes().get(i).setAvailable(pl, true);
            }
        }
    }
    
    public int getDay() {
        return day;
    }
    
    public void incDay() {
        day += 1;
    }

    public Products getProducts() {
        return products;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Newsfeed getNewsfeed() {
        return newsfeed;
    }
    
    public GuestList getGuestList() {
        return guestlist;
    }
}
