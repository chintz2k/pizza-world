package pizzaworld.gui;

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
import pizzaworld.gui.elements.BottomPanel;
import pizzaworld.gui.elements.TopPanel;
import pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class MenuCardEditWindow extends Stage {

    private final Game game;
    private final Stage stage;

    private int max;

    private final CheckBox[] cb;

    public MenuCardEditWindow(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
        this.cb = new CheckBox[game.getProducts().getDishes().size()];
        this.max = 0;
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            cb[i] = new CheckBox(game.getProducts().getDishes().get(i).getName());
            cb[i].setMinSize(32.5, 32.5);
        }
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            if (game.getProducts().getDishes().get(i).isAvailable(0)) {
                max++;
                cb[i].setSelected(true);
                if (max >= game.getProducts().getDishes().size() / 2) {
                    blockBoxes();
                }
            }
        }
        for (int i = 0; i < cb.length; i++) {
            cb[i].selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                if (newValue) {
                    max++;
                    if (max >= game.getProducts().getDishes().size() / 2) {
                        blockBoxes();
                    }
                    setAvailable();
                } else {
                    max--;
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
                game.getProducts().getDishes().get(i).setAvailable(0, true);
            } else {
                game.getProducts().getDishes().get(i).setAvailable(0, false);
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
            gp.add(new Text(game.getProducts().getDishes().get(i).getPrice() + " €"), 2, i + 1);
        }
        gp.getRowConstraints().add(new RowConstraints(setCheckBoxSize(gp.getRowCount(), gp.getMinHeight())));
        gp.getColumnConstraints().add(new ColumnConstraints(50.0));
        gp.getColumnConstraints().add(new ColumnConstraints(220.0));
        gp.getColumnConstraints().add(new ColumnConstraints(90.0));
        
        button.setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new MenuCardWindow(game, stage).showElement());
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
