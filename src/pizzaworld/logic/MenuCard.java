package pizzaworld.logic;

import java.io.Serializable;

/**
 *
 * @author André Heinen
 */
public final class MenuCard implements Serializable {

    private final Game game;

    private String menuCard;

    public MenuCard(Game game) {
        this.game = game;
        setMenuCard();
    }

    public void setMenuCard() {
        menuCard = "Pizzen\n";
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            if (game.getProducts().getDishes().get(i).isAvailable(0)) {
                menuCard += ("- " + game.getProducts().getDishes().get(i).getName() + "\n");
            }
        }
    }

    public String getMenuCard() {
        return menuCard;
    }

}
