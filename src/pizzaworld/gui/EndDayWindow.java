package pizzaworld.gui;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pizzaworld.gui.elements.BottomPanel;
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
                        + "TODO\n"
                        + "\n"
                        + "Umsatz: " + String.format("%.2f", game.getPlayers()[i].getStatistics().getSales(0)) + " €\n"
                        + "\n"
                        + "Gäste: " + game.getPlayers()[i].getStatistics().getGuests()[0][0] + "\n"
                        + "Abgewiesene Gäste: " + game.getPlayers()[i].getStatistics().getGuests()[1][0] + "\n"
                        + "-----------------------------------------------------\n";
            }
        } else {
            dayStats += "TODO" + "\n"
                    + "\n"
                    + "Umsatz: " + String.format("%.2f", game.getPlayers()[0].getStatistics().getSales(0)) + " €\n"
                    + "\n"
                    + "Gäste: " + game.getPlayers()[0].getStatistics().getGuests()[0][0] + "\n"
                    + "Abgewiesene Gäste: " + game.getPlayers()[0].getStatistics().getGuests()[1][0];
        }

        TextArea news = new TextArea(dayStats);
        news.setEditable(false);
        news.setMinSize(360.0, 540.0);

        Button button = new Button("OK");
        button.setMinSize(360.0, 80.0);
        button.setMaxSize(360.0, 80.0);

        FlowPane fp = new FlowPane(button);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);

        button.setOnAction((ActionEvent) -> {
            game.incDay();
            stage.getScene().setRoot(new MainWindow(game, stage).showElement());
        });

        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, news);
        gpRoot.addRow(1, fp);
        gpRoot.addRow(2, new BottomPanel(game).showElement());
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }

}
