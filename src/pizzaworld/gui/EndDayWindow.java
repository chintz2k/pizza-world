package pizzaworld.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pizzaworld.gui.elements.BottomPanel;
import pizzaworld.gui.elements.TopPanel;
import pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class EndDayWindow extends Stage {

    private final Game game;
    private final Stage stage;

    public EndDayWindow(Game game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }

    public Parent showElement() {
        
        for (int i = 0; i < Game.PLAYERCOUNT; i++) {
            game.getPlayers()[i].endDay();
        }

        String dayStats = "";

        if (Game.DEBUGGING) {
            for (int i = 0; i < game.getPlayers().length; i++) {
                dayStats += "Spieler: " + i + "\n"
                        + "Verkaufte Pizzen: " + game.getPlayers()[i].getStatistics().getSoldUnitsYesterdayTotal(game.getDay() + 1) + "\n"
                        + "\n"
                        + "Umsatz: " + game.getPlayers()[i].getStatistics().getSalesYesterdayTotal(game.getDay() + 1) + " €\n"
                        + "\n"
                        + "-----------------------------------------------------\n";
            }
        } else {
            dayStats += "Verkaufte Pizzen: " + game.getPlayers()[0].getStatistics().getSoldUnitsYesterdayTotal(game.getDay() + 1) + "\n"
                    + "\n"
                    + "Umsatz: " + game.getPlayers()[0].getStatistics().getSalesYesterdayTotal(game.getDay() + 1) + " €\n"
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
            stage.getScene().setRoot(new PointsWindow(game, stage).showWindow());
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
