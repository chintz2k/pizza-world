package logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author André Heinen
 */
public class Player {
    
    private static int ID = 0;
    private final int playerId;
    
    private final String name;
    
    private IntegerProperty moneyProperty;
    private IntegerProperty pointsProperty;
    
    private final Statistics statistics;

    public Player(Game game) {
        this.playerId = ID;
        ID++;
        this.name = "Player " + this.playerId;
        moneyProperty = new SimpleIntegerProperty(1000);
        pointsProperty = new SimpleIntegerProperty(0);
        this.statistics = new Statistics(game);
    }
    
    public void addMoney(int amount) {
        moneyProperty.set(getMoney() + amount);
    }
    
    public void addPoints(int amount) {
        pointsProperty.set(getPoints() + amount);
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
    
    public int getPlayerId() {
        return playerId;
    }
    
    public String getName() {
        return this.name;
    }
}