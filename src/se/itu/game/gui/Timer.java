package se.itu.game.gui;

/**
 * Class for keeping track of time from when the player
 * starts to when player picks up the Pirate Chest
 */
public class Timer {

    // start and stop variables
    private static long startTime = 0;
    private static long stopTime = 0;

    /**
     * Timer shouldn't be instantiated, static access only
     */
    private Timer() {}

    /**
     * Starts the timer
     */
    public static void start() {
        Timer.startTime = System.currentTimeMillis();
    }

    /**
     * Stops the timer
     */
    public static void stop() {
        Timer.stopTime = System.currentTimeMillis();
    }

    /**
     * Evaluates the amount of milliseconds between start and termination of the game
     * and then show it to the user as a popup window.
     */
    public static void showTime() {
        long elapsed = (stopTime - startTime) / 1000;
        int minutes = 0;
        for (int i = 1; i <= elapsed; i++) {
            if (i % 60 == 0) {
                minutes++;
            }
        }
        String name = GameFrame.popUp(new InputMessagePopup("Enter your name:"));
        GameFrame.popUp(new MessagePopup(name +
                ", you finished in " + minutes + " minutes and "
                + (elapsed - (60 * minutes)) + " seconds!"));
//        HighScoreUtil.addToHighScore(elapsed, name + " -- " + minutes + ":" + (elapsed - (60 * minutes)));
    }
}
