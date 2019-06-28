package pizzaworld.logic;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author André Heinen
 */
public class Player implements Serializable {
    
    private static int Id = 0;
    private final int playerId;
    
    private final String name;
    
    private double money = 1000.0;
    private transient DoubleProperty moneyProperty;
    
    private int fame = 30;
    
    private final Restaurant restaurant;
    private final Statistics statistics;

    public Player(Game game) {
        this.playerId = Id;
        Id++;
        this.name = "Player" + Id;
        moneyProperty = new SimpleDoubleProperty(money);
        this.restaurant = new Restaurant();
        this.statistics = new Statistics(game);
        fame -= this.playerId;
    }
    
    public void resetDay() {
        this.getRestaurant().resetSeatTimes();
        this.getStatistics().resetDailyStatistics();
    }
    
    public void setMoney(double amount) {
        money = amount;
        moneyProperty.set(amount);
    }
    
    public void addMoney(double amount) {
        money += amount;
        moneyProperty.set(getMoney() + amount);
    }
    
    public double getMoney() {
        if (moneyProperty != null) {
            return moneyProperty.get();
        } else {
            return -0.1;
        }
    }
    
    public DoubleProperty getMoneyProperty() {
        if (moneyProperty == null) {
            moneyProperty = new SimpleDoubleProperty(0.0);
        }
        return moneyProperty;
    }

    public String getName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public int getFame() {
        return fame;
    }

    public int getId() {
        return playerId;
    }
    
    public void modFame(int amount) {
        fame += amount;
    }
}
