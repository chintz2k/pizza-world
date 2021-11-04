import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Game;
import gui.MainWindow;

/**
 *
 * @author André Heinen
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        Button[] buttons = {
            new Button("Neues Spiel"),
            new Button("Spiel laden"),
            new Button("Spiel verlassen")
        };

        VBox vb = new VBox(buttons);
        vb.setSpacing(8.0);
        vb.setAlignment(Pos.CENTER);

        buttons[0].setOnAction((ActionEvent) -> {
            Game game = new Game();
            stage.getScene().setRoot(new MainWindow(game, stage).showElement());
        });

        Scene scene = new Scene(vb, 360, 640);
        
        stage.setResizable(false);
        stage.setTitle("Pizza World");
        stage.setScene(scene);
        
        stage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
