package gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
public class EndDayWindow extends Stage {

    private final Stage stage;
    
    private final Game game;

    public EndDayWindow(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
    }

    public Parent showElement() {
        
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
        news.setMinSize(360.0, 520.0);

        Button button = new Button("OK");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);

        FlowPane fp = new FlowPane(button);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);

        button.setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new PointsWindow(stage, game).showWindow());
        });

        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, new TopPanel(game).showElement());
        gpRoot.addRow(1, news);
        gpRoot.addRow(2, fp);
        gpRoot.addRow(3, new BottomPanel(game).showElement());
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}