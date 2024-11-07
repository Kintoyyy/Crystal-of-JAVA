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
        System.out.println(componentManager);
//        System.out.println("Mouse moved to: " + mouseX + " " + mouseY);
        if(componentManager != null) {
            componentManager.onMouseMove(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse clicked");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse exited");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
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
        System.out.println("Mouse released");
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
    }
}
