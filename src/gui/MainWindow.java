package gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import gui.elements.BottomPanel;
import gui.elements.TopPanel;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class MainWindow extends Stage {

    private final Stage stage;

    private final Game game;

    public MainWindow(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
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
            int minsz = 440 - ((Game.NUMBER_OF_PLAYERS - 1) * 40);
            news.setMinSize(360, minsz);
            int y = 0;
            for (int i = 0; i < Game.NUMBER_OF_PLAYERS; i++) {
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
            stage.getScene().setRoot(new MenuCardWindow(stage, game).showElement());
        });

        buttons[1].setOnAction((ActionEvent) -> {
            
        });

        buttons[2].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new StatisticsWindow(stage, game, 0).showElement());
        });

        buttons[3].setOnAction((ActionEvent) -> {
            game.endCurrentDay();
            stage.getScene().setRoot(new EndDayWindow(stage, game).showElement());
        });

        stage.getScene().setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.U) {
                for (int i = 0; i < game.getPlayer(0).getStatistics().getSoldUnits().size(); i++) {
                    for (int j = 0; j < game.getPlayer(0).getStatistics().getSoldUnits().get(i).size(); j++) {
                        System.out.println(i + "/" +  j + ":" + game.getPlayer(0).getStatistics().getSoldUnits().get(i).get(j));
                    }
                }
            }
            if (event.getCode() == KeyCode.S) {
                for (int i = 0; i < game.getPlayer(0).getStatistics().getSales().size(); i++) {
                    for (int j = 0; j < game.getPlayer(0).getStatistics().getSales().get(i).size(); j++) {
                        System.out.println(i + "/" +  j + ":" + game.getPlayer(0).getStatistics().getSales().get(i).get(j));
                    }
                }
            }
            if (event.getCode() == KeyCode.A) {
                System.out.println(game.getPlayer(0).getStatistics().getSoldUnits().size());
                System.out.println(game.getPlayer(0).getStatistics().getSoldUnits().get(0));
            }
            if (event.getCode() == KeyCode.B) {
                for (int c = 0; c < 10000; c++) {
                    for (int i = 0; i < Game.NUMBER_OF_PLAYERS; i++) {
                        for (int j = 0; j < Game.NUMBER_OF_GUESTS; j++) {
                            
                        }
                    }
                }
            }
            if (event.getCode() == KeyCode.D) {
                game.getPlayer(0).addMoney(1);
            }
            if (event.getCode() == KeyCode.Z) {
                
            }
        });

        return gpRoot;
    }
}