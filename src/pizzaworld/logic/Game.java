package pizzaworld.logic;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.stage.Stage;
import pizzaworld.dishes.Products;
import pizzaworld.staff.Guest;

/**
 *
 * @author andre
 */
public class Game implements Serializable {

    public static final boolean DEBUGGING = true;

    private final Stage stage;

    private final Clock clock;
    private final Timer timer;

    private final Products products;
    private final Newsfeed newsfeed;
    private final GuestList guestlist;
    
    public static final int GUESTCOUNT = 1000;
    public static final int PLAYERCOUNT = 4;
    
    private final Player[] players;
    private final Player[] sortedPlayers;

    public Game(Stage stage) {
        this.stage = stage;

        clock = new Clock();
        timer = new Timer(this, clock);

        products = new Products();
        newsfeed = new Newsfeed();
        guestlist = new GuestList(this);

        players = new Player[PLAYERCOUNT];
        sortedPlayers = new Player[PLAYERCOUNT];
        players[0] = new Player(this);
        sortedPlayers[0] = players[0];
        for (int i = 1; i < players.length; i++) {
            players[i] = new PlayerAi(this);
            sortedPlayers[i] = players[i];
        }
        sortPlayers();

        if (DEBUGGING) {
            for (int i = 0; i < players.length; i++) {
                System.out.println("Spieler" + i + " hat " + players[i].getFame() + " Ehre!");
            }
            for (int i = 0; i < players.length; i++) {
                System.out.print(sortedPlayers[i].getId() + " ");
            }
            System.out.println("");
            
            int pl = -1;
            for (int i = 0; i < 16; i++) {
                if ((i % 4) == 0) {
                    pl++;
                }
                products.getDishes().get(i).setAvailable(pl, true);
            }
        }
    }

    public final int sortPlayers() {
        // < ist aufsteigend, <= ist absteigend
        int random;
        Player temp;
        for (int i = 1; i < sortedPlayers.length; i++) {
            for (int j = 0; j < sortedPlayers.length - i; j++) {
                // Experimental
                random = (int) (Math.random() * 2);
                if (random == 0) {
                    if (sortedPlayers[j].getFame() /*HIER>>>*/ < /*<<<HIER*/ sortedPlayers[j + 1].getFame()) {
                        temp = sortedPlayers[j];
                        sortedPlayers[j] = sortedPlayers[j + 1];
                        sortedPlayers[j + 1] = temp;
                    } else {
                        if (sortedPlayers[j].getFame() /*HIER>>>*/ <= /*<<<HIER*/ sortedPlayers[j + 1].getFame()) {
                            temp = sortedPlayers[j];
                            sortedPlayers[j] = sortedPlayers[j + 1];
                            sortedPlayers[j + 1] = temp;
                        }
                    }
                }
                /*
                // Stable
                if (sortedPlayers[j].getFame() /*HIER>>>*//*</*<<<HIER*//* sortedPlayers[j + 1].getFame()) {
                    temp = sortedPlayers[j];
                    sortedPlayers[j] = sortedPlayers[j + 1];
                    sortedPlayers[j + 1] = temp;
                }
                */
            }
        }
        return sortedPlayers[0].getId();
    }

    public void endDay() {
        clock.reset();
        newsfeed.reset();
        for (Player player : players) {
            player.resetDay();
        }
    }

    public Stage getStage() {
        return stage;
    }

    public Clock getClock() {
        return clock;
    }

    public Timer getTimer() {
        return timer;
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

    public Player[] getSortedPlayers() {
        return sortedPlayers;
    }

}
