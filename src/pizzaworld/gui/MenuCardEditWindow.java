package pizzaworld.gui;

import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

    public Parent showElement() {
        Button button = new Button("Zurück");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);

        FlowPane fp = new FlowPane(button);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);

        VBox vb = new VBox(cb);
        vb.setMinSize(360, 520);
        vb.setMaxSize(360, 520);

        button.setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new MenuCardWindow(game, stage).showElement());
        });

        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, new TopPanel(game).showElement());
        gpRoot.addRow(1, vb);
        gpRoot.addRow(2, fp);
        gpRoot.addRow(3, new BottomPanel(game).showElement());
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}
