package de.pizzaworld;

import de.pizzaworld.gui.GameWindow;
import de.pizzaworld.logic.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class PizzaWorldApplication extends Application {

	ArrayList<Button> buttons;
	VBox vb;
	Scene scene;

	public PizzaWorldApplication() {
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
			GameWindow gw = new GameWindow(scene.getHeight(), game);
			Parent parent = gw.getRoot();
			stage.getScene().setRoot(parent);
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
