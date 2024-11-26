package Components;

import java.awt.event.MouseEvent;

public interface ComponentEventListener {
    /**
     * Triggered when a component is clicked.
     *
     * @param event The mouse event associated with the click
     */
    void onComponentClick(MouseEvent event);

    /**
     * Triggered when the mouse enters the component.
     *
     * @param event The mouse event associated with mouse entry
     */
    void onMouseEnter(MouseEvent event);

    /**
     * Triggered when the mouse exits the component.
     *
     * @param event The mouse event associated with mouse exit
     */
    void onMouseExit(MouseEvent event);
}