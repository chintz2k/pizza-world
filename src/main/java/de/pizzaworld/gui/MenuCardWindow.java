package de.pizzaworld.gui;

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

import de.pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class MenuCardWindow {

    Group root;

    VBox container;

    public MenuCardWindow(GameWindow window, Game game) {
        
        Button[] buttons = {
            new Button("Bearbeiten"),
            new Button("Zurück")
        };

        for (Button button : buttons) {
            button.setMinSize(180.0, 80.0);
        }

        FlowPane fp = new FlowPane(buttons);

        GridPane gp = new GridPane();
        if (!game.getPlayer(0).getMenuCard().isMenuCardEmpty()) {
            gp.add(new Text("Speisenkarte"), 0, 0, 2, 1);
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                if (game.getPlayer(0).getMenuCard().getDish(i).isAvailable()) {
                    gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, gp.getRowCount());
                    gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getPrice() + " €"), 1, gp.getRowCount() - 1);
                }
            }
            //gp.getColumnConstraints().add(new ColumnConstraints(window.getWidth() * 0.6));
            //gp.getColumnConstraints().add(new ColumnConstraints(window.getWidth() * 0.2));
            //RowConstraints rc = new RowConstraints((window.getMainWindowHeight() - buttons[0].getMinHeight()) / (Game.MAX_AMOUNT_OF_DISHES + 1));
            for (int i = 0; i < gp.getRowCount(); i++) {
                //gp.getRowConstraints().addAll(rc);
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
        
        container = new VBox(gp, fp);

        root = new Group(container);

        buttons[0].setOnAction((ActionEvent) -> {
            //window.change(new MenuCardEditWindow(window, game).getRoot());
        });

        buttons[1].setOnAction((ActionEvent) -> {
            //window.change(new MainWindow(window, game).getRoot());
        });

    }
    
    public Group getRoot() {
        return root;
    }

}
