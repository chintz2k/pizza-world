package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class PointsWindow {

    Group root;
    
    public PointsWindow(GameWindow window, Game game) {
        
        GridPane gp = new GridPane();
        gp.setMinSize(360.0, 520.0);
        gp.add(new Text("Punkteliste"), 0, 0, 2, 1);
        for (int i = 0; i < Game.NUMBER_OF_PLAYERS; i++) {
            gp.add(new Text(game.getPlayer(i).getName()), 0, i + 1);
            gp.add(new Text(String.valueOf(game.getPlayer(i).getPoints())), 1, i + 1);
        }
        gp.setAlignment(Pos.CENTER);
        GridPane.setHalignment(gp.getChildren().get(0), HPos.CENTER);
        ColumnConstraints cc = new ColumnConstraints(130.0);
        gp.getColumnConstraints().addAll(cc, cc);
        RowConstraints rc = new RowConstraints(40.0);
        for (int i = 0; i < gp.getRowCount(); i++) {
            gp.getRowConstraints().add(rc);
        }
        for (int i = 2; i <= gp.getChildren().size() - 1; i += 2) {
            GridPane.setHalignment(gp.getChildren().get(i), HPos.RIGHT);
        }
        
        Button button = new Button("OK");
        button.setMinSize(360.0, 80.0);
        
        FlowPane fp = new FlowPane(button);
        fp.setMinSize(360.0, 80.0);
        
        button.setOnAction((ActionEvent) -> {
            game.startNewDay();
            //stage.getScene().setRoot(new MainWindow(stage, game).get());
            //window.change(new MainWindow(window, game).getRoot());
        });
        
        root = new Group(new VBox(gp, fp));

    }
    
    public Group getRoot() {
        return root;
    }

}
