package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class PointsWindow {

    private GridPane content;
    private Button[] buttons = new Button[1];

    public PointsWindow(Game game, Window window) {
        
        // Content
        content = new GridPane();
        content.add(new Text("Punkteliste"), 0, 0, 2, 1);
        GridPane.setHalignment(content.getChildren().get(0), HPos.CENTER);
        for (int i = 0; i < Game.NUMBER_OF_PLAYERS; i++) {
            content.add(new Text(game.getPlayer(i).getName()), 0, i + 1);
            content.add(new Text(String.valueOf(game.getPlayer(i).getPoints())), 1, i + 1);
        }
        content.getColumnConstraints().add(new ColumnConstraints(GuiConfig.WIDTH / (content.getColumnCount() + 1)));
        RowConstraints rc;
        if (Game.DEBUGGING) {
            rc = new RowConstraints(GuiConfig.CONTENT_HEIGHT_1_BUTTONSROW_DEBUGGING / content.getRowCount());
        } else {
            rc = new RowConstraints(GuiConfig.CONTENT_HEIGHT_1_BUTTONROW / content.getRowCount());
        }
        for (int i = 0; i < content.getRowCount(); i++) {
            content.getRowConstraints().add(rc);
        }

        // Buttons
        buttons[0] = new Button("OK");
        
        buttons[0].setOnAction((ActionEvent) -> {
            game.startNewDay();
            MainWindow w = new MainWindow(game, window);
            window.update(w.getContent(), w.getButtons());
        });
        
        // DEBUGGING
        content.setGridLinesVisible(true);
        content.setAlignment(Pos.CENTER);
    }
    
    public Region getContent() {
        return content;
    }

    public Button[] getButtons() {
        return buttons;
    }
    
}
