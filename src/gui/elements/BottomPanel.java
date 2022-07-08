package gui.elements;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class BottomPanel {
    
    String text;
    
    public BottomPanel(Game game) {

        text = game.getPlayer(0).getMoney() + " €";
        game.getPlayer(0).getMoneyProperty().addListener((observable) -> {
            text = game.getPlayer(0).getMoney() + " €";
        });

    }

    public BottomPanel(Game game, int player) {

        text = game.getPlayer(player).getMoney() + " €";
        game.getPlayer(player).getMoneyProperty().addListener((observable) -> {
            text = game.getPlayer(player).getMoney() + " €";
        });

    }

    public String getText() {
        return text;
    }
    
}
