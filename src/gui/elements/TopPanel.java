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
public class TopPanel {
    
    private Text text;
    private VBox vbox;

    public TopPanel(Game game) {
        text = new Text("Tag " + game.getDay());
        game.getDayProperty().addListener((observable) -> {
            text.setText("Tag " + game.getDay());
        });
        vbox = new VBox(text);

        // DEBUGGING
        vbox.setPrefHeight(GuiConfig.PANEL_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    public VBox getVBox() {
        return vbox;
    }

}
