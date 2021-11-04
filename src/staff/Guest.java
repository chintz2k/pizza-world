package staff;

import java.io.Serializable;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class Guest implements Serializable {

    private final Game game;

    private final int dishLike;
    
    public Guest(Game game) {
        this.game = game;
        dishLike = (int) (Math.random() * game.getProducts().getDishes().size());
    }
    
    public void buy(int player) {
        if (game.getProducts().getDishes().get(dishLike).isAvailable(player)) {
            game.getPlayers()[player].addMoney(game.getProducts().getDishes().get(dishLike).getPrice());
            game.getPlayers()[player].addPoints(game.getProducts().getDishes().get(dishLike).getPrice());
            game.getPlayers()[player].getStatistics().incSoldUnits(game.getDay(), dishLike);
            game.getPlayers()[player].getStatistics().incSales(game.getDay(), dishLike, game.getProducts().getDishes().get(dishLike).getPrice());
        }
    }
}