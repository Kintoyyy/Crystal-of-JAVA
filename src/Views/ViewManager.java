package Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;

import Game.Handler;
import Inputs.InputMouseListener;
import Views.Game.Game;
import Views.Game.DialogScene;
import Views.Menu.Menu;
import Views.Menu.Setting;
import Views.Battle.Battle;
import Views.Overlay.Pause;
import Views.enums.Views;

public class ViewManager {
    private final EnumMap<Views, View> views = new EnumMap<>(Views.class);
    private final ArrayList<View> layers = new ArrayList<>();
    private final Handler handler;
    private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 128);
    private final InputMouseListener inputMouseListener;

    /**
     * Constructor for the ViewManager class.
     *
     * @param handler The Handler responsible for managing game state and input.
     */
    public ViewManager(Handler handler) {
        handler.setViewManager(this);
        this.handler = handler;
        this.inputMouseListener = handler.getInputMouseListener();
    }


    /**
     * Initializes the available views for the game.
     * This method creates instances of all views and stores them in the {@link #views} map.
     */
    public void initializeViews() {
        views.put(Views.GAME, new Game());
        views.put(Views.MENU, new Menu());
        views.put(Views.SETTINGS, new Setting());
        views.put(Views.SELECT_CHARACTER, new Menu());
        views.put(Views.PAUSE, new Pause());
        views.put(Views.BATTLE, new Battle());
        views.put(Views.DIALOG, new DialogScene());
        setView(Views.MENU);
    }

    public void customView(View customView) {
        if (customView.isOverlay) {
            if (!layers.getLast().isOverlay) {
                layers.add(customView);
            }
        } else {
            layers.clear();
            layers.add(customView);
        }
        updateInputListener();
    }

    public void overlayView() {

    }

    public void setView(Views viewEnum) {
        View selectedView = views.get(viewEnum);
        if (selectedView == null) return;

        if (selectedView.isOverlay) {
            if (layers.getLast().isOverlay) {
                layers.removeLast();
            }
            layers.add(selectedView);
        } else {
            layers.clear();
            layers.add(selectedView);
        }

        updateInputListener();
    }

    public boolean isViewActive(Views viewEnum) {
        return layers.contains(views.get(viewEnum));
    }


    private void updateInputListener() {
        View topLayer = getTopLayer();
        if (topLayer != null) {
            inputMouseListener.setComponentManager(topLayer.getComponentManager());
        }
    }

    private View getTopLayer() {
        return layers.isEmpty() ? null : layers.getLast();
    }

    private void applyOverlay(Graphics g) {
        g.setColor(OVERLAY_COLOR);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
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
            for (View layer : layers) {
                layer.tickLayer();
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).renderLayer(g);
            if (i < layers.size() - 1) {
                applyOverlay(g);
            }
        }
    }

    public void updateViewData(Views viewEnum, Object data) {
        View view = views.get(viewEnum);
        if (view != null) {
            view.setData(data);
        }
    }
}
