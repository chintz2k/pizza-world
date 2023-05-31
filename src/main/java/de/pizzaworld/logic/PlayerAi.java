package de.pizzaworld.logic;

import de.pizzaworld.ai.AiOperations;

/**
 *
 * @author André Heinen
 */
public class PlayerAi extends Player {
    
    private final AiOperations aiOperations;
    
    public PlayerAi() {
        this.aiOperations = new AiOperations();
    }
    
    public void dailyAiOperations(int day) {
        aiOperations.exchangeRandomDish(this, aiOperations.getWorstSellingDish(this, day));
    }
}