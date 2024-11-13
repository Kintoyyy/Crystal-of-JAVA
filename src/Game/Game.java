package Game;

import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import Assets.Assets;
import Assets.Transition;
import Inputs.InputMouseListener;
import Inputs.InputKeyboardManager;
import Utils.DebugMode;
import Views.ViewManager;

public class Game implements Runnable {

    private Display display;

    private final int width;
    private final int height;
    private final String title;

    private boolean running = false;

    public static boolean flag = false;
    public static boolean flag2 = false;
    public static boolean battling = false;

    private Thread thread; //own separate game loop

    private BufferStrategy bs;
    private Graphics g;

    private GameState gameState;

    //Input
    private InputKeyboardManager inputKeyboardManager;

    private GameCamera gameCamera;

    private Handler handler;
    private Transition transition;

    private DebugMode debugMode;
    private ViewManager viewManager;
    private InputMouseListener inputMouseListener;


    private int fps;
    private double timePerTick;

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
        inputKeyboardManager = new InputKeyboardManager();
        inputMouseListener = new InputMouseListener();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(inputKeyboardManager);

        display.getFrame().addMouseListener(inputMouseListener);
        display.getFrame().addMouseMotionListener(inputMouseListener);
        display.getCanvas().addMouseListener(inputMouseListener);
        display.getCanvas().addMouseMotionListener(inputMouseListener);
        Assets.init();

        handler = new Handler(this);

        gameState = new GameState(handler);

        // set the debug mode
        debugMode = new DebugMode(handler);

        // set the game state
        handler.setGameState(gameState);

        gameCamera = new GameCamera(handler, 0, 0);

        viewManager = new ViewManager(handler);
    }

    private void tick() { //updates all variables
        inputKeyboardManager.tick();

        if (viewManager.hasLayers()) {
            viewManager.tick();
        }

        gameState.tick();

//		if(State.getState() != null) {
//			State.getState().tick();
//		}

//        if (flag) {
//            flag = false;
//            transition = new Transition();
//            flag2 = true;
//        }
//        if (Transition.canStart) {
//            Transition.canStart = false;
//            battling = true;
//            battleState = new BattleState(handler);
////				State.setState(handler.getGame().battleState);
//        }
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

//        if (flag2) {
//            transition.render(g);
//        }

        g.drawString("FPS: " + ticks, 10, 10);

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

    public InputKeyboardManager getKeyManager() {
        return inputKeyboardManager;
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
