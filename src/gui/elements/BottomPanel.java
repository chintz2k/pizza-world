package gui.elements;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Game;

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
        
        Text money = new Text(game.getPlayers()[player].getMoney() + " €");
        game.getPlayers()[player].getMoneyProperty().addListener((observable) -> {
            money.setText(game.getPlayers()[player].getMoney() + " €");
        });
        
        HBox hb = new HBox(money);
        hb.setMinSize(180, 20);
        hb.setAlignment(Pos.CENTER);
        
        hb.setMinSize(360, 20);
        hb.setMaxSize(360, 20);
        
        return hb;
    }
    
}