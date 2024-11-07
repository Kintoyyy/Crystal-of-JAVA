package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button implements MouseListener {

    private int x, y, width, height;
    private String text;
    private boolean hovered = false;

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void render(Graphics g) {
        // Change color based on hover state
        g.setColor(hovered ? Color.LIGHT_GRAY : Color.DARK_GRAY);
        g.fillRect(x, y, width, height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(text, x + (width / 2) - (g.getFontMetrics().stringWidth(text) / 2), y + (height / 2) + 7);
    }

    public void tick() {
        // Here you can add logic to update the button state
    }

    public void mouseClicked(MouseEvent e) {
        // Check if the button is clicked
        if (e.getButton() == MouseEvent.BUTTON1) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                Game.flag = true; // Trigger transition on button click
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        hovered = true;
    }

    public void mouseExited(MouseEvent e) {
        hovered = false;
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}

/*

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

    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    // States
    public State gameState;
    public State menuState;
    public State battleState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    private GameCamera gameCamera;

    private Handler handler;
    private Transition transition;

    private DebugMode debugMode;
    private ViewManager viewManager;
    private InputMouseListener inputMouseListener;

    private Button startButton; // Button to start the game

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
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        debugMode = new DebugMode(handler);
        viewManager = new ViewManager(handler);

        startButton = new Button(width / 2 - 100, height / 2 - 50, 200, 100, "Start Battle"); // Centered button
        display.getFrame().addMouseListener(startButton); // Register button mouse listener
    }

    private void tick() { // Updates all variables
        keyManager.tick();
        viewManager.tick();

        startButton.tick(); // Update button state

        if (flag) {
            flag = false;
            transition = new Transition();
            flag2 = true;
        }
        if (Transition.canStart) {
            Transition.canStart = false;
            battling = true;
            battleState = new BattleState(handler);
        }
    }

    private void render() { // Renders all objects
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // Clears the screen
        g.clearRect(0, 0, width, height);

        // Draw stuff on the screen
        if (viewManager.hasLayers()) {
            viewManager.render(g);
        }

        if (flag2) {
            transition.render(g);
        }

        // Render the button
        startButton.render(g);

        // End drawings
        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        fps = 60;
        timePerTick = 1000000000 / fps;
        delta = 0;
        lastTime = System.nanoTime();
        timer = 0;
        ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1000000000) {
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


 */