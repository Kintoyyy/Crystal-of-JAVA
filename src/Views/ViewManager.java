package Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;

import Game.Handler;
import Views.Battle.Battle;
import Views.Game.Game;
import Views.Menu.Menu;
import Views.Overlay.Pause;
import Views.enums.Views;
/**
 * The ViewManager class manages different views and their respective layers in the game.
 * It is responsible for transitioning between views, handling overlays, and managing
 * the rendering and updating of each view's state.
 *
 * <p>Views represent different screens or states in the game, such as the main menu,
 * game screen, battle, pause overlay, etc. The ViewManager handles these views and allows
 * for easy switching between them. It also supports layering of views, allowing for overlays
 * such as pause screens to be displayed on top of other views.</p>
 *
 * <p>The ViewManager maintains an EnumMap of available views and an ArrayList to track the
 * current layers of views. Each view is responsible for its own rendering and updating logic.</p>
 *
 * <p>Fields:</p>
 * <ul>
 *     <li>{@link #views} - A map storing available views by their enum representation.</li>
 *     <li>{@link #layers} - A list of currently active views (layers), with the topmost view rendered last.</li>
 *     <li>{@link #handler} - The Handler instance used to manage game state and input.</li>
 *     <li>{@link #OVERLAY_COLOR} - The color of the overlay applied to non-top layers when rendering.</li>
 * </ul>
 *
 * <p>Usage Example:</p>
 * <pre>
 * ViewManager viewManager = new ViewManager(handler);
 * viewManager.setView(Views.MENU); // Set the current view to the main menu
 * viewManager.removeView(Views.PAUSE); // Remove the pause overlay view
 * </pre>
 */
public class ViewManager {
    private final EnumMap<Views, View> views = new EnumMap<>(Views.class);
    private final ArrayList<View> layers = new ArrayList<>();
    private final Handler handler;
    private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 128);

    /**
     * Constructor for the ViewManager class.
     *
     * @param handler The Handler responsible for managing game state and input.
     */
    public ViewManager(Handler handler) {
        this.handler = handler;
        this.handler.setViewManager(this);
        initializeViews();

        setView(Views.GAME);
    }

    /**
     * Initializes the available views for the game.
     * This method creates instances of all views and stores them in the {@link #views} map.
     */
    private void initializeViews() {
        views.put(Views.BATTLE, new Battle(this));
        views.put(Views.GAME, new Game(this));
        views.put(Views.MENU, new Menu(this));
        views.put(Views.SETTINGS, new Menu(this));
        views.put(Views.SELECT_CHARACTER, new Menu(this));
        views.put(Views.PAUSE, new Pause(this));
    }

    /**
     * Sets the current view for the game. If the view is an overlay, it is added to the layers list,
     * otherwise, the layers list is cleared and the new view is added as the sole view.
     *
     * @param viewEnum The enum representing the view to set.
     */
    public void setView(Views viewEnum) {
        View selectedView = views.get(viewEnum);

        if (selectedView == null) return;

        handler.getInputMouseListener().setComponentManager(selectedView.getComponentManager());

        if (selectedView.isOverlay) {
            layers.add(selectedView);
        } else {
            layers.clear();
            layers.add(selectedView);
        }

        // Update mouse listener to always point to the last (topmost) layer's component manager
        View topLayer = layers.getLast();
        handler.getInputMouseListener().setComponentManager(topLayer.getComponentManager());
    }

    /**
     * Removes a view from the layers stack, typically for overlay views.
     * The topmost layer is always updated as the current layer.
     *
     * @param viewEnum The enum representing the view to remove.
     */
    public void removeView(Views viewEnum) {
        View selectedView = views.get(viewEnum);
        if (selectedView == null) return;

        // Check if the selected view is in the layers list, and remove it
        if (layers.contains(selectedView) && layers.size() > 1) {
            layers.remove(selectedView);
        }

        // If the layers list is not empty, update the mouse listener to the topmost layer
        if (!layers.isEmpty()) {
            View topLayer = layers.getLast();
            handler.getInputMouseListener().setComponentManager(topLayer.getComponentManager());
        }
    }

    /**
     * Checks if there are any active layers.
     *
     * @return true if there are active layers, false otherwise.
     */
    public boolean hasLayers() {
        return !layers.isEmpty();
    }

    /**
     * Returns the Handler associated with this ViewManager.
     *
     * @return The Handler instance managing the game state and input.
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Updates the state of all active views (ticks each layer).
     */
    public void tick() {
        if (!layers.isEmpty()) {
            layers.forEach(View::tick);
        }
    }

    /**
     * Renders all active views in the layers stack. An overlay effect is applied to non-top layers.
     *
     * @param g The Graphics object used for rendering the views.
     */
    public void render(Graphics g) {
        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).render(g);
            if (i < layers.size() - 1) {
                applyOverlay(g);  // Apply overlay for non-top layers
            }
        }

        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.GRAY);
        g.drawString("Layers: " + layers, 10, 20);
    }

    /**
     * Applies a semi-transparent overlay over the screen, used for non-top layers.
     *
     * @param g The Graphics object used for rendering the overlay.
     */
    private void applyOverlay(Graphics g) {
        g.setColor(OVERLAY_COLOR);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
    }

    /**
     * Checks if the game view is currently active in the layers stack.
     *
     * @return true if the game view is active, false otherwise.
     */
    public boolean isInGame() {
        return layers.contains(views.get(Views.GAME));
    }
}
