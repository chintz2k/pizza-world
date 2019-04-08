package pizzaworld.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pizzaworld.logic.Game;

/**
 *
 * @author andre
 */
public class PizzaWorld extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button[] allButtons = {
            new Button("Neues Spiel"),
            new Button("Spiel laden"),
            new Button("Spiel verlassen")
        };

        VBox vbCenter = new VBox(allButtons);
        vbCenter.setSpacing(8.0);
        vbCenter.setAlignment(Pos.CENTER);

        allButtons[0].setOnAction((ActionEvent event) -> {
            Game game = new Game();
            game.getTimer().start();
            primaryStage.getScene().setRoot(new WindowController(primaryStage, game).showWindow());
        });

        Scene scene = new Scene(vbCenter, 360, 640);
        primaryStage.setTitle("Pizza World");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
