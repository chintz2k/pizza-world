package de.pizzaworld;

import de.pizzaworld.gui.MainWindow;
import de.pizzaworld.logic.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PizzaWorldApplication extends Application {

	Button[] buttons;
	VBox vb;
	Scene scene;

	public PizzaWorldApplication() {
		buttons = new Button[3];
		buttons[0] = new Button("Neues Spiel");
		buttons[1] = new Button("Spiel laden");
		buttons[2] = new Button("Spiel verlassen");

		vb = new VBox(10.0);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(buttons);

		scene = new Scene(vb, 360.0, 640.0);
	}

	@Override
	public void start(Stage stage) {

		stage.setResizable(false);
		stage.setTitle("Pizza World");
		stage.setScene(scene);

		buttons[0].setOnAction((ActionEvent) -> {
			Game game = new Game();
			MainWindow mw = new MainWindow(game, scene);
			stage.getScene().setRoot(mw.getRoot());
		});

		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
