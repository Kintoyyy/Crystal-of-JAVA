package Utils;
/**
 * The Timer class represents a simple timer that can execute an action after a specified delay.
 * The timer operates in ticks (with a default of 60 ticks per second) and can be chained with other timers to execute actions sequentially.
 * The class supports setting a delay, specifying an action to perform when the timer expires, and starting/stopping the timer.
 *
 * <p>Features:</p>
 * <ul>
 *   <li>Set delay in seconds (converted to ticks based on tick rate).</li>
 *   <li>Attach an action to execute when the timer expires.</li>
 *   <li>Chain multiple timers to execute actions in sequence.</li>
 *   <li>Reset the timer and check if it's finished.</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * Timer timer1 = new Timer()
 *     .setDelay(5) // 5 seconds delay
 *     .setAction(() -> System.out.println("Timer 1 finished"))
 *     .start();
 *
 * Timer timer2 = new Timer()
 *     .setDelay(2) // 2 seconds delay after timer1
 *     .setAction(() -> System.out.println("Timer 2 finished"))
 *     .chainNext(timer1)
 *     .start();
 * </pre>
 */
public class Timer {
    /**
     * The action to be executed when the timer expires.
     */
    private CallBackAction callBackAction;

    /**
     * The time in ticks that the timer has been running.
     */
    private int elapsedTime = 0;

    /**
     * The delay for the timer in ticks (based on seconds and tick rate).
     */
    private int delayInTicks = 0;

    /**
     * Reference to the next timer in the chain to be triggered after this timer expires.
     */
    private Timer nextTimer;

    /**
     * Flag to track if the timer is currently active or not.
     */
    private boolean active = false;

    /**
     * The tick rate of the game or system, defaulted to 60 ticks per second.
     */
    private final int tickRate = 60;

    /**
     * Default constructor to create a new Timer.
     */
    public Timer() { }

    /**
     * Sets the delay for the timer in seconds. The delay is converted to ticks based on the tick rate.
     *
     * @param delayInSeconds The delay in seconds before the timer should expire.
     * @return The current Timer instance.
     */
    public Timer setDelay(int delayInSeconds) {
        this.delayInTicks = delayInSeconds * tickRate;
        return this;
    }

    /**
     * Sets the action to be executed when the timer expires.
     *
     * @param callBackAction The action to execute upon timer completion.
     * @return The current Timer instance.
     */
    public Timer setAction(CallBackAction callBackAction) {
        this.callBackAction = callBackAction;
        return this;
    }

    /**
     * Chains another timer to execute after the current timer expires.
     *
     * @param nextTimer The next timer to execute after this one finishes.
     * @return The current Timer instance.
     */
    public Timer chainNext(Timer nextTimer) {
        this.nextTimer = nextTimer;
        return this;
    }

    /**
     * Starts the timer, resetting its elapsed time and setting it to active.
     *
     * @return The current Timer instance.
     */
    public Timer start() {
        this.active = true;
        this.elapsedTime = 0;
        return this;
    }

    /**
     * Updates the timer. It increments the elapsed time and checks if the timer has finished.
     * If the timer has completed, it executes the callback action and proceeds to the next timer if chained.
     */
    public void update() {
        if (!active) {
            return;
        }

        elapsedTime++;

        // Check if the timer has reached its delay
        if (elapsedTime >= delayInTicks) {
            if (callBackAction != null) {
                callBackAction.onAction();
            } else {
                System.out.println("Warning: No action defined for this timer.");
            }

            // Deactivate this timer and start the next timer if it exists
            active = false;

            if (nextTimer != null) {
                System.out.println("Starting next timer in chain.");
                nextTimer.start();
            }
        }
    }

    /**
     * Resets the timer, deactivating it and clearing the elapsed time.
     */
    public void reset() {
        active = false;
        elapsedTime = 0;
    }

    /**
     * Gets the elapsed time as a decimal value in seconds.
     *
     * @return The elapsed time in seconds, rounded to two decimal places.
     */
    public double getTime() {
        double decimalTime = (double) elapsedTime / tickRate;
        return Math.round(decimalTime * 100.0) / 100.0;
    }

    /**
     * Checks if the timer has finished (i.e., is inactive).
     *
     * @return True if the timer is inactive, false otherwise.
     */
    public boolean isDone() {
        return !active;
    }

    public boolean isActive() {
        return active;
    }
}
