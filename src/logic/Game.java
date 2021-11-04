package logic;

import dishes.Products;

/**
 *
 * @author André Heinen
 */
public class Game {

    public static final boolean DEBUGGING = true;

    public static final int GUESTCOUNT = 1000;
    public static final int PLAYERCOUNT = 4;
    
    private int day = 1;

    private final Products products;
    private final Newsfeed newsfeed;
    private final GuestList guestlist;
    
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
    
    public void endCurrentDay() {
        for (int i = 0; i < Game.PLAYERCOUNT; i++) {
            for (int j = 0; j < Game.GUESTCOUNT; j++) {
                this.guestlist.getGuests().get(j).buy(i);
            }
        }
    }

    public void startNewDay() {
        day += 1;
        for (int i = 0; i < PLAYERCOUNT; i++) {
            players[i].getStatistics().addColumn();
        }
    }

    public int getDay() {
        return day;
    }
    
    public Products getProducts() {
        return products;
    }

    public Newsfeed getNewsfeed() {
        return newsfeed;
    }
    
    public GuestList getGuestList() {
        return guestlist;
    }
    
    public Player[] getPlayers() {
        return players;
    }
}