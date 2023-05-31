package de.pizzaworld.dishes;

/**
 *
 * @author André Heinen
 */
public class Dish {
    
    private final String name;
    private final int price;

    private boolean availability;
    
    public Dish(String name, int price) {
        this.name = name;
        this.price = price;

        this.availability = false;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    
    public boolean isAvailable() {
        return availability;
    }

    public void setAvailable(boolean available) {
        this.availability = available;
    }
}