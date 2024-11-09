package Components.Menu;

import Characters.Character;
import Game.Handler;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterTopLeftMenu extends Menu {
    private Character player;
    private BufferedImage playerProfile;
    private SpriteSheet sheet;
    private BufferedImage frame;
    private BufferedImage healthBar;
    private BufferedImage manaBar;
    private double health;
    private double mana;
    private final Handler handler;

    public CharacterTopLeftMenu(Handler handler) {
        super(0, 0, 48, 19);
        this.handler = handler;

        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        frame = sheet.crop(155, 0, 48, 19);
        healthBar = sheet.crop(144, 0, 10, 5);
        manaBar = sheet.crop(144, 5, 10, 5);
    }

    @Override
    public void tick() {
        super.tick();

        player = handler.getGameState().getPlayer();

        playerProfile = player.getProfile();
        health = player.getHealth().getHealth();
        mana = player.getMana().getMana();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(bounds.x + 10, bounds.y + 10, height - 20, height - 20);

        if (playerProfile != null) {
            g.drawImage(playerProfile, bounds.x + 1, bounds.y + 8, height + 4, height + 4, null);
        }

        // Health and Mana Bar Rendering
        double maxHealth = player.getHealth().getBaseHealth();
        double maxMana = player.getMana().getBaseMana();

        // Calculate scaled width based on current health and mana percentage
        int healthWidth = (int) ((health / (double) maxHealth) * (27 * scale));
        int manaWidth = (int) ((mana / (double) maxMana) * (27 * scale));

        // Draw health and mana bars with calculated width
        g.drawImage(healthBar, bounds.x + (20 * scale), bounds.y + (4 * scale), healthWidth, 5 * scale, null);
        g.drawImage(manaBar, bounds.x + (20 * scale), bounds.y + (9 * scale), manaWidth, 5 * scale, null);

        // Draw the frame around the character profile and bars
        g.drawImage(frame, bounds.x, bounds.y, width, height, null);
    }


    @Override
    public void onClick() {

    }
}
