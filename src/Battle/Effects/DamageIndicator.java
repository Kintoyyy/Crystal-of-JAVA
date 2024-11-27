package Battle.Effects;

import fonts.SimplePixelFont;
import fonts.SuperPixelFont;
import java.awt.*;

public class DamageIndicator {
    private final String text;
    private double x, y;
    private final Color color;
    private double alpha = 1.0f;
    private final long startTime;
    private final long duration;
    private final float floatSpeed = 1.0f;
    private boolean isDone = false;
    private final Font font = new SuperPixelFont(16);

    public DamageIndicator(String text, double x, double y, Color color, long duration) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
    }

    public void update() {
        long elapsed = System.currentTimeMillis() - startTime;
        float progress = (float) elapsed / duration;
        
        y -= floatSpeed;
        alpha = 1.0f - progress;
        
        if (elapsed >= duration) {
            isDone = true;
        }
    }

    public void render(Graphics g) {
        if (isDone) return;

        g.setFont(new SimplePixelFont(16));
        g.setColor(color);
        g.drawString(text, (int) x, (int) y);
        
//        Graphics2D g2d = (Graphics2D) g;
//        Composite oldComposite = g2d.getComposite();
//
//        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
//        g2d.setColor(color);
//        g2d.setFont(font);
//
//        g2d.drawString(text, (int) x - g2d.getFontMetrics().stringWidth(text) / 2, (int) y);
//
//        g2d.setComposite(oldComposite);
    }

    public boolean isDone() {
        return isDone;
    }
}
