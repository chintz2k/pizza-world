package pizzaworld.logic;

/**
 *
 * @author andre
 */
public class Game {
    
    Clock clock;
    Newsfeed newsfeed;
    Timer timer;

    public Game() {
        this.clock = new Clock();
        this.timer = new Timer(clock);
        this.newsfeed = new Newsfeed();
    }

    public Clock getClock() {
        return clock;
    }
    
    public Newsfeed getNewsfeed() {
        return newsfeed;
    }

    public Timer getTimer() {
        return timer;
    }

}
