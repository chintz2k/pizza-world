package de.pizzaworld.logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import de.pizzaworld.dishes.MenuCard;

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
    
    private final MenuCard menuCard;
    private final Statistics statistics;

    public Player() {
        this.playerId = ID;
        ID++;
        this.name = "Player " + this.playerId;
        moneyProperty = new SimpleIntegerProperty(1000);
        pointsProperty = new SimpleIntegerProperty(0);
        this.menuCard = new MenuCard();
        this.statistics = new Statistics();
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

    public MenuCard getMenuCard() {
        return menuCard;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public String getName() {
        return this.name;
    }
}