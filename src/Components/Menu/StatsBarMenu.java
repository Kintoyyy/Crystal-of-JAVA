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

public class StatsBarMenu extends Menu {
    private Character player;
    private BufferedImage playerProfile;
    private final SpriteSheet sheet;
    private final BufferedImage frame;
    private final BufferedImage healthBar;
    private final BufferedImage manaBar;
    private double health;
    private double mana;
    private final Handler handler;

    public StatsBarMenu(Handler handler) {
        super();
        this.handler = handler;
        this.width = 94;
        this.height = 53;
        // Initialize sprite sheet and UI components
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        this.addChildren(
                new Text("Kent")
                        .setFont(new SuperPixelFont(30))
                        .setAlignment(Alignment.LEFT)
                        .setColor(new Color(184, 111, 80))
                        .setParent(this)
                        .setLocation(30, 15)
                        .setDimensions(this.width, this.height),
                new Text("lvl: nnn")
                        .setFont(new SuperPixelFont(30))
                        .setAlignment(Alignment.RIGHT)
                        .setColor(new Color(184, 111, 80))
                        .setParent(this)
                        .setLocation(0, 15)
                        .setDimensions(this.width, this.height)
        );

        frame = sheet.crop(0, 121, 94, 53);
        healthBar = sheet.crop(144, 0, 10, 5);
        manaBar = sheet.crop(144, 5, 10, 5);
    }

    @Override
    public void tick() {
        super.tick();
        this.tickChildren();
        player = handler.getGameState().getPlayer();
        if (player != null) {
            playerProfile = player.getProfile();
            health = player.getHealth().getHealth();
            mana = player.getMana().getMana();
        }
    }

    @Override
    public void render(Graphics g) {
//         Background and profile image rendering
        g.setColor(new Color(24, 20, 37));
        g.fillRect(bounds.x + 10, bounds.y + 10, width - 20, height - 20);

        if (player != null) {
            renderHealthManaBars(g);
        }

        // Frame rendering
        g.drawImage(frame, bounds.x, bounds.y, width, height, null);

        this.renderChildren(g);
    }

    private void renderHealthManaBars(Graphics g) {
        double maxHealth = player.getHealth().getBaseHealth();
        double maxMana = player.getMana().getBaseMana();

        // Calculate the width for health and mana bars
        int healthWidth = (int) ((health / maxHealth) * (61 * scale));
        int manaWidth = (int) ((mana / maxMana) * (61 * scale));

        // Draw health and mana bars
        g.drawImage(healthBar, bounds.x + (24 * scale), bounds.y + (20 * scale), healthWidth, 5 * scale, null);
        g.drawImage(manaBar, bounds.x + (24 * scale), bounds.y + (29 * scale), manaWidth, 5 * scale, null);
    }

    @Override
    public void onClick() {
        // No action implemented for clicks in this menu
    }
}
