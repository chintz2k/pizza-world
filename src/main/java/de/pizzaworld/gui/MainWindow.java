package de.pizzaworld.gui;

import de.pizzaworld.gui.elements.BottomPanel;
import de.pizzaworld.gui.elements.TopPanel;
import de.pizzaworld.logic.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author André Heinen
 */
public class MainWindow {

    Group root;

    public MainWindow(Game game, Scene scene) {

        VBox vbox = new VBox();

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

        Button[] buttons = {
                new Button("Speisenkarte"),
                new Button("Personal"),
                new Button("Statistik"),
                new Button("Tag beenden")
        };
        for (Button button : buttons) {
            button.setPrefSize(180.0, 80.0);
        }

        if (Game.DEBUGGING) {
            HBox[] enemyStats = new HBox[Game.NUMBER_OF_PLAYERS];
            for (int i = 0; i < enemyStats.length; ++i) {
                enemyStats[i] = new BottomPanel(game, i).getBottomPanel();
            }
            vbox.getChildren().addAll(enemyStats);
            news.setPrefSize(360.0, 400.0);
            vbox.getChildren().add(news);
            vbox.getChildren().add(new FlowPane(buttons));
        } else {
            vbox.getChildren().add(new TopPanel(game).getTopPanel());
            news.setPrefHeight(440.0);
            vbox.getChildren().add(news);
            vbox.getChildren().add(new FlowPane(buttons));
            vbox.getChildren().add(new BottomPanel(game).getBottomPanel());
        }

        root = new Group(vbox);

        buttons[0].setOnAction((ActionEvent) -> {
            scene.setRoot(new MenuCardWindow(game, scene).getRoot());
        });

        buttons[1].setOnAction((ActionEvent) -> {
            
        });

        buttons[2].setOnAction((ActionEvent) -> {
            scene.setRoot(new StatisticsWindow(game, scene, 0).getRoot());
        });

        buttons[3].setOnAction((ActionEvent) -> {
            game.endCurrentDay();
            scene.setRoot(new EndDayWindow(game, scene).getRoot());
        });

    }

    public Group getRoot() {
        return root;
    }

}
