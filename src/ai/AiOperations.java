package ai;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class AiOperations {
    
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
                } else if (least > game.getPlayers()[player].getStatistics().getSalesYesterday(i, (game.getDay() + 1))) {
                    pizza = i;
                    least = game.getPlayers()[player].getStatistics().getSalesYesterday(i, game.getDay() + 1);
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
                pizzaFound = true;
            }
        }
        game.getProducts().getDishes().get(oldDish).setAvailable(player, false);
    }
}