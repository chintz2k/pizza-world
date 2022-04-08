package logic;

/**
 *
 * @author André Heinen
 */
public class Game {

    public static final boolean DEBUGGING = true;

    public static final int NUMBER_OF_GUESTS = 1000;
    public static final int NUMBER_OF_PLAYERS = 4;
    public static final int NUMBER_OF_DISHES = 16;
    public static final int MAX_AMOUNT_OF_DISHES = 8;
    public static final double WIDTH = 360.0;
    public static final double HEIGHT = 640.0;

    private int day = 1;

    private final Newsfeed newsfeed;

    private final Guest[] guests;
    private final Player[] players;

    public Game() {

        newsfeed = new Newsfeed();

        guests = new Guest[NUMBER_OF_GUESTS];
        for (int i = 0; i < guests.length; i++) {
            guests[i] = new Guest();
        }

        players = new Player[NUMBER_OF_PLAYERS];
        players[0] = new Player();
        for (int i = 1; i < players.length; i++) {
            players[i] = new PlayerAi();
        }
        
        if (DEBUGGING) {
            int player = -1;
            for (int i = 0; i < 16; i++) {
                if ((i % 4) == 0) {
                    player++;
                }
                players[player].getMenuCard().getDish(i).setAvailable(true);
            }
        }
    }

    public int getDay() {
        return day;
    }

    public Newsfeed getNewsfeed() {
        return newsfeed;
    }
    
    public Player getPlayer(int player) {
        return players[player];
    }

    public void endCurrentDay() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            for (int j = 0; j < NUMBER_OF_GUESTS; j++) {
                if (guests[j].isLikeAvailable(players[i])) {
                    players[i].addMoney(players[i].getMenuCard().getDish(guests[j].getLike()).getPrice());
                    players[i].addPoints(players[i].getMenuCard().getDish(guests[j].getLike()).getPrice());
                    players[i].getStatistics().addSoldUnits(guests[j].getLike(), day, 1);
                    players[i].getStatistics().addSales(guests[j].getLike(), day, players[i].getMenuCard().getDish(guests[j].getLike()).getPrice());
                }
            }
        }
    }

    public void startNewDay() {
        day += 1;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i].getStatistics().addColumn();
        }
    }
}