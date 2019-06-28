package pizzaworld.dishes;

import java.io.Serializable;
import pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class Dish implements Serializable {
    
    private final String name;
    private final double price;
    
    private final boolean[] available;
    
    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        this.available = new boolean[Game.PLAYERCOUNT];
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable(int player) {
        return available[player];
    }

    public void setAvailable(int player, boolean available) {
        this.available[player] = available;
    }
    
}
