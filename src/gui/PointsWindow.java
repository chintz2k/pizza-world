package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import gui.elements.BottomPanel;
import gui.elements.TopPanel;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class PointsWindow extends Stage {
    
    private final Game game;
    private final Stage stage;
    
    public PointsWindow(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }
    
    public Parent showWindow() {
        
        GridPane gp = new GridPane();
        gp.setMinSize(360.0, 520.0);
        gp.setMaxSize(360.0, 520.0);
        gp.add(new Text("Punkteliste"), 0, 0, 2, 1);
        for (int i = 0; i < Game.PLAYERCOUNT; i++) {
            gp.add(new Text(game.getPlayers()[i].getName()), 0, i + 1);
            gp.add(new Text(String.valueOf(game.getPlayers()[i].getPoints())), 1, i + 1);
        }
        
        GridPane.setHalignment(gp.getChildren().get(0), HPos.CENTER);
        
        Button button = new Button("OK");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);
        
        FlowPane fp = new FlowPane(button);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);
        
        gp.setAlignment(Pos.CENTER);
        gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        for (int i = 2; i <= gp.getChildren().size() - 1; i += 2) {
            GridPane.setHalignment(gp.getChildren().get(i), HPos.RIGHT);
        }
        
        ColumnConstraints cc = new ColumnConstraints(130.0);
        gp.getColumnConstraints().addAll(cc, cc);
        RowConstraints rc = new RowConstraints(40.0);
        for (int i = 0; i < gp.getRowCount(); i++) {
            gp.getRowConstraints().add(rc);
        }
        
        button.setOnAction((ActionEvent) -> {
            game.startNewDay();
            stage.getScene().setRoot(new MainWindow(game, stage).showElement());
        });
        
        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, new TopPanel(game).showElement());
        gpRoot.addRow(1, gp);
        gpRoot.addRow(2, fp);
        gpRoot.addRow(3, new BottomPanel(game).showElement());
        
        gpRoot.setGridLinesVisible(true);
        
        return gpRoot;
    }
}