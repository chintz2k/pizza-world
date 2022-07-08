import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

import logic.Game;
import gui.MainWindow;

/**
 *
 * @author André Heinen
 */
public class App extends Application {

    ArrayList<Button> buttons;
    VBox vb;
    Scene scene;

    public App() {
        buttons = new ArrayList<Button>();
        buttons.add(new Button("Neues Spiel"));
        buttons.add(new Button("Spiel laden"));
        buttons.add(new Button("Spiel verlassen"));

        vb = new VBox(10.0);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(buttons);

        scene = new Scene(vb, 360, 640);
    }

    @Override
    public void start(Stage stage) {

        buttons.get(0).setOnAction((ActionEvent) -> {
            Game game = new Game();
            MainWindow mw = new MainWindow(game);
            stage.getScene().setRoot(mw.getRoot());
        });

        stage.setResizable(true);
        stage.setTitle("Pizza World");
        stage.setScene(scene);
        
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
