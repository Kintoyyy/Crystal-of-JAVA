package Game;

import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import Assets.Assets;
import Assets.Transition;
import Inputs.InputMouseListener;
import States.BattleState;
import States.State;
import Utils.DebugMode;
import Views.ViewManager;

public class Game implements Runnable {

    private Display display;

    private final int width;
    private final int height;
    public final String title;

    public boolean running = false;

    public static boolean flag = false;
    public static boolean flag2 = false;
    public static boolean battling = false;

    private Thread thread; //own separate game loop

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State battleState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    private GameCamera gameCamera;

    private Handler handler;
    private Transition transition;

    private DebugMode debugMode;
    private ViewManager viewManager;
    private InputMouseListener inputMouseListener;


    private int fps;
    private double timePerTick;

    double delta;
    long now;
    long lastTime;
    long timer;
    int ticks;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        inputMouseListener = new InputMouseListener();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);

        display.getFrame().addMouseListener(inputMouseListener);
        display.getFrame().addMouseMotionListener(inputMouseListener);
        display.getCanvas().addMouseListener(inputMouseListener);
        display.getCanvas().addMouseMotionListener(inputMouseListener);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        debugMode = new DebugMode(handler);
        viewManager = new ViewManager(handler);
    }

    private void tick() { //updates all variables
        keyManager.tick();

        if (viewManager.hasLayers()) {
            viewManager.tick();
        }

//		if(State.getState() != null) {
//			State.getState().tick();
//		}

        if (flag) {
            flag = false;
            transition = new Transition();
            flag2 = true;
        }
        if (Transition.canStart) {
            Transition.canStart = false;
            battling = true;
            battleState = new BattleState(handler);
//				State.setState(handler.getGame().battleState);
        }
    }


    private void render() { //renders all objects
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //Clears certain portion of screen (in this case the whole screen)
        g.clearRect(0, 0, width, height);

        //Draws stuff in the screen-

        if (viewManager.hasLayers()) {
            viewManager.render(g);
        }

//		if(State.getState() != null) {
//			State.getState().render(g);
//		}

        if (flag2) {
            transition.render(g);
        }

        //End drawings-
        bs.show();
        g.dispose();
    }


    //starting the thread runs this method
    public void run() {
        init();
        fps = 60;
        timePerTick = 1000000000 / fps; //1 billion bcus 1 billion nanoseconds in one second
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

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public InputMouseListener getInputMouseListener() {
        return inputMouseListener;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
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
