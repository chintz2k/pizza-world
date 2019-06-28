package pizzaworld.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pizzaworld.gui.elements.BottomPanel;
import pizzaworld.gui.elements.TopPanel;
import pizzaworld.logic.Game;

/**
 *
 * @author andre
 */
public class MainWindow extends Stage {

    private final Game game;
    private final Stage stage;

    public MainWindow(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }

    public Parent showElement() {

        TextArea news = new TextArea(game.getNewsfeed().getNews());
        news.appendText("");
        news.setEditable(false);
        news.setMinSize(360.0, 440.0);
        news.textProperty().addListener((observable) -> {
            news.setScrollTop(Double.MAX_VALUE);
        });
        game.getNewsfeed().getNewsProperty().addListener((observable) -> {
            news.setText(game.getNewsfeed().getNews());
            news.appendText("");
        });

        Button[] buttons = {
            new Button("Speisekarte"),
            new Button("Personal"),
            new Button("Einrichtung"),
            new Button("Statistiken")
        };

        for (Button button : buttons) {
            button.setMinSize(180.0, 80.0);
            button.setMaxSize(180.0, 80.0);
        }

        FlowPane fp = new FlowPane(buttons);
        fp.setMinSize(360.0, 160.0);
        fp.setMaxSize(360.0, 160.0);

        GridPane gpRoot = new GridPane();
        if (Game.DEBUGGING) {
            int minsz = 440 - ((game.getPlayers().length - 1) * 40);
            news.setMinSize(360, minsz);
            int y = 0;
            for (int i = 0; i < game.getPlayers().length; i++) {
                gpRoot.addRow(y, new TopPanel(game, i).showElement());
                y++;
                gpRoot.addRow(y, new BottomPanel(game, i).showElement());
                y++;
            }
            gpRoot.addRow(y, news);
            y++;
            gpRoot.addRow(y, fp);
        } else {
            gpRoot.addRow(0, new TopPanel(game).showElement());
            gpRoot.addRow(1, news);
            gpRoot.addRow(2, fp);
            gpRoot.addRow(3, new BottomPanel(game).showElement());
            gpRoot.setGridLinesVisible(true);
        }
        gpRoot.setGridLinesVisible(true);

        buttons[0].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new MenuCardWindow(game, stage).showElement());
        });

        // TODO einige Keybinds sind nur für Debuggingzwecke
        stage.getScene().setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.T) {
                System.out.println(game.getTimer().isRunning());
            }
            if (event.getCode() == KeyCode.P) {
                if (game.getTimer().isRunning()) {
                    game.getTimer().stop();
                } else {
                    game.getTimer().start();
                }
            }
        });

        return gpRoot;
    }
}
