package de.pizzaworld.logic;

/**
 *
 * @author André Heinen
 */
public class Guest {

    private final int like;
    
    public Guest() {
        like = (int) (Math.random() * Game.NUMBER_OF_DISHES);
    }

    public int getLike() {
        return like;
    }
    
    public boolean isLikeAvailable(Player player) {
        return player.getMenuCard().getDish(like).isAvailable();
    }
}