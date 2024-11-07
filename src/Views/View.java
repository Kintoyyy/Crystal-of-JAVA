package Views;

import Components.ComponentManager;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

abstract class View {

    final Handler handler;
    final ViewManager manager;
    final ComponentManager components;
    protected boolean isOverlay = false;


    public View(ViewManager viewManager) {
        this.manager = viewManager;
        this.handler = viewManager.getHandler();
        this.components = new ComponentManager(viewManager);
    }

    public abstract void render(Graphics g);
    public abstract void tick();
}
