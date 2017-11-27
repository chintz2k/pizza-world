package pizzaworld.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author andre
 */
public class PizzaWorld extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(null, 360, 640);
        
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
