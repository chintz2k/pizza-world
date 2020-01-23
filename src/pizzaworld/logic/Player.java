package pizzaworld.logic;

import java.io.Serializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author André Heinen
 */
public class Player implements Serializable {
    
    private final Game game;
    
    private static int Id = 0;
    private final int playerId;
    
    private double money = 1000.0;
    private transient DoubleProperty moneyProperty;
    
    private final Statistics statistics;

    public Player(Game game) {
        this.game = game;
        this.playerId = Id;
        Id++;
        moneyProperty = new SimpleDoubleProperty(money);
        this.statistics = new Statistics(game);
    }
    
    public void endDay() {
        for (int i = 0; i < Game.GUESTCOUNT; i++) {
            game.getGuestList().getGuests().get(i).buy(playerId);
        }
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

    public Statistics getStatistics() {
        return statistics;
    }
}
