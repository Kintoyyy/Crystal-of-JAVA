package Components.Menu;

import Characters.Character;
import Enemies.Enemy;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyMiniHealthBar extends Menu {
    private final Enemy enemy;
    private final SpriteSheet sheet;
    private final BufferedImage frame;
    private final BufferedImage healthBar;
    private final BufferedImage defenseBar;
    private double health;
    private double mana;
    private double defense;

    public EnemyMiniHealthBar(Enemy enemy) {
        super();
        this.width = 21;
        this.height = 7;
        this.enemy = enemy;
        // Initialize sprite sheet and UI components
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

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

        g.drawString(String.valueOf(health), bounds.x, bounds.y + 40);
        g.drawString(String.valueOf(defense), bounds.x, bounds.y + 60);

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
