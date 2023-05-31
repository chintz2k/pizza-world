package de.pizzaworld.gui.elements;

import javafx.scene.text.Text;

import de.pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class BottomPanel {
    
    Text text;
    
    public BottomPanel(Game game) {

        Text money = new Text(game.getPlayer(0).getMoney() + " €");
        game.getPlayer(0).getMoneyProperty().addListener((observable) -> {
            money.setText(game.getPlayer(0).getMoney() + " €");
        });

    }

    public BottomPanel(Game game, int player) {

        Text money = new Text(game.getPlayer(player).getMoney() + " €");
        game.getPlayer(player).getMoneyProperty().addListener((observable) -> {
            money.setText(game.getPlayer(player).getMoney() + " €");
        });

    }

    public Text getText() {
        return text;
    }
    
}
