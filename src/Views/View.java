package Views;

import Battle.BattleManager;
import Components.ComponentManager;
import Game.Handler;

import java.awt.*;
public abstract class View {
    protected final Handler handler = Handler.getInstance();
    protected final ViewManager viewManager = handler.getViewManager();
    protected final BattleManager battleManager = handler.getBattleManager();
    protected final ComponentManager components;
    protected boolean isOverlay = false;

    /**
     * Constructor for the View class.
     *
     */
    public View() {
        this.components = new ComponentManager();
    }

    /**
     * Returns the ComponentManager responsible for managing UI components in this view.
     *
     * @return The ComponentManager instance for the current view.
     */
    public ComponentManager getComponentManager() {
        return components;
    }

    /**
     * Abstract method to render the visuals of the view.
     * Concrete implementations of this method should define how the view is drawn.
     *
     * @param g The Graphics object used for rendering the view.
     */
    public abstract void render(Graphics g);

    /**
     * Abstract method to update the state of the view.
     * Concrete implementations of this method should define how the view updates each tick.
     */
    public abstract void tick();

    public abstract void setData(Object data);
}
