package Components;

import Battle.BattleManager;
import Components.enums.States;
import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Game.Handler;
import Views.ViewManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static Components.enums.States.*;

public abstract class UIComponent {
    protected final Handler handler;
    protected ViewManager viewManager;
    protected BattleManager battleManager;
    protected CharacterManager characterManager;
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
    protected int scale = 1;

    protected States state = IDLE;
    private boolean isVisible = true;
    private boolean isEnabled = true;

    protected boolean showBounds = false;

    private UIComponent parent;
    protected List<UIComponent> children = new ArrayList<>();

    private ComponentEventListener eventListener;

    public UIComponent() {
        this.handler = Handler.getInstance();
        this.viewManager = handler.getViewManager();
        this.battleManager = handler.getBattleManager();
        this.characterManager = handler.getCharacterManager();
        validateDimensions();
    }

    public UIComponent setLocation(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
        this.x = x;
        this.y = y;
        updatePosition();
        return this;
    }

    public UIComponent setDimensions(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimensions must be non-negative");
        }
        this.width = width;
        this.height = height;
        return this;
    }

    public void addChildren(UIComponent... components) {
        for (UIComponent component : components) {
            if (component != null) {
                children.add(component);
                component.setParent(this);
                component.x -= this.x;
                component.y -= this.y;
            }
        }
    }

    public UIComponent setParent(UIComponent parent) {
        this.parent = parent;
        updatePosition();
        return this;
    }

    private void updateChildrenPositions() {
        children.forEach(UIComponent::updatePosition);
    }

    private void updatePosition() {
        if (parent != null) {
            x += parent.x;
            y += parent.y;
        }
        updateChildrenPositions();
    }

    private void validateDimensions() {
        if (width < 0) width = 0;
        if (height < 0) height = 0;
    }

    public UIComponent setEventListener(ComponentEventListener listener) {
        this.eventListener = listener;
        return this;
    }

    public void handleMouseMove(MouseEvent event) {
        if (!isEnabled || !isVisible) return;

        boolean isInside = isPointInside(event.getX(), event.getY());

        if (isInside && state != States.HOVERED) {
            state = States.HOVERED;
            if (eventListener != null) {
                eventListener.onMouseEnter(event);
            }
        } else if (!isInside && state == States.HOVERED) {
            state = States.IDLE;
            if (eventListener != null) {
                eventListener.onMouseExit(event);
            }
        }

        // Propagate to children
        children.forEach(child -> child.handleMouseMove(event));
    }

    public void handleMouseClick(MouseEvent event) {
        if (!isEnabled || !isVisible) return;

        if (isPointInside(event.getX(), event.getY())) {
            state = States.PRESSED;
            if (eventListener != null) {
                eventListener.onComponentClick(event);
            }
        }

        // Propagate to children
        children.forEach(child -> child.handleMouseClick(event));
    }

    private boolean isPointInside(int x, int y) {
        return x >= this.x && x <= (this.x + width) &&
                y >= this.y && y <= (this.y + height);
    }

    public void tickComponent() {
        if (!isEnabled || !isVisible) return;
        tick();
        children.forEach(UIComponent::tickComponent);
    }

    public void renderComponent(Graphics g) {
        if (!isVisible) return;
        render(g);
        children.forEach(child -> child.renderComponent(g));
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public States getState() {
        return state;
    }

    public UIComponent setEnabled(boolean enabled) {
        this.isEnabled = enabled;
        this.state = enabled ? States.IDLE : States.DISABLED;
        return this;
    }

    public UIComponent setVisible(boolean visible) {
        this.isVisible = visible;
        return this;
    }

    public UIComponent showBounds() {
        this.showBounds = true;
        return this;
    }

    public UIComponent scale(int i) {
        this.scale = i;
        setDimensions(width * i, height * i);
        return this;
    }


    public void drawImage(Graphics g, Image image, int x, int y, int width, int height) {
        g.drawImage(image, (int) (parent.x + (x * scale)), (int) (parent.y + (y * scale)), width * scale, height * scale, null);
    }

    public void drawString(Graphics g, String text, int x, int y, Font font, Color color) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, (int) (parent.x + (x * scale)), (int) (parent.y + (y * scale)));
    }

    // TODO: DO BE REMOVED
    public abstract void onClick(MouseEvent e);
}
