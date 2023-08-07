package de.pizzaworld.gui.elements;

import de.pizzaworld.logic.Game;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author André Heinen
 */
public class TopPanel {
    
    HBox hb;

    public TopPanel(Game game) {

        hb = new HBox(new Text("Tag " + game.getDay()));
        hb.setMinSize(360.0, 20.0);
        hb.setMaxSize(360.0, 20.0);
        hb.setAlignment(Pos.CENTER);

    }
    
    public HBox getTopPanel() {
        return hb;
    }

}
