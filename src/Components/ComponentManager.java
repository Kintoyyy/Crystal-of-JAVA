package Components;

import Game.Handler;
import Views.ViewManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ComponentManager {
    private Handler handler;
    private ViewManager viewManager;
    private ArrayList<Component> objects;

    public ComponentManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.setHandler(viewManager.getHandler());
        objects = new ArrayList<Component>();
    }

    public void tick() {
        System.out.println("ComponentManager tick");
        for(Component o : objects) {
            o.tick();
        }
    }

    public void render(Graphics g) {
        for(Component o : objects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for(Component o : objects) {
            System.out.println(e.getX() + " " + e.getY());
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for(Component o : objects) {
            System.out.println(e.getX() + " " + e.getY());
            o.onMouseRelease(e);
        }
    }

    public void addObject(Component o) {
        objects.add(o);
    }

    public void removeObject(Component o) {
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
