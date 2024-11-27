package Components.Dialog;

import Components.UIComponent;
import Components.Text.Text;
import Entities.Entity;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EntityDialog extends UIComponent {
    private final Entity entity;
    private final List<String> dialogLines;
    private int currentLine;
    private boolean isActive;
    private final Text text;
    private static final int DIALOG_WIDTH = 200;
    private static final int DIALOG_HEIGHT = 60;
    private static final int PADDING = 10;

    public EntityDialog(Entity entity, List<String> dialogLines) {
        this.entity = entity;
        this.dialogLines = new ArrayList<>(dialogLines);
        this.currentLine = 0;
        this.isActive = false;

        text = (Text) new Text("")
                .typing()
                .setFont(new SuperPixelFont(16))
                .setColor(Color.BLACK)
                .setParent(this);
    }

    @Override
    public void tick() {
        if (!isActive) return;
        
        // Update dialog position to follow entity
        updatePosition();
        text.setText(dialogLines.get(currentLine));
        text.tick();
    }

    private void updatePosition() {
        // Position dialog above entity
        Point entityScreenPos = entity.getLocation();
        this.x = entityScreenPos.x - DIALOG_WIDTH / 2;
        this.y = entityScreenPos.y - DIALOG_HEIGHT - 20; // 20 pixels above entity
    }

    @Override
    public void render(Graphics g) {
        if (!isActive) return;

        // Draw dialog background
        g.setColor(new Color(255, 255, 255, 220));
        g.fillRoundRect(x, y, DIALOG_WIDTH, DIALOG_HEIGHT, 10, 10);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, DIALOG_WIDTH, DIALOG_HEIGHT, 10, 10);

        // Draw text
        text.setLocation(x + PADDING, y + PADDING);
        text.render(g);

        // Draw next indicator if there are more lines
        if (currentLine < dialogLines.size() - 1) {
            g.setColor(Color.BLACK);
            int[] xPoints = {x + DIALOG_WIDTH - 20, x + DIALOG_WIDTH - 10, x + DIALOG_WIDTH - 20};
            int[] yPoints = {y + DIALOG_HEIGHT - 15, y + DIALOG_HEIGHT - 10, y + DIALOG_HEIGHT - 5};
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    public void nextLine() {
        if (currentLine < dialogLines.size() - 1) {
            currentLine++;
        } else {
            isActive = false;
        }
    }

    public void show() {
        isActive = true;
        currentLine = 0;
    }

    public boolean isActive() {
        return isActive;
    }
}
