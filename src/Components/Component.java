package Components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

abstract class Component {

    protected BufferedImage image;
    protected float x, y;
    protected int width, height;
    protected boolean hovering = false;
    protected boolean moved;
    protected Rectangle bounds;


//    public Component(BufferedImage image, int height, int width) {
//        this.image = image;
//        this.height = height;
//        this.width = width;
//    }

    abstract public void tick();
    abstract public void render(Graphics g);
    abstract public void onMouseMove(MouseEvent e);
    abstract public void onMouseClick();
    abstract public void onMouseRelease(MouseEvent e);
}
