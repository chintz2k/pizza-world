package pizzaworld.ai;

import java.io.Serializable;
import pizzaworld.logic.Game;

/**
 *
 * @author André
 */
public class AiOperations implements Serializable {
    
    private final Game game;

    public AiOperations(Game game) {
        this.game = game;
    }
    
    public int findLeastSales(int player) {
        int pizza = -1;
        int least = -1;
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            if (game.getProducts().getDishes().get(i).isAvailable(player)) {
                if (least == -1) {
                    pizza = i;
                    least = game.getPlayers()[player].getStatistics().getSalesYesterday(i, game.getDay() + 1);
                    System.out.println("Anfang!  " + game.getProducts().getDishes().get(i).getName() + " wurde " + game.getPlayers()[player].getStatistics().getSalesYesterday(i, (game.getDay() + 1)) + "x verkauft.");
                } else if (least > game.getPlayers()[player].getStatistics().getSalesYesterday(i, (game.getDay() + 1))) {
                    pizza = i;
                    least = game.getPlayers()[player].getStatistics().getSalesYesterday(i, game.getDay() + 1);
                    System.out.println("Weniger! " + game.getProducts().getDishes().get(i).getName() + " wurde " + game.getPlayers()[player].getStatistics().getSalesYesterday(i, (game.getDay() + 1)) + "x verkauft.");
                }
            }
        }
        return pizza;
    }
    
    public void exchangeDishRnd(int player, int oldDish) {
        boolean pizzaFound = false;
        while (!pizzaFound) {
            int random = (int) (Math.random() * game.getProducts().getDishes().size());
            if (game.getProducts().getDishes().get(random).isAvailable(player) == false) {
                game.getProducts().getDishes().get(random).setAvailable(player, true);
                System.out.println(game.getProducts().getDishes().get(random).getName() + " kommt rein!");
                pizzaFound = true;
            }
        }
        game.getProducts().getDishes().get(oldDish).setAvailable(player, false);
    }
}