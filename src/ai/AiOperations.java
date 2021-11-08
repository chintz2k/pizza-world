package ai;

import logic.Game;
import logic.Player;

/**
 *
 * @author André Heinen
 */
public class AiOperations {
    
    public AiOperations() {
        
    }
    
    public int getWorstSellingDish(Player player, int day) {
        int dish = -1;
        int worst = -1;
        for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
            if (player.getMenuCard().getDish(i).isAvailable()) {
                if (worst == -1) {
                    dish = i;
                    worst = player.getStatistics().getSales(i, day);
                } else if (worst > player.getStatistics().getSales(i, day)) {
                    dish = i;
                    worst = player.getStatistics().getSales(i, day);
                }
            }
        }
        return dish;
    }
    
    public void exchangeRandomDish(Player player, int dish) {
        boolean dishFound = false;
        while (!dishFound) {
            int randomDish = (int) (Math.random() * Game.NUMBER_OF_DISHES);
            if (!player.getMenuCard().getDish(randomDish).isAvailable()) {
                player.getMenuCard().getDish(randomDish).setAvailable(true);
                dishFound = true;
            }
        }
        player.getMenuCard().getDish(dish).setAvailable(false);
    }
}