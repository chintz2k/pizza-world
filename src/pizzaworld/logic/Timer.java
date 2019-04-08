package pizzaworld.logic;

import java.io.Serializable;
import javafx.animation.AnimationTimer;

/**
 *
 * @author André Heinen
 */
public class Timer extends AnimationTimer implements Serializable {

    private long startTime;
    private final Clock clock;

    public Timer(Clock clock) {
        startTime = System.nanoTime();
        this.clock = clock;
    }

    @Override
    public void handle(long now) {
        double timeSinceStart = ((now - startTime) / 1000000000.0);
        if (timeSinceStart > 0.5) {
            startTime = System.nanoTime();
            clock.incMinute();
        }
    }
    
}
