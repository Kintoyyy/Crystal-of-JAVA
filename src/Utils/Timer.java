package Utils;

public class Timer {
    private CallBackAction callBackAction;
    private int elapsedTime = 0;
    private int delayInTicks = 0;
    private Timer nextTimer; // Reference to the next timer in the chain
    private boolean active = false; // Flag to control if the timer is currently active
    private int tickRate = 60; // Default tick rate of 60 ticks per second

    public Timer() { }

    public Timer setDelay(int delayInSeconds) {
        this.delayInTicks = delayInSeconds * tickRate; // Set delay in ticks based on tick rate
        return this;
    }

    public Timer setAction(CallBackAction callBackAction) {
        this.callBackAction = callBackAction;
        return this;
    }

    public Timer chainNext(Timer nextTimer) {
        this.nextTimer = nextTimer; // Set the next timer in the chain
        return this;
    }

    public Timer start() {
        this.active = true; // Set this timer as active
        this.elapsedTime = 0; // Reset elapsed time
        return this;
    }

    public void update() {
        // Only update if the timer is active
        if (!active) {
            return;
        }

        elapsedTime++;

        // Check if the elapsed time has reached the delay
        if (elapsedTime >= delayInTicks) {
            // Perform the delayed action
            if (callBackAction != null) {
                callBackAction.onAction();
            } else {
                System.out.println("Warning: No action defined for this timer.");
            }

            // Deactivate this timer and activate the next in the chain
            active = false;

            //TODO: need to fix this
            if (nextTimer != null) {
                System.out.println("Starting next timer in chain.");
                nextTimer.start();
            }
        }
    }

    public void reset() {
        active = false;
        elapsedTime = 0;
    }

    public double getTime() {
        double decimalTime = (double) elapsedTime / tickRate;
        return Math.round(decimalTime * 100.0) / 100.0;
    }

    public boolean isDone() {
        return !active;
    }
}
