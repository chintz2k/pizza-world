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
public class MenuCardWindow extends Stage {
    
    private final Game game;
    private final Stage stage;

    public MenuCardWindow(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }
    
    public Parent showElement() {
        
        GridPane gp = new GridPane();
        gp.setMinSize(360.0, 520.0);
        gp.setMaxSize(360.0, 520.0);
        gp.add(new Text("Speisenkarte"), 0, 0, 2, 1);
        GridPane.setHalignment(gp.getChildren().get(0), HPos.CENTER);
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            if (game.getProducts().getDishes().get(i).isAvailable(0)) {
                gp.add(new Text(game.getProducts().getDishes().get(i).getName()), 0, gp.getRowCount());
                gp.add(new Text(game.getProducts().getDishes().get(i).getPrice() + " €"), 1, gp.getRowCount() - 1);
            }
        }
        gp.getColumnConstraints().add(new ColumnConstraints(250.0));
        RowConstraints rc = new RowConstraints(50.0);
        for (int i = 0; i < gp.getRowCount(); i++) {
            gp.getRowConstraints().addAll(rc);
        }
        gp.setAlignment(Pos.CENTER);
        gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Button[] buttons = {
            new Button("Bearbeiten"),
            new Button("Zurück")
        };

        for (Button button : buttons) {
            button.setMinSize(180.0, 80.0);
            button.setMaxSize(180.0, 80.0);
        }

        FlowPane fp = new FlowPane(buttons);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);
        
        buttons[0].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new MenuCardEditWindow(game, stage).showElement());
        });

        buttons[1].setOnAction((ActionEvent) -> {
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