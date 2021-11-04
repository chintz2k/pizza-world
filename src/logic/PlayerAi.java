package logic;

import ai.AiOperations;

/**
 *
 * @author André Heinen
 */
public class PlayerAi extends Player {
    
    private final int playerId;

    private final AiOperations aiOperations;
    
    public PlayerAi(Game game) {
        super(game);
        this.playerId = super.getPlayerId();
        this.aiOperations = new AiOperations(game);
    }
    
    public void dailyOperations() {
        aiOperations.exchangeDishRnd(playerId, aiOperations.findLeastSales(playerId));
    }
}