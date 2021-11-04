package dishes;

import java.util.ArrayList;

/**
 *
 * @author André Heinen
 */
public class Products {
    
    private final ArrayList<Dish> dishes;
    
    public Products() {
        dishes = new ArrayList<>();
        
        dishes.add(new Dish("Pizza Marinara", 1));
        dishes.add(new Dish("Pizza Margherita", 1));
        dishes.add(new Dish("Pizza Napoletana", 1));
        dishes.add(new Dish("Pizza Salami", 1));
        dishes.add(new Dish("Pizza Prosciutto", 1));
        dishes.add(new Dish("Pizza Funghi", 1));
        dishes.add(new Dish("Pizza Tonno", 1));
        dishes.add(new Dish("Pizza Spinaci", 1));
        dishes.add(new Dish("Pizza Caprese", 1));
        dishes.add(new Dish("Pizza Capricciosa", 1));
        dishes.add(new Dish("Pizza Hawaii", 1));
        dishes.add(new Dish("Pizza Contadina", 1));
        dishes.add(new Dish("Pizza Gorgonzola", 1));
        dishes.add(new Dish("Pizza Quattro Stagioni", 1));
        dishes.add(new Dish("Pizza Calzone", 1));
        dishes.add(new Dish("Pizza Frutti di Mare", 1));
        
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }
}