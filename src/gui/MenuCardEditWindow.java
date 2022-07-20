package gui;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class MenuCardEditWindow {
    
    private GridPane content;
    private Button[] buttons = new Button[1];

    private CheckBox[] cb;
    private int activatedDishes;

    public MenuCardEditWindow(Game game, Window window) {

        // Content
        cb = new CheckBox[Game.NUMBER_OF_DISHES];
        activatedDishes = 0;
        for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
            cb[i] = new CheckBox(game.getPlayer(0).getMenuCard().getDish(i).getName());
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

        content = new GridPane();
        content.add(new Text("Maximal " + Game.MAX_AMOUNT_OF_DISHES + " Pizzen"), 0, 0, 2, 1);
        GridPane.setHalignment(content.getChildren().get(0), HPos.CENTER);
        for (int i = 0; i < cb.length; i++) {
            content.add(cb[i], 0, i + 1);
            content.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getPrice() + " €"), 1, i + 1);
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
        
        // Buttons
        buttons[0] = new Button("Zurück");

        buttons[0].setOnAction((ActionEvent) -> {
            if (activatedDishes != 0) {
                MenuCardWindow w = new MenuCardWindow(game, window);
                window.update(w.getContent(), w.getButtons());
            } else {
                MenuCardWindow w = new MenuCardWindow(game, window);
                window.update(w.getContent(), w.getButtons()); // TODO Leere Speisenkarte
            }
        });

        // DEBUGGING
        content.setGridLinesVisible(true);
        content.setAlignment(Pos.CENTER);
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
    
    public Region getContent() {
        return content;
    }

    public Button[] getButtons() {
        return buttons;
    }
    
}
