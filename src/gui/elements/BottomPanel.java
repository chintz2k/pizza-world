package gui.elements;

import gui.GuiConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class BottomPanel {
    
    private Text text;
    private VBox vbox;
    
    public BottomPanel(Game game) {
        text = new Text(game.getPlayer(0).getMoney() + " €");
        vbox = new VBox(text);
        game.getPlayer(0).getMoneyProperty().addListener((observable) -> {
            text.setText(game.getPlayer(0).getMoney() + " €");
        });

        // DEBUGGING
        vbox.setPrefHeight(GuiConfig.PANEL_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public BottomPanel(Game game, int player) {
        text = new Text(game.getPlayer(player).getMoney() + " €");
        vbox = new VBox(text);
        game.getPlayer(player).getMoneyProperty().addListener((observable) -> {
            text.setText(game.getPlayer(player).getMoney() + " €");
        });

        // DEBUGGING
        vbox.setPrefHeight(GuiConfig.PANEL_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public VBox getVbox() {
        return vbox;
    }
    
}
