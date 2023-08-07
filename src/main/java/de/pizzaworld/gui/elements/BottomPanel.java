package de.pizzaworld.gui.elements;

import de.pizzaworld.logic.Game;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author André Heinen
 */
public class BottomPanel {

    HBox hb;
    
    public BottomPanel(Game game) {

        Text money = new Text("Player 1 : " + game.getPlayer(0).getMoney() + " €");
        game.getPlayer(0).getMoneyProperty().addListener((observable) -> {
            money.setText("Player 1 : " + game.getPlayer(0).getMoney() + " €");
        });
        hb = new HBox(money);
        hb.setMinSize(360.0, 20.0);
        hb.setMaxSize(360.0, 20.0);
        hb.setAlignment(Pos.CENTER);

    }

    public BottomPanel(Game game, int player) {

        Text money = new Text("Player " + (player + 1) +  " : " + game.getPlayer(player).getMoney() + " €");
        game.getPlayer(player).getMoneyProperty().addListener((observable) -> {
            money.setText("Player " + (player + 1) +  " : " + game.getPlayer(player).getMoney() + " €");
        });
        hb = new HBox(money);
        hb.setMinSize(360.0, 20.0);
        hb.setMaxSize(360.0, 20.0);
        hb.setAlignment(Pos.CENTER);

    }

    public HBox getBottomPanel() {
        return hb;
    }
    
}
