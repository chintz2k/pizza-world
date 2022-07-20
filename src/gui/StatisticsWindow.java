package gui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class StatisticsWindow {

    private GridPane content;
    private Button[] buttons = new Button[2];

    public StatisticsWindow(Game game, Window window, int option) {

        // Content
        content = new GridPane();
        if (option == 0) {
            content.addRow(0, new Text("Verkaufte Pizzen"));
        } else if (option == 1) {
            content.addRow(0, new Text("Umsatz Pizzen"));
        }
        content.add(new Text("Gestern"), 1, 1);
        content.add(new Text("Gesamt"), 2, 1);
        if (option == 0) {
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                content.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, i + 2);
                content.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnits(i, game.getDay() - 1))), 1, i + 2);
                content.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnitsAllTime(i, game.getDay()))), 2, i + 2);
            }
            content.add(new Text("Gesamt"), 0, content.getRowCount());
            content.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnitsTotal(game.getDay() - 1))), 1, content.getRowCount() - 1);
            content.add(new Text(String.valueOf(game.getPlayer(0).getStatistics().getSoldUnitsAllTimeTotal(game.getDay()))), 2, content.getRowCount() - 1);
        } else if (option == 1) {
            for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
                content.add(new Text(game.getPlayer(0).getMenuCard().getDish(i).getName()), 0, i + 2);
                content.add(new Text(game.getPlayer(0).getStatistics().getSales(i, game.getDay() - 1) + " €"), 1, i + 2);
                content.add(new Text(game.getPlayer(0).getStatistics().getSalesAllTime(i, game.getDay()) + " €"), 2, i + 2);
            }
            content.add(new Text("Gesamt"), 0, content.getRowCount());
            content.add(new Text(game.getPlayer(0).getStatistics().getSalesTotal(game.getDay() - 1) + " €"), 1, content.getRowCount() - 1);
            content.add(new Text(game.getPlayer(0).getStatistics().getSalesAllTimeTotal(game.getDay()) + " €"), 2, content.getRowCount() - 1);
        }
        
        for (int i = 1; i < content.getChildren().size(); i += 3) {
            GridPane.setHalignment(content.getChildren().get(i), HPos.RIGHT);
            GridPane.setHalignment(content.getChildren().get(i + 1), HPos.RIGHT);
        }
        
        content.getColumnConstraints().add(new ColumnConstraints(GuiConfig.WIDTH * 0.3));
        content.getColumnConstraints().add(new ColumnConstraints(GuiConfig.WIDTH * 0.2));
        content.getColumnConstraints().add(new ColumnConstraints(GuiConfig.WIDTH * 0.2));
        RowConstraints rc;
        if (Game.DEBUGGING) {
            rc = new RowConstraints(GuiConfig.CONTENT_HEIGHT_1_BUTTONSROW_DEBUGGING / content.getRowCount());
        } else {
            rc = new RowConstraints(GuiConfig.CONTENT_HEIGHT_1_BUTTONROW / content.getRowCount());
        }
        for (int i = 0; i < content.getRowCount(); i++) {
            content.getRowConstraints().add(rc);
        }
        
        // Buttons
        buttons = new Button[2];
        if (option == 0) {
            buttons[0] = new Button("Umsatz");
            buttons[1] = new Button("Zurück");
        } else if (option == 1) {
            buttons[0] = new Button("Verkäufe");
            buttons[1] = new Button("Zurück");
        }

        if (option == 0) {
            buttons[0].setOnAction((ActionEvent) -> {
                StatisticsWindow w = new StatisticsWindow(game, window, 1);
                window.update(w.getContent(), w.getButtons());
            });
        } else if (option == 1) {
            buttons[0].setOnAction((ActionEvent) -> {
                StatisticsWindow w = new StatisticsWindow(game, window, 0);
                window.update(w.getContent(), w.getButtons());
            });
        }
        buttons[1].setOnAction((ActionEvent) -> {
            MainWindow w = new MainWindow(game, window);
            window.update(w.getContent(), w.getButtons());
        });

        // DEBUGGING
        content.setGridLinesVisible(true);
        content.setAlignment(Pos.CENTER);
    }
    
    public Region getContent() {
        return content;
    }

    public Button[] getButtons() {
        return buttons;
    }

}
