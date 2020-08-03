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
        news.setMaxSize(360.0, 440.0);
        news.textProperty().addListener((observable) -> {
            news.setScrollTop(Double.MAX_VALUE);
        });
        game.getNewsfeed().getNewsProperty().addListener((observable) -> {
            news.setText(game.getNewsfeed().getNews());
            news.appendText("");
        });

        Button[] buttons = {
            new Button("Speisenkarte"),
            new Button("Personal"),
            new Button("Statistik"),
            new Button("Tag beenden")
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
                gpRoot.addRow(y, new TopPanel(game).showElement());
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

        buttons[1].setOnAction((ActionEvent) -> {
            
        });

        buttons[2].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new StatisticsWindow(game, stage, 0).showElement());
        });

        buttons[3].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new EndDayWindow(game, stage).showElement());
        });

        stage.getScene().setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.U) {
                for (int i = 0; i < game.getPlayers()[0].getStatistics().getSoldUnits().size(); i++) {
                    for (int j = 0; j < game.getPlayers()[0].getStatistics().getSoldUnits().get(i).size(); j++) {
                        System.out.println(i + "/" +  j + ":" + game.getPlayers()[0].getStatistics().getSoldUnits().get(i).get(j));
                    }
                }
            }
            if (event.getCode() == KeyCode.S) {
                for (int i = 0; i < game.getPlayers()[0].getStatistics().getSales().size(); i++) {
                    for (int j = 0; j < game.getPlayers()[0].getStatistics().getSales().get(i).size(); j++) {
                        System.out.println(i + "/" +  j + ":" + game.getPlayers()[0].getStatistics().getSales().get(i).get(j));
                    }
                }
            }
            if (event.getCode() == KeyCode.A) {
                System.out.print(game.getPlayers()[0].getStatistics().getSoldUnits().get(0).get(0) + "/");
                System.out.println(game.getPlayers()[0].getStatistics().getSales().get(0).get(0));
                System.out.print(game.getPlayers()[0].getStatistics().getSoldUnits().get(0).get(1) + "/");
                System.out.println(game.getPlayers()[0].getStatistics().getSales().get(0).get(1));
                System.out.print(game.getPlayers()[0].getStatistics().getSoldUnits().get(0).get(2) + "/");
                System.out.println(game.getPlayers()[0].getStatistics().getSales().get(0).get(2));
                System.out.print(game.getPlayers()[0].getStatistics().getSoldUnits().get(0).get(3) + "/");
                System.out.println(game.getPlayers()[0].getStatistics().getSales().get(0).get(3));
            }
            if (event.getCode() == KeyCode.B) {
                for (int j = 0; j < 10000; j++) {
                    for (int i = 0; i < Game.PLAYERCOUNT; i++) {
                        game.getPlayers()[i].endDay();
                    }
                }
            }
        });

        return gpRoot;
    }
}
