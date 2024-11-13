package Components.Frame;

import Components.Menu.EnemyMiniHealthBar;
import Enemies.Enemy;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;


public class EnemyFrame extends Frame {
    private final Enemy enemy;
    private final EnemyMiniHealthBar healthBar;

    public EnemyFrame(Enemy enemy) {
        super("Character Frame");
        hideText();
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        buttonSheet[0] = sheet.crop(106, 0, 1, 1); // Default
        buttonSheet[1] = sheet.crop(106, 0, 1, 1); // Hovered
        buttonSheet[2] = sheet.crop(106, 0, 28, 28);  // Pressed

        setDimensions(28, 28);
        scale(3);
        this.enemy = enemy;

        this.healthBar = (EnemyMiniHealthBar) new EnemyMiniHealthBar(enemy)
                .scale(3);
    }


    @Override
    public void tick() {
        super.tick();
        healthBar.tick();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        // Draw button image
        g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);

        healthBar.setLocation(bounds.x + 10, bounds.y + 90);
        healthBar.render(g);

        // Render enemy name above the frame
        if (enemy != null) {
            g.drawString(enemy.getName(), bounds.x, bounds.y - 10);
        }

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
