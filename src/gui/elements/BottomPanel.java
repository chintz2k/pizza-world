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
    private final int player;// TODO delete for release
    
    public BottomPanel(Game game) {
        this.game = game;
        this.player = 0;
    }

    public BottomPanel(Game game, int player) {
        this.game = game;
        this.player = player;
    }

    public Parent showElement() {
        
        Text money = new Text(game.getPlayer(player).getMoney() + " €");
        game.getPlayer(player).getMoneyProperty().addListener((observable) -> {
            money.setText(game.getPlayer(player).getMoney() + " €");
        });
        
        HBox hb = new HBox(money);
        hb.setMinSize(180, 20);
        hb.setAlignment(Pos.CENTER);
        
        hb.setMinSize(360, 20);
        hb.setMaxSize(360, 20);
        
        return hb;
    }
}