package de.pizzaworld.gui;

import de.pizzaworld.gui.elements.BottomPanel;
import de.pizzaworld.gui.elements.TopPanel;
import de.pizzaworld.logic.Game;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 *
 * @author André Heinen
 */
public class MenuCardWindow {

    Group root;

    public MenuCardWindow(Game game, Scene scene) {

        GridPane gp = new GridPane();
        gp.setMinSize(360.0, 520.0);
        gp.setMaxSize(360.0, 520.0);
        if (!game.getPlayer(0).getMenuCard().isMenuCardEmpty()) {
            gp.add(new Text("Speisenkarte"), 0, 0, 2, 1);
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                if (game.getPlayer(0).getMenuCard().getDish(i).isAvailable()) {
                    gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, gp.getRowCount());
                    gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getPrice() + " €"), 1, gp.getRowCount() - 1);
                }
            }
            gp.getColumnConstraints().add(new ColumnConstraints(360.0 * 0.6));
            gp.getColumnConstraints().add(new ColumnConstraints(360.0 * 0.2));
            RowConstraints rc = new RowConstraints((640.0 - 100.0) / (Game.MAX_AMOUNT_OF_DISHES + 1));
            for (int i = 0; i < gp.getRowCount(); i++) {
                gp.getRowConstraints().addAll(rc);
            }
        } else {
            gp.add(new Text("Deine Speisenkarte ist leer"), 0, 0, 2, 1);
        }
        GridPane.setHalignment(gp.getChildren().get(0), HPos.CENTER);
        for (int i = 1; i < gp.getChildren().size(); ++i) {
            if (i % 2 == 0) {
                GridPane.setHalignment(gp.getChildren().get(i), HPos.RIGHT);
            }
        }
        gp.setAlignment(Pos.CENTER);

        Button[] buttons = {
                new Button("Bearbeiten"),
                new Button("Zurück")
        };
        for (Button button : buttons) {
            button.setMinSize(180.0, 80.0);
            button.setMaxSize(180.0, 80.0);
        }

        root = new Group(
                new VBox(
                        new TopPanel(game).getTopPanel(),
                        gp,
                        new FlowPane(buttons),
                        new BottomPanel(game).getBottomPanel()
                )
        );

        buttons[0].setOnAction((ActionEvent) -> {
            scene.setRoot(new MenuCardEditWindow(game, scene).getRoot());
        });

        buttons[1].setOnAction((ActionEvent) -> {
            scene.setRoot(new MainWindow(game, scene).getRoot());
        });

    }
    
    public Group getRoot() {
        return root;
    }

}
