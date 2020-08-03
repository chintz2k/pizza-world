package pizzaworld.logic;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author André Heinen
 */
public class Player implements Serializable {
    
    private final Game game;
    
    private static int Id = 0;
    private final int playerId;
    
    private int money = 1000;
    private transient IntegerProperty moneyProperty;
    
    private final Statistics statistics;

    public Player(Game game) {
        this.game = game;
        this.playerId = Id;
        Id++;
        moneyProperty = new SimpleIntegerProperty(money);
        this.statistics = new Statistics(game);
    }
    
    public int getPlayerId() {
        return playerId;
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

    public Statistics getStatistics() {
        return statistics;
    }
}
