package Components;

import Game.Handler;
import Views.ViewManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ComponentManager {
    private Handler handler;
    private final ArrayList<Component> components = new ArrayList<>();

    public ComponentManager(ViewManager viewManager) {
        setHandler(viewManager.getHandler());
        handler.getInputMouseListener().setComponentManager(this);
    }

    public void tick() {
        for (Component component : components) {
            component.tick();
        }
    }

    public void render(Graphics g) {
        for (Component component : components) {
            component.render(g);
            component.showBounds();
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (Component component : components) {
            component.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (Component component : components) {
            component.onMouseRelease(e);
        }
    }

    public void init(Component... componentsArray) {
        components.addAll(Arrays.asList(componentsArray));
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
