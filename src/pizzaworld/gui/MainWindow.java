package pizzaworld.gui;

import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
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
    
    public Parent showElement(Game game) {
        
        Text topDateText = new Text(game.getClock().getTime());
        topDateText.setTextAlignment(TextAlignment.CENTER);
        topDateText.setFont(new Font(14.0));
        
        game.getClock().getTimeProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            topDateText.setText(game.getClock().getTime());
        });

        Text topMoneyText = new Text("1100 €");
        topMoneyText.setTextAlignment(TextAlignment.CENTER);
        topMoneyText.setFont(new Font(14.0));

        HBox hbDate = new HBox(topDateText);
        hbDate.setMinSize(360.0, 80.0);
        hbDate.setMaxSize(360.0, 80.0);

        HBox hbMoney = new HBox(topMoneyText);
        hbMoney.setMinSize(360.0, 80.0);
        hbMoney.setMaxSize(360.0, 80.0);

        String news;
        news = ("more text!\n"
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
        game.getNewsfeed().addNews(news);
        
        TextArea area = new TextArea(game.getNewsfeed().getNews());
        area.appendText("");
        area.setEditable(false);
        area.setMinSize(360.0, 320.0);
        area.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            area.setScrollTop(Double.MAX_VALUE);
        });

        GridPane gpRoot = new GridPane();
        gpRoot.addRow(0, hbDate);
        gpRoot.addRow(1, hbMoney);
        gpRoot.addRow(2, area);
        gpRoot.setGridLinesVisible(true);

        return gpRoot;
    }
}
