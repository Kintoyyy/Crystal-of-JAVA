package Components.ToolTip;

import Components.Menu.Menu;
import Entities.Enemies.Enemy;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyHealthBar extends Menu {
    private final Enemy enemy;
    private final BufferedImage frame;
    private final BufferedImage healthBar;
    private final BufferedImage defenseBar;
    private double health;
    private double mana;
    private double defense;

    public EnemyHealthBar(Enemy enemy) {
        super();
        this.width = 21;
        this.height = 7;
        this.enemy = enemy;
        // Initialize sprite sheet and UI components
        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        frame = sheet.crop(84, 0, 21, 7);
        healthBar = sheet.crop(85, 11, 19, 2);
        defenseBar = sheet.crop(85, 15, 19, 2);
    }

    @Override
    public void tick() {
        super.tick();
        this.tickChildren();
        if (enemy != null) {
            health = enemy.getHealth().getHealth();
            defense = enemy.getDefense().getDefense();
        }
    }

    @Override
    public void render(Graphics g) {
//         Background and profile image rendering
        g.setColor(new Color(24, 20, 37));

        if (enemy != null) {
            renderHealthManaBars(g);
        }

        g.drawString("Health: " + health, bounds.x, bounds.y + height + 10);
        g.drawString("Defence: " + defense, bounds.x, bounds.y  + height + 20);
        // Frame rendering
        g.drawImage(frame, bounds.x, bounds.y, width, height, null);
//        this.renderChildren(g);
    }

    private void renderHealthManaBars(Graphics g) {
        double maxHealth = enemy.getHealth().getBaseHealth();
        double maxDefense = enemy.getDefense().getBaseDefense();

        // Calculate the width for health and mana bars
        int healthWidth = (int) ((health / maxHealth) * (19 * scale));
        int defenseWidth = (int) ((defense / maxDefense) * (19 * scale));

        // Draw health and mana bars
        g.drawImage(healthBar, bounds.x + (1 * scale), bounds.y + (1 * scale), healthWidth, 2 * scale, null);
        g.drawImage(defenseBar, bounds.x + (1 * scale), bounds.y + (4 * scale), defenseWidth, 2 * scale, null);
    }

    @Override
    public void onClick() {
        // No action implemented for clicks in this menu
    }
}
