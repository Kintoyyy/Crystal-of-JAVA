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

public class ViewManager {
    private final EnumMap<Views, View> views = new EnumMap<>(Views.class);
    private final ArrayList<View> layers = new ArrayList<>();
    private final Handler handler;
    private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 128);

    public ViewManager(Handler handler) {
        this.handler = handler;
        this.handler.setViewManager(this);
        initializeViews();

        setView(Views.GAME);
    }

    private void initializeViews() {
        views.put(Views.BATTLE, new Battle(this));
        views.put(Views.GAME, new Game(this));
        views.put(Views.MENU, new Menu(this));
        views.put(Views.SETTINGS, new Menu(this));
        views.put(Views.SELECT_CHARACTER, new Menu(this));
        views.put(Views.PAUSE, new Pause(this));
    }

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

    public boolean hasLayers() {
        return !layers.isEmpty();
    }

    public Handler getHandler() {
        return handler;
    }

    public void tick() {
        if (!layers.isEmpty()) {
            layers.forEach(View::tick);
        }
    }

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

    private void applyOverlay(Graphics g) {
        g.setColor(OVERLAY_COLOR);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
    }

    public boolean isInGame() {
        return layers.contains(views.get(Views.GAME));
    }
}
