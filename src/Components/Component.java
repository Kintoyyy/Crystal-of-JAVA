package Components;

import enums.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static enums.ComponentStateEnums.*;

public abstract class Component {

    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected boolean showBounds = false;
    private Component parent;
    private final ArrayList<Component> childComponents;

    protected Rectangle bounds;
    protected ComponentStateEnums state = IDLE;
    protected boolean hovering = false;
    protected boolean moved;

    public Component(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x, y, width, height);
        childComponents = new ArrayList<Component>();
        updateBounds();
    }

    abstract public void tick();

    abstract public void render(Graphics g);

    public abstract void onClick();

    public void updateBounds() {
        int parentX = parent != null ? (int) parent.x : 0;
        int parentY = parent != null ? (int) parent.y : 0;
        bounds.x = parentX + (int) this.x;
        bounds.y = parentY + (int) this.y;
        bounds.width = this.width;
        bounds.height = this.height;
    }

    public void onMouseMove(MouseEvent e) {
        moved = true;
        System.out.println(bounds.contains(e.getX(), e.getY()));
        if (bounds.contains(e.getX(), e.getY())) {
            state = HOVERED;
            hovering = true;
        } else {
            state = IDLE;
            moved = false;
            hovering = false;
        }
    }

    public void onMouseRelease(MouseEvent e) {
        if ((hovering || moved) && e.getButton() == MouseEvent.BUTTON1) {
            state = PRESSED;
            onClick();
        }
    }

    public Component setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        updateBounds();
        return this;
    }

    public Component setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        updateBounds();
        return this;
    }

    // Set the parent component
    public Component setParent(Component parent) {
        this.parent = parent;
        this.height = parent.height;
        this.width = parent.width;
        updateBounds();
        return this;
    }

    // Add child components
    public Component children(Component... componentsArray) {
        childComponents.addAll(Arrays.asList(componentsArray));
        return this;
    }

    // Render child components
    public void renderChildren(Graphics g) {
        for (Component component : childComponents) {
            component.render(g);
        }
    }

    // Tick child components
    public void tickChildren() {
        for (Component component : childComponents) {
            component.setDimensions(bounds.width - 40, bounds.height - 40);
            component.tick();
        }
    }

    public Component showBounds() {
        this.showBounds = true;
        return this;
    }

}
