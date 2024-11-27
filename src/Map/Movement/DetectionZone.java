package Map.Movement;

import Utils.DebugMode;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DetectionZone {
    private final float radius;
    private Ellipse2D.Float circle;
    private final Color debugColor;
    private float x, y;

    public static final float DEFAULT_RADIUS = 64f; // Default interaction radius

    public DetectionZone(float x, float y, float radius) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.circle = new Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2);
        this.debugColor = new Color(0, 255, 0, 64);
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
        circle.setFrame(x - radius, y - radius, radius * 2, radius * 2);
    }

    public boolean intersects(Rectangle bounds) {
        return circle.intersects(bounds);
    }

    public void render(Graphics g) {
//        if (!DebugMode.debugMode()) return;
        
        Graphics2D g2d = (Graphics2D) g;
        Color oldColor = g2d.getColor();
        Composite oldComposite = g2d.getComposite();
        
        // Draw filled circle with transparency
        g2d.setColor(debugColor);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2d.fill(circle);
        
        // Draw circle outline
        g2d.setColor(Color.GREEN);
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.draw(circle);
        
        // Restore original graphics settings
        g2d.setColor(oldColor);
        g2d.setComposite(oldComposite);
    }

    public float getRadius() {
        return radius;
    }

    public Ellipse2D.Float getCircle() {
        return circle;
    }
}
