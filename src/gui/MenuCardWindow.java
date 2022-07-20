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
public class MenuCardWindow {

    private GridPane content;
    private Button[] buttons = new Button[2];

    public MenuCardWindow(Game game, Window window) {

        // Content
        content = new GridPane();
        if (!game.getPlayer(0).getMenuCard().isMenuCardEmpty()) {
            content.add(new Text("Speisenkarte"), 0, 0, 2, 1);
            GridPane.setHalignment(content.getChildren().get(0), HPos.CENTER);
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                if (game.getPlayer(0).getMenuCard().getDish(i).isAvailable()) {
                    content.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, content.getRowCount());
                    content.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getPrice() + " €"), 1, content.getRowCount() - 1);
                }
            }
            content.getColumnConstraints().add(new ColumnConstraints(GuiConfig.WIDTH * 0.6));
            content.getColumnConstraints().add(new ColumnConstraints(GuiConfig.WIDTH * 0.2));
            RowConstraints rc;
            if (Game.DEBUGGING) {
                rc = new RowConstraints(GuiConfig.CONTENT_HEIGHT_1_BUTTONSROW_DEBUGGING / content.getRowCount());
            } else {
                rc = new RowConstraints(GuiConfig.CONTENT_HEIGHT_1_BUTTONROW / content.getRowCount());
            }
            for (int i = 0; i < content.getRowCount(); i++) {
                content.getRowConstraints().add(rc);
            }
        } else {
            content.add(new Text("Deine Speisenkarte ist leer"), 0, 0, 2, 1);
        }

        // Buttons
        buttons[0] = new Button("Bearbeiten");
        buttons[1] = new Button("Zurück");

        buttons[0].setOnAction((ActionEvent) -> {
            MenuCardEditWindow w = new MenuCardEditWindow(game, window);
            window.update(w.getContent(), w.getButtons());
        });

        buttons[1].setOnAction((ActionEvent) -> {
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
