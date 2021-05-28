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
        aiOperations.exchangeDishRnd(playerId, aiOperations.findLeastSales(playerId));
    }
}