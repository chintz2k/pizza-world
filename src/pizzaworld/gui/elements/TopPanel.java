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
    private final int player;

    public TopPanel(Game game) {
        this.game = game;
        this.player = 0;
    }
    
    public TopPanel(Game game, int player) {
        this.game = game;
        this.player = player;
    }
    
    public Parent showElement() {
        
        Text seats = new Text("Belegte Plätze: " + game.getPlayers()[player].getRestaurant().getUsedSeats() + "/" + game.getPlayers()[player].getRestaurant().getMaxSeats());
        game.getPlayers()[player].getRestaurant().getUsedSeatsProperty().addListener((observable) -> {
            seats.setText("Belegte Plätze: " + game.getPlayers()[player].getRestaurant().getUsedSeats() + "/" + game.getPlayers()[player].getRestaurant().getMaxSeats());
        });
        
        HBox hb = new HBox(seats);
        hb.setMinSize(360, 20);
        hb.setAlignment(Pos.CENTER);
        
        return hb;
    }
    
}
