package Components;

import Game.Handler;
import Views.ViewManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manages the UI components in the game, handling the update and rendering of all components,
 * as well as mouse events.
 */
public class ComponentManager {
    private Handler handler;
    private final ArrayList<Component> components = new ArrayList<>();

    /**
     * Constructs a ComponentManager to manage UI components and listens to mouse input.
     *
     * @param viewManager the ViewManager instance used to get the handler and input listener.
     */
    public ComponentManager(ViewManager viewManager) {
        setHandler(viewManager.getHandler());
        handler.getInputMouseListener().setComponentManager(this);
    }

    /**
     * Updates the state of all components.
     */
    public void tick() {
        for (Component component : components) {
            component.tick();
            if (component.getHandler() == null) {
                component.setHandler(handler);
            }
        }
    }

    /**
     * Renders all components to the screen.
     *
     * @param g the Graphics object used to draw the components.
     */
    public void render(Graphics g) {
        for (Component component : components) {
            component.render(g);
        }
    }

    /**
     * Handles mouse movement events and passes them to all components.
     *
     * @param e the MouseEvent containing mouse movement data.
     */
    public void onMouseMove(MouseEvent e) {
        for (Component component : components) {
            component.onMouseMove(e);
        }
    }

    /**
     * Handles mouse release events and passes them to all components.
     *
     * @param e the MouseEvent containing mouse release data.
     */
    public void onMouseRelease(MouseEvent e) {
        for (Component component : components) {
            component.onMouseRelease(e);
        }
    }

    /**
     * Initializes the ComponentManager with the specified components.
     *
     * @param componentsArray the components to add to the manager.
     */
    public void init(Component... componentsArray) {
        components.addAll(Arrays.asList(componentsArray));
    }

    /**
     * Removes a specified component from the manager.
     *
     * @param component the component to remove.
     */
    public void removeComponent(Component component) {
        components.remove(component);
    }

    /**
     * Returns the Handler associated with this ComponentManager.
     *
     * @return the Handler instance.
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Sets the Handler for this ComponentManager.
     *
     * @param handler the Handler instance to set.
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
