package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import gui.elements.BottomPanel;
import gui.elements.TopPanel;
import logic.Game;

/**
 *
 * @author André Heinen
 */
public class StatisticsWindow extends Stage {
    
    private final Game game;
    private final Stage stage;
    
    private final int option;

    public StatisticsWindow(Game game, Stage stage, int option) {
        this.game = game;
        this.stage = stage;
        
        this.option = option;
    }
    
    public Parent showElement() {
        
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
            for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
                gp.add(new Text(game.getProducts().getDishes().get(i).getName()), 0, i + 2);
                gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsYesterday(i, game.getDay()))), 1, i + 2);
                gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsAllTime(i, game.getDay()))), 2, i + 2);
            }
            gp.add(new Text("Gesamt"), 0, gp.getRowCount());
            gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsYesterdayTotal(game.getDay()))), 1, gp.getRowCount() - 1);
            gp.add(new Text(String.valueOf(game.getPlayers()[0].getStatistics().getSoldUnitsAllTimeTotal(game.getDay()))), 2, gp.getRowCount() - 1);
        } else if (option == 1) {
            for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
                gp.add(new Text(game.getProducts().getDishes().get(i).getName()), 0, i + 2);
                gp.add(new Text(game.getPlayers()[0].getStatistics().getSalesYesterday(i, game.getDay()) + " €"), 1, i + 2);
                gp.add(new Text(game.getPlayers()[0].getStatistics().getSalesAllTime(i, game.getDay()) + " €"), 2, i + 2);
            }
            gp.add(new Text("Gesamt"), 0, gp.getRowCount());
            gp.add(new Text(game.getPlayers()[0].getStatistics().getSalesYesterdayTotal(game.getDay()) + " €"), 1, gp.getRowCount() - 1);
            gp.add(new Text(game.getPlayers()[0].getStatistics().getSalesAllTimeTotal(game.getDay()) + " €"), 2, gp.getRowCount() - 1);
        }
        
        for (int i = 1; i < gp.getChildren().size(); i += 3) {
            GridPane.setHalignment(gp.getChildren().get(i), HPos.RIGHT);
            GridPane.setHalignment(gp.getChildren().get(i + 1), HPos.RIGHT);
        }
        
        ColumnConstraints cc0 = new ColumnConstraints(116.0);
        ColumnConstraints cc1 = new ColumnConstraints(80.0);
        RowConstraints rc0 = new RowConstraints(25.5);
        RowConstraints rc1 = new RowConstraints(32.0);
        gp.getColumnConstraints().addAll(cc0, cc1, cc1);
        for (int i = 0; i < gp.getRowCount() - 1; i++) {
            gp.getRowConstraints().addAll(rc0);
        }
        gp.getRowConstraints().add(gp.getRowCount() - 1, rc1);
        
        gp.setAlignment(Pos.CENTER);
        gp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
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

        FlowPane fp = new FlowPane(buttons);
        fp.setMinSize(360.0, 80.0);
        fp.setMaxSize(360.0, 80.0);
        
        if (option == 0) {
            buttons[0].setOnAction((ActionEvent) -> {
                stage.getScene().setRoot(new StatisticsWindow(game, stage, 1).showElement());
            });
        } else if (option == 1) {
            buttons[0].setOnAction((ActionEvent) -> {
                stage.getScene().setRoot(new StatisticsWindow(game, stage, 0).showElement());
            });
        }
        buttons[1].setOnAction((ActionEvent) -> {
            stage.getScene().setRoot(new MainWindow(game, stage).showElement());
        });
        
        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, new TopPanel(game).showElement());
        gpRoot.addRow(1, gp);
        gpRoot.addRow(2, fp);
        gpRoot.addRow(3, new BottomPanel(game).showElement());
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}
