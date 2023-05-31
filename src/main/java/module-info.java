module de.pizzaworld {
	requires javafx.controls;
	requires javafx.fxml;


	opens de.pizzaworld to javafx.fxml;
	exports de.pizzaworld;
}