package Views;

import Components.ComponentManager;
import Game.Handler;

import java.awt.*;

/**
 * The abstract View class represents a screen or view in the game's UI.
 * It provides the structure for managing and rendering components as well as handling
 * the tick-based updates for each view.
 *
 * <p>The View class is intended to be extended by concrete view classes, which will
 * implement the {@link #render(Graphics)} and {@link #tick()} methods to define the
 * specific behavior and visuals of that particular view.</p>
 *
 * <p>Each view is associated with a {@link ViewManager}, a {@link ComponentManager},
 * and a {@link Handler} to manage components and game state interactions.</p>
 *
 * <p>Fields:</p>
 * <ul>
 *     <li>{@link #handler} - Manages the game state and input interactions.</li>
 *     <li>{@link #viewManager} - Manages the transitions between different views.</li>
 *     <li>{@link #components} - Manages the UI components that belong to this view.</li>
 *     <li>{@link #isOverlay} - Indicates whether the view is an overlay (optional UI layer).</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * public class MainMenuView extends View {
 *     public MainMenuView(ViewManager viewManager) {
 *         super(viewManager);
 *     }
 *
 *     &#064;Override
 *     public void render(Graphics g) {
 *         // Custom rendering logic for the main menu
 *     }
 *
 *     &#064;Override
 *     public void tick() {
 *         // Custom update logic for the main menu
 *     }
 * }
 * </pre>
 */
public abstract class View {
    protected final Handler handler = Handler.getInstance();
    protected final ViewManager viewManager = handler.getViewManager();
    protected final ComponentManager components;
    protected boolean isOverlay = false;

    /**
     * Constructor for the View class.
     *
     */
    public View() {
        this.components = new ComponentManager(viewManager);
    }

    /**
     * Returns the ComponentManager responsible for managing UI components in this view.
     *
     * @return The ComponentManager instance for the current view.
     */
    public ComponentManager getComponentManager() {
        return components;
    }

    /**
     * Abstract method to render the visuals of the view.
     * Concrete implementations of this method should define how the view is drawn.
     *
     * @param g The Graphics object used for rendering the view.
     */
    public abstract void render(Graphics g);

    /**
     * Abstract method to update the state of the view.
     * Concrete implementations of this method should define how the view updates each tick.
     */
    public abstract void tick();

    public abstract void setData(Object data);
}
