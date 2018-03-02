package pizzaworld.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    Game game = new Game();
    
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
        
        allButtons[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainWindow mwWindow = new MainWindow(primaryStage);
                primaryStage.getScene().setRoot(mwWindow.showWindow(game));
            }
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
