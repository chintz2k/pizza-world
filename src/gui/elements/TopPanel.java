package gui.elements;

import logic.Game;

/**
 *
 * @author André Heinen
 */
public class TopPanel {
    
    String text;

    public TopPanel(Game game) {

        text = "Tag " + game.getDay();

    }
    
    public String getText() {
        return text;
    }

}
