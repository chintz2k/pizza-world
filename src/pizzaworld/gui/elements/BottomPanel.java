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
public class BottomPanel extends Stage {
    
    private final Game game;
    private final int player;

    public BottomPanel(Game game) {
        this.game = game;
        this.player = 0;
    }
    
    public BottomPanel(Game game, int player) {
        this.game = game;
        this.player = player;
    }
    
    public Parent showElement() {
        
        Text time = new Text(game.getClock().getTime());
        game.getClock().getTimeProperty().addListener((observable) -> {
            time.setText(game.getClock().getTime());
        });
        
        Text money = new Text(String.format("%.2f", game.getPlayers()[player].getMoney()) + " €");
        game.getPlayers()[player].getMoneyProperty().addListener((observable) -> {
            money.setText(String.format("%.2f", game.getPlayers()[player].getMoney()) + " €");
        });
        
        HBox hbleft = new HBox(time);
        hbleft.setMinSize(180, 20);
        hbleft.setAlignment(Pos.CENTER_LEFT);
        
        HBox hbright = new HBox(money);
        hbright.setMinSize(180, 20);
        hbright.setAlignment(Pos.CENTER_RIGHT);
        
        HBox hb = new HBox(hbleft, hbright);
        hb.setMinSize(360, 20);
        hb.setMaxSize(360, 20);
        
        return hb;
    }
    
}
