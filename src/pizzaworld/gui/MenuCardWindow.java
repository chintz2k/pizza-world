package pizzaworld.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pizzaworld.gui.elements.BottomPanel;
import pizzaworld.gui.elements.TopPanel;
import pizzaworld.logic.Game;
import pizzaworld.logic.MenuCard;

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
        
        TextArea menuCard = new TextArea(new MenuCard(game).getMenuCard());
        menuCard.appendText("");
        menuCard.setEditable(false);
        menuCard.setMinSize(360.0, 520.0);
        
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
        gpRoot.addRow(1, menuCard);
        gpRoot.addRow(2, fp);
        gpRoot.addRow(3, new BottomPanel(game).showElement());
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}
