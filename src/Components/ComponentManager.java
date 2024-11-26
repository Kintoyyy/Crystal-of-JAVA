package Components;

import Game.Handler;
import Views.ViewManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ComponentManager {
    private final ArrayList<UIComponent> components = new ArrayList<>();

    public ComponentManager() {
        Handler handler = Handler.getInstance();
        handler.getInputMouseListener().setComponentManager(this);
    }

    public void addComponents(UIComponent... componentArray) {
        for (UIComponent component : componentArray) {
            if (component != null) {
                components.add(component);
            }
        }
    }

    public void tick() {
        components.forEach(UIComponent::tickComponent);
    }

    public void render(Graphics g) {
        components.forEach(component -> component.renderComponent(g));
    }

    public void onMouseMove(MouseEvent e) {
        components.forEach(component -> component.handleMouseMove(e));
    }

    public void onMouseRelease(MouseEvent e) {
        components.forEach(component -> component.handleMouseClick(e));
    }
}
