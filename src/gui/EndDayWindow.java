package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class EndDayWindow {
    
    private TextArea content;
    private Button[] buttons = new Button[1];

    public EndDayWindow(Game game, Window window) {
        
        // Content
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
        content = new TextArea(dayStats);
        content.setEditable(false);

        // Buttons
        buttons[0] = new Button("OK");

        buttons[0].setOnAction((ActionEvent) -> {
            PointsWindow w = new PointsWindow(game, window);
            window.update(w.getContent(), w.getButtons());
        });

    }

    public Region getContent() {
        return content;
    }

    public Button[] getButtons() {
        return buttons;
    }
    
}
