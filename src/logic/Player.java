package logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author André Heinen
 */
public class Player {
    
    private final Game game;
    
    private static int Id = 0;
    private final int playerId;
    
    private final String name;
    
    private int money = 1000;
    private transient IntegerProperty moneyProperty;
    
    private int points = 0;
    private transient IntegerProperty pointsProperty;
    
    private final Statistics statistics;

    public Player(Game game) {
        this.game = game;
        this.playerId = Id;
        Id++;
        this.name = "Player " + this.playerId;
        moneyProperty = new SimpleIntegerProperty(money);
        pointsProperty = new SimpleIntegerProperty(points);
        this.statistics = new Statistics(game);
    }
    
    public int getPlayerId() {
        return playerId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void endDay() {
        for (int i = 0; i < Game.GUESTCOUNT; i++) {
            game.getGuestList().getGuests().get(i).buy(playerId);
        }
    }
    
    public void addMoney(int amount) {
        money += amount;
        moneyProperty.set(getMoney() + amount);
    }
    
    public int getMoney() {
        if (moneyProperty != null) {
            return moneyProperty.get();
        } else {
            return -1;
        }
    }
    
    public IntegerProperty getMoneyProperty() {
        if (moneyProperty == null) {
            moneyProperty = new SimpleIntegerProperty(0);
        }
        return moneyProperty;
    }

    public void addPoints(int amount) {
        points += amount;
        pointsProperty.set(getPoints() + amount);
    }
    
    public int getPoints() {
        if (pointsProperty != null) {
            return pointsProperty.get();
        } else {
            return -1;
        }
    }
    
    public IntegerProperty getPointsProperty() {
        if (pointsProperty == null) {
            pointsProperty = new SimpleIntegerProperty(0);
        }
        return pointsProperty;
    }

    public Statistics getStatistics() {
        return statistics;
    }
}