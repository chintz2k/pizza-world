package pizzaworld.dishes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author André Heinen
 */
public class Products implements Serializable {
    
    private final ArrayList<Dish> dishes;
    
    public Products() {
        dishes = new ArrayList<>();
        
        dishes.add(new Dish("Pizza Marinara", 1.0));
        dishes.add(new Dish("Pizza Margherita", 1.0));
        dishes.add(new Dish("Pizza Napoletana", 1.0));
        dishes.add(new Dish("Pizza Salami", 1.0));
        dishes.add(new Dish("Pizza Prosciutto", 1.0));
        dishes.add(new Dish("Pizza Funghi", 1.0));
        dishes.add(new Dish("Pizza Tonno", 1.0));
        dishes.add(new Dish("Pizza Spinaci", 1.0));
        dishes.add(new Dish("Pizza Caprese", 1.0));
        dishes.add(new Dish("Pizza Capricciosa", 1.0));
        dishes.add(new Dish("Pizza Hawaii", 1.0));
        dishes.add(new Dish("Pizza Contadina", 1.0));
        dishes.add(new Dish("Pizza Gorgonzola", 1.0));
        dishes.add(new Dish("Pizza Quattro Stagioni", 1.0));
        dishes.add(new Dish("Pizza Calzone", 1.0));
        dishes.add(new Dish("Pizza Frutti di Mare", 1.0));
        
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

}
