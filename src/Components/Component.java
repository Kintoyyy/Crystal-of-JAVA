package Components;

import Components.enums.States;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static Components.enums.States.*;

public abstract class Component {
    protected float x = 0;
    protected float y = 0;
    protected int width = 0;
    protected int height = 0;
    protected int scale = 1;
    protected boolean showBounds = false;
    protected boolean moved = false;

    private Component parent;
    protected ArrayList<Component> childComponents = new ArrayList<>();
    protected Rectangle bounds = new Rectangle();
    protected States state = IDLE;
    private String text;

    public Component() {
        updateBounds();
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void updateBounds() {
        int parentX = parent != null ? (int) parent.x : 0;
        int parentY = parent != null ? (int) parent.y : 0;
        bounds.setBounds(parentX + (int) x, parentY + (int) y, width, height);
    }

    public void onMouseMove(MouseEvent e) {
        moved = bounds.contains(e.getX(), e.getY());
        state = moved ? HOVERED : IDLE;

        for (Component component : childComponents) {
            component.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        if ((state == HOVERED || moved) && e.getButton() == MouseEvent.BUTTON1) {
            state = PRESSED;
            onClick();
        }
        for (Component component : childComponents) {
            component.onMouseRelease(e);
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

    public Component setParent(Component parent) {
        this.parent = parent;
//        setDimensions(parent.width, parent.height);

        updateBounds();
        return this;
    }

    public void addChildren(Component... componentsArray) {
        childComponents.addAll(Arrays.asList(componentsArray));
    }

    public void renderChildren(Graphics g) {
        for (Component component : childComponents) {
            component.render(g);
        }
    }

    public void onClickChildren() {
        for (Component component : childComponents) {
            component.onClick();
        }
    }

    public void tickChildren() {
        for (Component component : childComponents) {
//            component.setDimensions(bounds.width - 40, bounds.height - 40);
            component.tick();
        }
    }

    public Component showBounds() {
        this.showBounds = true;
        return this;
    }

    public Component scale(int i) {
        this.scale = i;
        setDimensions(width * i, height * i);
        return this;
    }

    public int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }
}
