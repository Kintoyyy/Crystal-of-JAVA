package Views;

import Components.ComponentManager;
import Game.Handler;

import java.awt.*;

public abstract class View {

    protected final Handler handler;
    protected  ViewManager viewManager;
    protected final ComponentManager components;
    protected boolean isOverlay = false;

    public View(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.handler = viewManager.getHandler();
        this.components = new ComponentManager(viewManager);
    }

    public ComponentManager getComponentManager() {
        return components;
    }

    public abstract void render(Graphics g);
    public abstract void tick();
}
