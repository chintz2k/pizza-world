package gui;

import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class MenuCardEditWindow extends Stage {

    private final Stage stage;
    
    private final Game game;

    private int activatedDishes;

    private final CheckBox[] cb;

    public MenuCardEditWindow(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
        this.cb = new CheckBox[Game.NUMBER_OF_DISHES];
        this.activatedDishes = 0;
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
                    if (activatedDishes >= Game.MAX_AMOUNT_OF_DISHES) {
                        blockBoxes();
                    }
                    setAvailable();
                } else {
                    activatedDishes--;
                    unblockBoxes();
                    setAvailable();
                }
            });
        }
    }

    public final void blockBoxes() {
        for (int i = 0; i < cb.length; i++) {
            if (!cb[i].isSelected()) {
                cb[i].setDisable(true);
            }
        }
    }

    public final void unblockBoxes() {
        for (int i = 0; i < cb.length; i++) {
            cb[i].setDisable(false);
        }
    }

    public final void setAvailable() {
        for (int i = 0; i < cb.length; i++) {
            if (cb[i].isSelected()) {
                game.getPlayer(0).getMenuCard().getDish(i).setAvailable(true);
            } else {
                game.getPlayer(0).getMenuCard().getDish(i).setAvailable(false);
            }
        }
    }
    
    public final int setCheckBoxSize(int rows, double height) {
        int size = (int) (height / rows);
        for (int i = 0; i < cb.length; i++) {
            cb[i].setMinSize(size, size);
        }
        return size;
    }

    public Parent showElement() {
        Button button = new Button("Zurück");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);

        FlowPane fp = new FlowPane(button);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);

        GridPane gp = new GridPane();
        gp.setMinSize(360, 520);
        gp.setMaxSize(360, 520);
        gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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
        
        button.setOnAction((ActionEvent) -> {
            if (activatedDishes != 0) {
                stage.getScene().setRoot(new MenuCardWindow(stage, game).showElement());
            } else {
                stage.getScene().setRoot(new MenuCardWindow(stage, game).showElement());
            }
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