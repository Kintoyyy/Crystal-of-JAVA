package Components.Menu;

import Characters.Character;
import Components.Text.Text;
import Game.Handler;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import enums.Alignment;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MiniHealthBar extends Menu {
    private final Character player;
    private final SpriteSheet sheet;
    private final BufferedImage frame;
    private final BufferedImage healthBar;
    private final BufferedImage manaBar;
    private final BufferedImage defenseBar;
    private double health;
    private double mana;
    private double defense;

    public MiniHealthBar(Character player) {
        super();
        this.width = 21;
        this.height = 10;
        this.player = player;
        // Initialize sprite sheet and UI components
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        frame = sheet.crop(84, 0, 21, 10);
        healthBar = sheet.crop(85, 11, 19, 2);
        manaBar = sheet.crop(85, 13, 19, 2);
        defenseBar = sheet.crop(85, 15, 19, 2);
    }

    @Override
    public void tick() {
        super.tick();
        this.tickChildren();
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

        g.drawString("Health: " + health, bounds.x, bounds.y + 40);
        g.drawString("Mana: " + mana, bounds.x, bounds.y + 50);
        g.drawString("Defense: " + defense, bounds.x, bounds.y + 60);

        // Frame rendering
        g.drawImage(frame, bounds.x, bounds.y, width, height, null);
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
        g.drawImage(healthBar, bounds.x + (scale), bounds.y + (scale), healthWidth, 2 * scale, null);
        g.drawImage(manaBar, bounds.x + (scale), bounds.y + (4 * scale), manaWidth, 2 * scale, null);
        g.drawImage(defenseBar, bounds.x + (scale), bounds.y + (7 * scale), defenseWidth, 2 * scale, null);
    }

    @Override
    public void onClick() {
        // No action implemented for clicks in this menu
    }
}
