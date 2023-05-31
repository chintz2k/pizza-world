package de.pizzaworld.gui;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import de.pizzaworld.gui.elements.BottomPanel;
import de.pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class MainWindow {

    Group root;

    VBox vbox;

    public MainWindow(GameWindow window, Game game) {

        vbox = new VBox();

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
                enemyStats[i] = new BottomPanel(game, i + 1).getText();
            }
            vbox.getChildren().addAll(enemyStats);
            vbox.getChildren().add(news);
        } else {
            news.setPrefHeight(440.0);
            vbox.getChildren().add(news);
        }
        
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

        root = new Group();
        root.getChildren().add(vbox);

        buttons[0].setOnAction((ActionEvent) -> {
            //controller.changeMid(new MenuCardWindow(controller, game).getRoot());
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

    public Parent getRoot() {
        return root;
    }

}
