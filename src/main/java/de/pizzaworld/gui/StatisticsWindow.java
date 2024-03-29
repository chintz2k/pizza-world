package de.pizzaworld.gui;

import de.pizzaworld.gui.elements.BottomPanel;
import de.pizzaworld.gui.elements.TopPanel;
import de.pizzaworld.logic.Game;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 *
 * @author André Heinen
 */
public class StatisticsWindow {

    Group root;    

    public StatisticsWindow(Game game, Scene scene, int option) {

        GridPane gp = new GridPane();
        gp.setMinSize(360.0, 520.0);
        gp.setMaxSize(360.0, 520.0);
        if (option == 0) {
            gp.addRow(0, new Text("Verkaufte Pizzen"));
        } else if (option == 1) {
            gp.addRow(0, new Text("Umsatz Pizzen"));
        }
        gp.add(new Text("Gestern"), 1, 1);
        gp.add(new Text("Gesamt"), 2, 1);
        if (option == 0) {
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, i + 2);
                gp.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnits(i, game.getDay() - 1))), 1, i + 2);
                gp.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnitsAllTime(i, game.getDay()))), 2, i + 2);
            }
            gp.add(new Text("Gesamt"), 0, gp.getRowCount());
            gp.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnitsTotal(game.getDay() - 1))), 1, gp.getRowCount() - 1);
            gp.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnitsAllTimeTotal(game.getDay()))), 2, gp.getRowCount() - 1);
        } else if (option == 1) {
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                gp.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, i + 2);
                gp.add(new Text(game.getPlayer(0).getStatistics().getSales(i, game.getDay() - 1) + " €"), 1, i + 2);
                gp.add(new Text(game.getPlayer(0).getStatistics().getSalesAllTime(i, game.getDay()) + " €"), 2, i + 2);
            }
            gp.add(new Text("Gesamt"), 0, gp.getRowCount());
            gp.add(new Text(game.getPlayer(0).getStatistics().getSalesTotal(game.getDay() - 1) + " €"), 1, gp.getRowCount() - 1);
            gp.add(new Text(game.getPlayer(0).getStatistics().getSalesAllTimeTotal(game.getDay()) + " €"), 2, gp.getRowCount() - 1);
        }
        for (int i = 1; i < gp.getChildren().size(); i += 3) {
            GridPane.setHalignment(gp.getChildren().get(i), HPos.RIGHT);
            GridPane.setHalignment(gp.getChildren().get(i + 1), HPos.RIGHT);
        }
        ColumnConstraints cc0 = new ColumnConstraints(116.0);
        ColumnConstraints cc1 = new ColumnConstraints(100.0);
        RowConstraints rc0 = new RowConstraints(25.5);
        RowConstraints rc1 = new RowConstraints(32.0);
        gp.getColumnConstraints().addAll(cc0, cc1, cc1);
        for (int i = 0; i < gp.getRowCount() - 1; i++) {
            gp.getRowConstraints().addAll(rc0);
        }
        gp.getRowConstraints().add(gp.getRowCount() - 1, rc1);
        gp.setAlignment(Pos.CENTER);
        
        Button[] buttons = new Button[2];
        if (option == 0) {
            buttons[0] = new Button("Umsatz");
            buttons[1] = new Button("Zurück");
        } else if (option == 1) {
            buttons[0] = new Button("Verkäufe");
            buttons[1] = new Button("Zurück");
        }
        for (Button button : buttons) {
            button.setMinSize(180.0, 80.0);
            button.setMaxSize(180.0, 80.0);
        }

        root = new Group(
                new VBox(
                        new TopPanel(game).getTopPanel(),
                        gp,
                        new FlowPane(buttons),
                        new BottomPanel(game).getBottomPanel()
                )
        );

        if (option == 0) {
            buttons[0].setOnAction((ActionEvent) -> {
                scene.setRoot(new StatisticsWindow(game, scene, 1).getRoot());
            });
        } else if (option == 1) {
            buttons[0].setOnAction((ActionEvent) -> {
                scene.setRoot(new StatisticsWindow(game, scene, 0).getRoot());
            });
        }
        buttons[1].setOnAction((ActionEvent) -> {
            scene.setRoot(new MainWindow(game, scene).getRoot());
        });

    }
    
    public Group getRoot() {
        return root;
    }

}
