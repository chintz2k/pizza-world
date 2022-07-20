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
        
    private String string;
    private Text text;
    private VBox vbox;

    public TopPanel(Game game) {
        string = "Tag " + game.getDay();
        text = new Text(string);
        vbox = new VBox(text);

        // DEBUGGING
        vbox.setPrefHeight(GuiConfig.PANEL_HEIGHT);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    
    public String getString() {
        return string;
    }

    public Text getText() {
        return text;
    }

    public VBox getVBox() {
        return vbox;
    }

}
