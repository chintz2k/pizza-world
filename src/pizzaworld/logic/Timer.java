package pizzaworld.logic;

import java.io.Serializable;
import javafx.animation.AnimationTimer;
import pizzaworld.gui.EndDayWindow;

/**
 *
 * @author André Heinen
 */
public class Timer extends AnimationTimer implements Serializable {

    private long startTime;

    private boolean running;

    private final Game game;
    private final Clock clock;

    private double timeDay = -1;

    public Timer(Game game, Clock clock) {
        startTime = System.nanoTime();
        this.game = game;
        this.clock = clock;
    }

    @Override
    public void handle(long now) {
        if (timeDay == -1) {
            timeDay = now;
        }
        double timeSinceStart = ((now - startTime) / 1000000000.0);
        if (timeSinceStart > 0.005) {
            startTime = System.nanoTime();
            game.getGuestList().guestsTimeBuy(clock.getDaytime());
            for (Player player : game.getPlayers()) {
                player.getRestaurant().decUsedSeats(clock.getDaytime());
            }
            if (!clock.incMinute()) {
                stop();
                timeDay = -1;
                game.getStage().getScene().setRoot(new EndDayWindow(game, game.getStage()).showElement());
            }
        }
    }

    @Override
    public void start() {
        super.start();
        running = true;
    }

    @Override
    public void stop() {
        super.stop();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

}
