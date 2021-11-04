package logic;

/**
 *
 * @author André Heinen
 */
public class Guest {

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
            game.getPlayers()[player].getStatistics().addSoldUnits(dishLike, game.getDay(), 1);
            game.getPlayers()[player].getStatistics().addSales(dishLike, game.getDay(), game.getProducts().getDishes().get(dishLike).getPrice());
        }
    }
}