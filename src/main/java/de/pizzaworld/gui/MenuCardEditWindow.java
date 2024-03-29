package de.pizzaworld.gui;

import de.pizzaworld.gui.elements.BottomPanel;
import de.pizzaworld.gui.elements.TopPanel;
import de.pizzaworld.logic.Game;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 *
 * @author André Heinen
 */
public class MenuCardEditWindow {

    Group root;

    CheckBox[] cb;
    int activatedDishes;

    public MenuCardEditWindow(Game game, Scene scene) {

        cb = new CheckBox[Game.NUMBER_OF_DISHES];
        activatedDishes = 0;
        for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
            cb[i] = new CheckBox(game.getPlayer(0).getMenuCard().getDish(i).getName());
            cb[i].setMinSize(32.5, 32.5);
        }
        for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
            if (game.getPlayer(0).getMenuCard().getDish(i).isAvailable()) {
                activatedDishes++;
                cb[i].setSelected(true);
                if (activatedDishes >= Game.MAX_AMOUNT_OF_DISHES) {
                    blockBoxes();
                }
            }
        }
        for (int i = 0; i < cb.length; i++) {
            cb[i].selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    activatedDishes++;
                    setAvailable(game);
                    if (activatedDishes >= Game.MAX_AMOUNT_OF_DISHES) {
                        blockBoxes();
                    }
                } else {
                    activatedDishes--;
                    setAvailable(game);
                    unblockBoxes();
                }
            });
        }

        GridPane gp = new GridPane();
        gp.setMinSize(360.0, 520.0);
        gp.setMaxSize(360.0, 520.0);
        gp.add(new Text("Maximal 8 Pizzen"), 0, 0, 3, 1);
        GridPane.setHalignment(gp.getChildren().get(0), HPos.CENTER);
        for (int i = 0; i < cb.length; i++) {
            gp.add(new Text(), 0, i + 1);
            gp.add(cb[i], 1, i + 1);
            gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getPrice() + " €"), 2, i + 1);
        }
        gp.getRowConstraints().add(new RowConstraints(setCheckBoxSize(gp.getRowCount(), gp.getMinHeight())));
        gp.getColumnConstraints().add(new ColumnConstraints(50.0));
        gp.getColumnConstraints().add(new ColumnConstraints(220.0));
        gp.getColumnConstraints().add(new ColumnConstraints(90.0));

        Button button = new Button("Zurück");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);

        root = new Group(
                new VBox(
                        new TopPanel(game).getTopPanel(),
                        gp,
                        new FlowPane(button),
                        new BottomPanel(game).getBottomPanel()
                )
        );

        button.setOnAction((ActionEvent) -> {
            if (activatedDishes != 0) {
                scene.setRoot(new MenuCardWindow(game, scene).getRoot());
            }
        });

    }

    public void blockBoxes() {
        for (int i = 0; i < cb.length; i++) {
            if (!cb[i].isSelected()) {
                cb[i].setDisable(true);
            }
        }
    }

    public void unblockBoxes() {
        for (int i = 0; i < cb.length; i++) {
            cb[i].setDisable(false);
        }
    }

    public void setAvailable(Game game) {
        for (int i = 0; i < cb.length; i++) {
            if (cb[i].isSelected()) {
                game.getPlayer(0).getMenuCard().getDish(i).setAvailable(true);
            } else {
                game.getPlayer(0).getMenuCard().getDish(i).setAvailable(false);
            }
        }
    }
    
    public int setCheckBoxSize(int rows, double height) {
        int size = (int) (height / rows);
        for (int i = 0; i < cb.length; i++) {
            cb[i].setMinSize(size, size);
        }
        return size;
    }

    public Group getRoot() {
        return root;
    }

}
