package Inputs;

import Components.ComponentManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The InputMouseListener class handles mouse input events such as pressing, releasing, moving,
 * and dragging the mouse. It integrates with a ComponentManager for handling component-specific actions.
 */
public class InputMouseListener implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed; // Track the state of left and right mouse buttons.
    private int mouseX, mouseY; // Current mouse coordinates.
    private ComponentManager componentManager; // Manages component interactions based on mouse input.

    /**
     * Sets the ComponentManager for handling mouse actions on components.
     *
     * @param componentManager The ComponentManager instance.
     */
    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    /**
     * Checks if the left mouse button is pressed.
     *
     * @return True if the left button is pressed, otherwise false.
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }

    /**
     * Checks if the right mouse button is pressed.
     *
     * @return True if the right button is pressed, otherwise false.
     */
    public boolean isRightPressed() {
        return rightPressed;
    }

    /**
     * Retrieves the current X-coordinate of the mouse cursor.
     *
     * @return The X-coordinate of the mouse.
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Retrieves the current Y-coordinate of the mouse cursor.
     *
     * @return The Y-coordinate of the mouse.
     */
    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Optional: Add functionality if required.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (componentManager != null) {
            componentManager.onMouseMove(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Optional: Add functionality if required.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Optional: Add functionality if required.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Optional: Add functionality if required.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }

        if (componentManager != null) {
            componentManager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }
}
