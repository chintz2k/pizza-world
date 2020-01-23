package pizzaworld.gui.elements;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class TopPanel extends Stage {
    
    private final Game game;

    public TopPanel(Game game) {
        this.game = game;
    }
    
    public Parent showElement() {
        HBox hb = new HBox(new Text(String.valueOf("Tag " + game.getDay())));
        hb.setMinSize(360, 20);
        hb.setAlignment(Pos.CENTER);
        
        return hb;
    }
}