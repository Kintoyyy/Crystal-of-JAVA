package Inputs;

import Components.ComponentManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputMouseListener implements MouseListener, MouseMotionListener{

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private ComponentManager componentManager;

    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(componentManager != null) {
            componentManager.onMouseMove(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }

        if(componentManager != null) {
            componentManager.onMouseRelease(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }
}
