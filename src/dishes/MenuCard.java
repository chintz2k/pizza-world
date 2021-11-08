package dishes;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class MenuCard {
    
    private final Dish[] menuCard;
    
    public MenuCard() {

        menuCard = new Dish[Game.NUMBER_OF_DISHES];
        
        menuCard[0] = new Dish("Pizza Marinara", 1);
        menuCard[1] = new Dish("Pizza Margherita", 1);
        menuCard[2] = new Dish("Pizza Napoletana", 1);
        menuCard[3] = new Dish("Pizza Salami", 1);
        menuCard[4] = new Dish("Pizza Prosciutto", 1);
        menuCard[5] = new Dish("Pizza Funghi", 1);
        menuCard[6] = new Dish("Pizza Tonno", 1);
        menuCard[7] = new Dish("Pizza Spinaci", 1);
        menuCard[8] = new Dish("Pizza Caprese", 1);
        menuCard[9] = new Dish("Pizza Capricciosa", 1);
        menuCard[10] = new Dish("Pizza Hawaii", 1);
        menuCard[11] = new Dish("Pizza Contadina", 1);
        menuCard[12] = new Dish("Pizza Gorgonzola", 1);
        menuCard[13] = new Dish("Pizza Quattro Stagioni", 1);
        menuCard[14] = new Dish("Pizza Calzone", 1);
        menuCard[15] = new Dish("Pizza Frutti di Mare", 1);

    }

    public Dish getDish(int dish) {
        return menuCard[dish];
    }

    public boolean isMenuCardEmpty() {
        for (int i = 0; i < menuCard.length; i++) {
            if (menuCard[i].isAvailable()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMenuCardFull() {
        int counter = 0;
        for (int i = 0; i < menuCard.length; i++) {
            if (menuCard[i].isAvailable()) {
                counter++;
            }
        }
        if (counter == Game.MAX_AMOUNT_OF_DISHES) {
            return true;
        }
        return false;
    }
}