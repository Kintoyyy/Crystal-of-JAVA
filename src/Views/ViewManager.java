package Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import Components.Component;
import Components.ComponentManager;
import Game.Handler;
import enums.ViewEnums;

public class ViewManager {
    private final HashMap<ViewEnums, View> views = new HashMap<>();
    private final ArrayList<View> layers = new ArrayList<>();
    private final Handler handler;
    private ComponentManager componentManager;

    // Semi-transparent gray color for overlay effect
    private static final Color OVERLAY_COLOR = new Color(200, 200, 200, 128);

    public ViewManager(Handler handler) {
        this.handler = handler;
        this.handler.setViewManager(this);

        // Initialize views and set default layer
        views.put(ViewEnums.BATTLE, new BattleView(this));
        views.put(ViewEnums.GAME, new GameView(this));
        views.put(ViewEnums.MAIN_MENU, new MenuView(this));
        views.put(ViewEnums.START_MENU, new StartMenuView(this));

        layers.add(views.get(ViewEnums.START_MENU));
    }

    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public void setView(ViewEnums viewEnum) {
        View selectedView = views.get(viewEnum);
        if (selectedView == null) {
            return;  // Exit if view does not exist
        }

        layers.clear();
        layers.add(selectedView);

        // Optionally add overlay management here if needed
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
            if (i < layers.size() - 1) {  // Apply overlay if not last layer
                applyOverlay(g);
            }
        }
    }


    private void applyOverlay(Graphics g) {
        g.setColor(OVERLAY_COLOR);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
    }
}
