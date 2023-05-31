package de.pizzaworld.gui.elements;

import javafx.scene.text.Text;

import de.pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class TopPanel {
    
    Text text;

    public TopPanel(Game game) {

        text = new Text(String.valueOf("Tag " + game.getDay()));

    }
    
    public Text getText() {
        return text;
    }

}
