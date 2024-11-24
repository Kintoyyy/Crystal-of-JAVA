package Game;

import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import Battle.BattleManager;
import Inputs.InputMouseListener;
import Inputs.InputKeyboardListener;
import Utils.DebugMode;
import Views.ViewManager;

public class Game implements Runnable {

    private Thread thread;
    private Display display;
    private final InputMouseListener inputMouseListener;
    private final InputKeyboardListener inputKeyboardListener;
    private ViewManager viewManager;
    private BattleManager battleManager;

    // might need to change this to a state manager
    private GameState gameState;

    private final int width;
    private final int height;
    private final String title;

    private boolean running = false;


    private DebugMode debugStats;

    //timing variables
    double delta;
    long now;
    long lastTime;
    long timer;
    int ticks;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        inputKeyboardListener = new InputKeyboardListener();
        inputMouseListener = new InputMouseListener();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(inputKeyboardListener);
        display.getFrame().addMouseListener(inputMouseListener);
        display.getFrame().addMouseMotionListener(inputMouseListener);
        display.getCanvas().addMouseListener(inputMouseListener);
        display.getCanvas().addMouseMotionListener(inputMouseListener);

        Handler handler = new Handler(this);

        viewManager = new ViewManager(handler);

        gameState = new GameState(handler);
        // set the debug mode
        debugStats = new DebugMode(handler);

        viewManager.initializeViews();
    }

    private void tick() { //updates all variables
        if (viewManager.hasLayers()) {
            viewManager.tick();
        }
    }


    private void render() { //renders all objects
        BufferStrategy bufferStrategy = display.getCanvas().getBufferStrategy();

        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        //Clears certain portion of screen (in this case the whole screen)
        graphics.clearRect(0, 0, width, height);

        // Draw here
        if (viewManager.hasLayers()) {
            viewManager.render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    //starting the thread runs this method
    public void run() {
        init();
        int fps = 60;
        double timePerTick = (double) 1000000000 / fps; //1 billion bcus 1 billion nanoseconds in one second
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            //System.out.println(delta);
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) { //if timer exceeds one second
//				System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public InputKeyboardListener getKeyManager() {
        return inputKeyboardListener;
    }

    public InputMouseListener getInputMouseListener() {
        return inputMouseListener;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public synchronized void start() {

        if (running) {
            return; //checks if already running
        }
        running = true;

        //runs this class on a new thread
        thread = new Thread(this);

        //calls the run method
        thread.start();
    }

    public synchronized void stop() {
        // Kills the game Quickly
        System.out.println("Exiting the game...");
        System.exit(0);

        if (!running) {
            return;
        }

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
