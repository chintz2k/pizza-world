import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

import logic.Game;
import gui.MainWindow;
import gui.Window;

/**
 *
 * @author André Heinen
 */
public class App extends Application {

    private ArrayList<Button> buttons;
    private VBox vb;
    private Scene scene;

    public App() {
        buttons = new ArrayList<Button>();
        buttons.add(new Button("Neues Spiel"));
        buttons.add(new Button("Spiel laden"));
        buttons.add(new Button("Spiel verlassen"));

        vb = new VBox(10.0);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(buttons);

        scene = new Scene(vb, 360.0, 640.0);
        // scene = new Scene(vb, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
    }

    @Override
    public void start(Stage stage) {

        buttons.get(0).setOnAction((ActionEvent) -> {
            Game game = new Game();
            Window window = new Window(game);
            MainWindow mw = new MainWindow(game, window);
            window.update(mw.getContent(), mw.getButtons());
            stage.getScene().setRoot(window.getRoot());
            scene.setOnKeyReleased((KeyEvent e) -> {
                if (e.getCode() == KeyCode.A) {
                    System.out.println(game.getDay());
                }
            });
        });

        stage.setResizable(true);
        stage.setMaximized(false);
        stage.setTitle("Pizza World");
        stage.setScene(scene);
        scene.setOnMouseClicked((MouseEvent e) -> {
            System.out.println(e.getTarget().toString());
        });
        
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
