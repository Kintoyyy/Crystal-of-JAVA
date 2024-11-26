package Components;

import Components.Text.Text;
import Components.enums.States;
import Game.Handler;
import Inputs.InputKeyboardListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static Components.enums.States.*;

/**
 * The {@code Component} class represents a basic UI component with support for positioning,
 * dimensions, child components, and interactive states (such as hover and click).
 * This class serves as a base for creating more complex UI elements like buttons, labels, or panels.
 * <p>
 * It allows for hierarchical composition of components (parent-child relationships) and provides
 * functionality for user interaction (mouse movement, clicking, etc.). Components can also have
 * visual boundaries for debugging purposes.
 * </p>
 * <p>
 * The component's state changes in response to mouse events like hovering and pressing, and it can
 * notify its parent or child components for further action.
 * </p>
 *
 * <h3>Key Features:</h3>
 * <ul>
 *     <li>Positioning and resizing of components via {@code setLocation} and {@code setDimensions}.</li>
 *     <li>Support for parent-child relationships to create component hierarchies.</li>
 *     <li>State management for user interactions (e.g., {@code IDLE}, {@code HOVERED}, {@code PRESSED}).</li>
 *     <li>Recursive handling of mouse events to trigger actions on child components.</li>
 *     <li>Debugging support through visual bounds rendering.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * Component button = new ButtonComponent();
 * button.setLocation(100, 200)
 *       .setDimensions(150, 50)
 *       .showBounds()
 *       .scale(2);
 * </pre>
 *
 * @see Components.enums.States
 */
public abstract class Component {
    protected final Handler handler;
    protected float x = 0;
    protected float y = 0;
    protected int width = 0;
    protected int height = 0;
    protected int scale = 1;
    protected boolean showBounds = false;
    protected boolean moved = false;

    private Component parent;
    protected ArrayList<Component> childComponents = new ArrayList<>();
    protected Rectangle bounds = new Rectangle();
    protected States state = IDLE;

    /**
     * Constructor that initializes the component and updates its bounds.
     */
    public Component() {
        this.handler = Handler.getInstance();
        updateBounds();
    }

    /**
     * Abstract method to update the component's logic or state each frame.
     */
    public abstract void tick();

    /**
     * Abstract method to render the component to a {@code Graphics} context.
     *
     * @param g the {@code Graphics} object used for rendering
     */
    public abstract void render(Graphics g);

    /**
     * Abstract method to define the action taken when the component is clicked.
     */
    public abstract void onClick(MouseEvent e);

    /**
     * Updates the component's bounding rectangle based on its position and size.
     */
    public void updateBounds() {
        int parentX = parent != null ? (int) parent.x : 0;
        int parentY = parent != null ? (int) parent.y : 0;
        bounds.setBounds(parentX + (int) x, parentY + (int) y, width, height);
    }

    public InputKeyboardListener isKeyPressed(String key) {
        return handler.getKeyManager().isKeyPressed(key);
    }

    /**
     * Handles mouse movement over the component. Updates the component's state
     * (e.g., HOVERED or IDLE) based on whether the mouse is inside the component's bounds.
     *
     * @param e the {@code MouseEvent} triggered by mouse movement
     */
    public void onMouseMove(MouseEvent e) {
        moved = bounds.contains(e.getX(), e.getY());
        state = moved ? HOVERED : IDLE;

        for (Component component : childComponents) {
            component.onMouseMove(e);
        }
    }

    /**
     * Handles mouse release events (e.g., a click action) on the component.
     * If the component is in a HOVERED state, it is marked as PRESSED and the click action is triggered.
     *
     * @param e the {@code MouseEvent} triggered by mouse release
     */
    public void onMouseRelease(MouseEvent e) {
        if ((state == HOVERED || moved)) {
            state = PRESSED;
            onClick(e);
        }
        for (Component component : childComponents) {
            component.onMouseRelease(e);
        }
    }

    /**
     * Sets the location of the component (x, y coordinates) and updates its bounds.
     *
     * @param x the x-coordinate of the component
     * @param y the y-coordinate of the component
     * @return the current {@code Component} instance
     */
    public Component setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        updateBounds();
        return this;
    }

    /**
     * Sets the dimensions (width and height) of the component and updates its bounds.
     *
     * @param width  the width of the component
     * @param height the height of the component
     * @return the current {@code Component} instance
     */
    public Component setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        updateBounds();
        return this;
    }

    /**
     * Sets the parent component for this component. Updates the component's bounds relative to its parent.
     *
     * @param parent the parent component
     * @return the current {@code Component} instance
     */
    public Component setParent(Component parent) {
        this.parent = parent;
        updateBounds();
        return this;
    }

    /**
     * Adds child components to this component. Child components will be rendered and ticked recursively.
     *
     * @param componentsArray the child components to be added
     */
    public void addChildren(Component... componentsArray) {
        childComponents.addAll(Arrays.asList(componentsArray));
    }

    /**
     * Renders all child components in the current component.
     *
     * @param g the {@code Graphics} object used for rendering
     */
    public void renderChildren(Graphics g) {
        for (Component component : childComponents) {
            component.render(g);
        }
    }

    /**
     * Calls the {@code onClick} method of all child components.
     */
    public void onClickChildren(MouseEvent e) {
        for (Component component : childComponents) {
            component.onClick(e);
        }
    }

    /**
     * Calls the {@code tick} method of all child components.
     */
    public void tickChildren() {
        for (Component component : childComponents) {
            component.tick();
        }
    }

    /**
     * Enables bounds rendering for debugging purposes.
     *
     * @return the current {@code Component} instance
     */
    public Component showBounds() {
        this.showBounds = true;
        return this;
    }

    /**
     * Scales the component by a factor and updates its dimensions.
     *
     * @param i the scaling factor
     * @return the current {@code Component} instance
     */
    public Component scale(int i) {
        this.scale = i;
        setDimensions(width * i, height * i);
        return this;
    }

    /**
     * Returns the width of the component.
     *
     * @return the width of the component
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the component.
     *
     * @return the height of the component
     */
    protected int getHeight() {
        return height;
    }

    public void drawImage(Graphics g, Image image, int x, int y, int width, int height) {
        g.drawImage(image, bounds.x + (x * scale), bounds.y + (y * scale), width * scale, height * scale, null);
    }

    public void drawString(Graphics g, String text, int x, int y, Font font, Color color) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, bounds.x + (x * scale), bounds.y + (y * scale));
    }


}
