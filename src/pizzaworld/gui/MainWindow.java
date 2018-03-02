package pizzaworld.gui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pizzaworld.logic.Game;

/**
 *
 * @author andre
 */
public class MainWindow extends Stage {

    Stage stage;

    public MainWindow(Stage stage) {
        this.stage = stage;
    }

    public Parent showWindow(Game game) {
        Text topDateText = new Text("11.07.07");
        topDateText.setTextAlignment(TextAlignment.CENTER);
        topDateText.setFont(new Font(14.0));

        Text topMoneyText = new Text("1100 €");
        topMoneyText.setTextAlignment(TextAlignment.CENTER);
        topMoneyText.setFont(new Font(14.0));

        Button[] bottomButtons = {
            new Button("Speisekarte"),
            new Button("Personal"),
            new Button("Einrichtung"),
            new Button("Statistiken")
        };

        for (Button button : bottomButtons) {
            button.setMinSize(180.0, 80.0);
            button.setMaxSize(180.0, 80.0);
        }

        HBox hbDate = new HBox(topDateText);
        hbDate.setMinSize(360.0, 80.0);
        hbDate.setMaxSize(360.0, 80.0);

        HBox hbMoney = new HBox(topMoneyText);
        hbMoney.setMinSize(360.0, 80.0);
        hbMoney.setMaxSize(360.0, 80.0);

        FlowPane fpBottomButtons = new FlowPane(bottomButtons);
        fpBottomButtons.setMinSize(360.0, 80.0);
        fpBottomButtons.setMaxSize(360.0, 80.0);

        Text newsText = new Text("Main Window HBOX");
        HBox hbText = new HBox(newsText);
        hbText.setAlignment(Pos.BOTTOM_LEFT);
        hbText.setMinSize(360.0, 320.0);
        hbText.setMaxSize(360.0, 320.0);
        
        newsText.setText("more text!!111\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "and mooorrrre\n"
                + "morreadadad\n"
                + "");

        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, hbDate);
        gpRoot.addRow(1, hbMoney);
        gpRoot.addRow(2, hbText);
        gpRoot.addRow(3, fpBottomButtons);
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}
