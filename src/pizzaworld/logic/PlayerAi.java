package pizzaworld.logic;

import java.io.Serializable;
import pizzaworld.ai.AiOperations;

/**
 *
 * @author André Heinen
 */
public class PlayerAi extends Player implements Serializable {
    
    private final Game game;
    
    private final int playerId;
    
    private final AiOperations aiOperations;
    
    public PlayerAi(Game game) {
        super(game);
        this.game = super.getGame();
        this.playerId = super.getPlayerId();
        this.aiOperations = new AiOperations(this.game);
    }
    
    @Override
    public void endDay() {
        for (int i = 0; i < Game.GUESTCOUNT; i++) {
            game.getGuestList().getGuests().get(i).buy(playerId);
        }
        changeMenu();
    }
    
    public void changeMenu() {
        System.out.println("Spieler " + playerId + ":");
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            if (game.getProducts().getDishes().get(i).isAvailable(playerId)) {
                System.out.println(game.getProducts().getDishes().get(i).getName() + "/" + game.getPlayers()[playerId].getStatistics().getSalesYesterday(i, (game.getDay() + 1)));
            }
        }
        int least = aiOperations.findLeastSales(playerId);
        System.out.println("Billigste! " + game.getProducts().getDishes().get(least).getName() + " wurde " + game.getPlayers()[playerId].getStatistics().getSalesYesterday(least, (game.getDay() + 1)) + "x verkauft.");
        System.out.println(game.getProducts().getDishes().get(least).getName() + " fliegt raus!");
        aiOperations.exchangeDishRnd(playerId, least);
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            if (game.getProducts().getDishes().get(i).isAvailable(playerId)) {
                System.out.println(game.getProducts().getDishes().get(i).getName() + "/" + game.getPlayers()[playerId].getStatistics().getSalesYesterday(i, (game.getDay() + 1)));
            }
        }
    }
}