package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class MainWindow {

    private TextArea content;
    private Button[] buttons = new Button[4];

    public MainWindow(Game game, Window window) {

        // Content
        content = new TextArea(game.getNewsfeed().getNews());
        content.appendText("");
        content.setEditable(false);
        content.textProperty().addListener((observable) -> {
            content.setScrollTop(Double.MAX_VALUE);
        });
        game.getNewsfeed().getNewsProperty().addListener((observable) -> {
            content.setText(game.getNewsfeed().getNews());
            content.appendText("");
        });
        
        // Buttons
        buttons[0] = new Button("Speisenkarte");
        buttons[1] = new Button("Personal");
        buttons[2] = new Button("Statistik");
        buttons[3] = new Button("Tag beenden");

        buttons[0].setOnAction((ActionEvent) -> {
            MenuCardWindow w = new MenuCardWindow(game, window);
            window.update(w.getContent(), w.getButtons());
        });

        buttons[1].setOnAction((ActionEvent) -> {
            
        });

        buttons[2].setOnAction((ActionEvent) -> {
            StatisticsWindow w = new StatisticsWindow(game, window, 0);
            window.update(w.getContent(), w.getButtons());
        });

        buttons[3].setOnAction((ActionEvent) -> {
            game.endCurrentDay();
            EndDayWindow w = new EndDayWindow(game, window);
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
