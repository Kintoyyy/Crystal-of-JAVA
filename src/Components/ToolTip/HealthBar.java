package Components.ToolTip;

import Entities.Characters.Character;
import Components.Menu.Menu;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HealthBar extends Menu {
    private final Character player;
    private final BufferedImage frame;
    private final BufferedImage healthBar;
    private final BufferedImage manaBar;
    private final BufferedImage defenseBar;
    private double health;
    private double mana;
    private double defense;

    public HealthBar(Character player) {
        super();
        this.width = 21;
        this.height = 7;
        this.player = player;
        // Initialize sprite sheet and UI components
        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        frame = sheet.crop(84, 0, 21, 7);
        healthBar = sheet.crop(85, 11, 19, 2);
        manaBar = sheet.crop(85, 13, 19, 2);
        defenseBar = sheet.crop(85, 15, 19, 2);
    }

    @Override
    public void tick() {
        super.tick();
//        this.tickChildren();
        if (player != null) {
            health = player.getHealth().getHealth();
            mana = player.getMana().getMana();
            defense = player.getDefense().getDefense();
        }
    }

    @Override
    public void render(Graphics g) {
//         Background and profile image rendering
        g.setColor(new Color(24, 20, 37));

        if (player != null) {
            renderHealthManaBars(g);
        }

//        g.drawString("Health: " + health, this.x, this.y + 40);
//        g.drawString("Mana: " + mana, this.x, this.y + 50);
//        g.drawString("Defense: " + defense, this.x, this.y + 60);

        // Frame rendering
        g.drawImage(frame, this.x, this.y, width, height, null);
//        this.renderChildren(g);
    }

    private void renderHealthManaBars(Graphics g) {
        double maxHealth = player.getHealth().getBaseHealth();
        double maxMana = player.getMana().getBaseMana();
        double maxDefense = player.getDefense().getBaseDefense();

        // Calculate the width for health and mana bars
        int healthWidth = (int) ((health / maxHealth) * (19 * scale));
        int manaWidth = (int) ((mana / maxMana) * (19 * scale));
        int defenseWidth = (int) ((defense / maxDefense) * (19 * scale));

        // Draw health and mana bars
        g.drawImage(healthBar, this.x + (scale), this.y + (scale), healthWidth, 2 * scale, null);
        g.drawImage(manaBar, this.x + (scale), this.y + (4 * scale), manaWidth, 2 * scale, null);
//        g.drawImage(defenseBar, this.x + (scale), this.y + (7 * scale), defenseWidth, 2 * scale, null);
    }

    @Override
    public void onClick(MouseEvent e) {
        // No action implemented for clicks in this menu
    }
}
