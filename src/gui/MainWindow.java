package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import gui.elements.BottomPanel;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class MainWindow {

    VBox vbox;

    public MainWindow(Game game) {

        vbox = new VBox();

        vbox.getChildren().add(new Text(new BottomPanel(game).getText()));

        TextArea news = new TextArea(game.getNewsfeed().getNews());
        news.appendText("");
        news.setEditable(false);
        news.textProperty().addListener((observable) -> {
            news.setScrollTop(Double.MAX_VALUE);
        });
        game.getNewsfeed().getNewsProperty().addListener((observable) -> {
            news.setText(game.getNewsfeed().getNews());
            news.appendText("");
        });

        if (Game.DEBUGGING) {
            Text[] enemyStats = new Text[Game.NUMBER_OF_PLAYERS - 1];
            for (int i = 0; i < enemyStats.length; ++i) {
                enemyStats[i] = new Text(new BottomPanel(game, i + 1).getText());
            }
            vbox.getChildren().addAll(enemyStats);
            news.setPrefHeight(440.0);
        } else {
            news.setPrefHeight(440.0);
        }
        
        vbox.getChildren().add(news);

        Button[] buttons = {
            new Button("Speisenkarte"),
            new Button("Personal"),
            new Button("Statistik"),
            new Button("Tag beenden")
        };
        for (Button button : buttons) {
            button.setPrefSize(180.0, 80.0);
        }
        FlowPane fp = new FlowPane(buttons);
        vbox.getChildren().add(fp);

        buttons[0].setOnAction((ActionEvent) -> {
            //controller.changeMid(new MenuCardWindow(controller, game).getRoot());
            vbox.getChildren().clear();
            vbox.getChildren().add(new MenuCardWindow(game).getRoot());
        });

        buttons[1].setOnAction((ActionEvent) -> {
            
        });

        buttons[2].setOnAction((ActionEvent) -> {
            //controller.changeMid(new StatisticsWindow(controller, game, 0).getRoot());
        });

        buttons[3].setOnAction((ActionEvent) -> {
            //game.endCurrentDay();
            //controller.changeMid(new EndDayWindow(controller, game).getRoot());
        });

    }

    public VBox getRoot() {
        return vbox;
    }

}
