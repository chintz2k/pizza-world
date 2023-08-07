package de.pizzaworld.gui;

import de.pizzaworld.logic.Game;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author André Heinen
 */
public class EndDayWindow {

    Group root;

    public EndDayWindow(Game game, Scene scene) {
        
        String dayStats = "";
        if (Game.DEBUGGING) {
            for (int i = 0; i < Game.NUMBER_OF_PLAYERS; i++) {
                dayStats += "Spieler: " + i + "\n"
                        + "Verkaufte Pizzen: " + game.getPlayer(i).getStatistics().getSoldUnitsTotal(game.getDay()) + "\n"
                        + "\n"
                        + "Umsatz: " + game.getPlayer(i).getStatistics().getSalesTotal(game.getDay()) + " €\n"
                        + "\n"
                        + "-----------------------------------------------------\n";
            }
        } else {
            dayStats += "Verkaufte Pizzen: " + game.getPlayer(0).getStatistics().getSoldUnitsTotal(game.getDay()) + "\n"
                    + "\n"
                    + "Umsatz: " + game.getPlayer(0).getStatistics().getSalesTotal(game.getDay()) + " €\n"
                    + "\n";
        }
        
        TextArea news = new TextArea(dayStats);
        news.setEditable(false);
        news.setMinSize(360.0, 560.0);
        news.setMaxSize(360.0, 560.0);

        Button button = new Button("OK");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);

        GridPane gp = new GridPane();
        gp.addRow(0, news);
        gp.addRow(1, new FlowPane(button));
        gp.setGridLinesVisible(true);

        root = new Group(gp);

        button.setOnAction((ActionEvent) -> {
            scene.setRoot(new PointsWindow(game, scene).getRoot());
        });

    }

    public Group getRoot() {
        return root;
    }

}
