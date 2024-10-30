package Views;

import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

abstract class View {

    Handler handler;
    ViewManager manager;
    protected boolean isOverlay = false;

    public View(ViewManager viewManager) {
        this.manager = viewManager;
        this.handler = viewManager.getHandler();
    }

    public abstract void render(Graphics g);
    public abstract void tick();
}
