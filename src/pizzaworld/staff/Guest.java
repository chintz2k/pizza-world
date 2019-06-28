package pizzaworld.staff;

import java.io.Serializable;
import pizzaworld.dishes.Dish;
import pizzaworld.logic.Game;

/**
 *
 * @author André Heinen
 */
public class Guest implements Serializable {

    private final Game game;

    private static int staticId = 0;

    private final int id;
    private final Dish mainLike;
    private final int dishLike;

    private final int time;

    public Guest(Game game) {
        this.game = game;

        id = staticId;
        staticId++;

        int i = (int) (Math.random() * game.getProducts().getDishes().size());
        mainLike = game.getProducts().getDishes().get(i);
        dishLike = i;
        time = (int) (Math.random() * ((24 - game.getClock().getStartHour()) * 60));
    }

    public int getId() {
        return id;
    }

    public Dish getMainLike() {
        return mainLike;
    }

    public int getTime() {
        return time;
    }

    public void buy() {
        int i = 0;
        while (i < Game.PLAYERCOUNT) {
            if (game.getSortedPlayers()[i].getRestaurant().isSeatAvailable()) {
                if (mainLike.isAvailable(game.getSortedPlayers()[i].getId())) {

                    game.getSortedPlayers()[i].addMoney(mainLike.getPrice());
                    game.getSortedPlayers()[i].getStatistics().incSales(dishLike, mainLike.getPrice());
                    game.getSortedPlayers()[i].getStatistics().incSoldUnits(dishLike);
                    game.getSortedPlayers()[i].getStatistics().incGuests(0);

                    game.getSortedPlayers()[i].getRestaurant().incUsedSeats(this.time);
                    game.getSortedPlayers()[i].getRestaurant().incSeatTime(this.time);

                    if (game.getSortedPlayers()[i].getId() == 0) {
                        game.getNewsfeed().addNews("Gast Nr." + id + " kauft " + mainLike.getName() + " für " + (String.format("%.2f", mainLike.getPrice()) + " €"));
                    }

                    break;

                } else {
                    game.getSortedPlayers()[i].getStatistics().incGuests(1);
                    if (game.getSortedPlayers()[i].getId() == 0) {
                        game.getNewsfeed().addNews("Gast Nr." + id + " findet sein Lieblingsgericht nicht!");
                    }
                    i++;
                }
            } else {
                game.getSortedPlayers()[i].getStatistics().incGuests(1);
                if (game.getSortedPlayers()[i].getId() == 0) {
                    game.getNewsfeed().addNews("Gast Nr." + id + " findet keinen Platz!");
                }
                i++;
            }
        }
    }
}