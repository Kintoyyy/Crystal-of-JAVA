package Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;

import Components.ComponentManager;
import Game.Handler;
import Views.Battle.BattleView;
import Views.Game.GameView;
import Views.Menu.MenuView;
import Views.Menu.Setting;
import Views.Overlay.Pause;
import enums.ViewEnums;

public class ViewManager {
    private final EnumMap<ViewEnums, View> views = new EnumMap<>(ViewEnums.class);

    private final ArrayList<View> layers = new ArrayList<>();
    private final Handler handler;

    // Semi-transparent gray color for overlay effect
    private static final Color OVERLAY_COLOR = new Color(200, 200, 200, 128);

    public ViewManager(Handler handler) {
        this.handler = handler;
        this.handler.setViewManager(this);

        // Initialize views and set default layer
        views.put(ViewEnums.BATTLE, new BattleView(this));
        views.put(ViewEnums.GAME, new GameView(this));
        views.put(ViewEnums.MENU, new MenuView(this));
        views.put(ViewEnums.SETTINGS, new MenuView(this));
        views.put(ViewEnums.SELECT_CHARACTER, new MenuView(this));
        views.put(ViewEnums.PAUSE, new Pause(this));
//        views.put(ViewEnums.SETTINGS, new Setting(this));

        // initialize the game view
        layers.add(views.get(ViewEnums.MENU));
    }

    public void setView(ViewEnums viewEnum) {
        View selectedView = views.get(viewEnum);

        if (selectedView == null) {
            return;  // Exit if view does not exist
        }

        handler.getInputMouseListener().setComponentManager(selectedView.getComponentManager());

        if (selectedView.isOverlay) {
            layers.add(selectedView);
            return;
        }

        layers.clear();
        layers.add(selectedView);

        // TODO: need to refactor this please :(
//        handler.getInputMouseListener().setComponentManager(layers.getLast().getComponentManager());
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
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.GRAY);
        g.drawString("Layers: " + layers, 10, 20);
    }


    private void applyOverlay(Graphics g) {

        g.setColor(OVERLAY_COLOR);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
    }

    public boolean isInGame() {
        return layers.contains(views.get(ViewEnums.GAME));
    }
}
