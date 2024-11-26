package Components.Card;

import Entities.Characters.Character;
import Battle.BattleManager;
import Entities.Characters.CharacterManager;
import Game.Handler;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SimplePixelFont;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BattleStatistic extends Card {
    private final BattleManager battleManager;
    private final CharacterManager characterManager;
    private Character player;
    private BufferedImage playerStat;
    private final BufferedImage frame;
    private final BufferedImage healthBar;
    private final BufferedImage manaBar;
    private final BufferedImage defenseBar;
    private double health;
    private double mana;
    private double defense;

    public BattleStatistic(BattleManager battleManager) {
        super();
        this.battleManager = battleManager;
        this.characterManager = battleManager.getCharacterManager();
        this.player = characterManager.getPlayer();

        this.width = 119;
        this.height = 49;

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));
        frame = sheet.crop(35, 70, 119, 49);
        healthBar = sheet.crop(155, 71, 10, 5);
        manaBar = sheet.crop(155, 76, 10, 5);
        defenseBar = sheet.crop(155, 81, 10, 5);
    }

    @Override
    public void tick() {
        player = this.characterManager.getPlayer();
        if (player != null) {
            playerStat = player.getProfile();
            health = player.getHealth().getHealth();
            mana = player.getMana().getMana();
            defense = player.getDefense().getDefense();
        }
    }

    @Override
    public void render(Graphics g) {
        if (playerStat != null) {
            drawImage(g, playerStat, 8, 8, 14, 14);
        }

        if (player != null) {
            renderHealthManaBars(g);
        }

        g.drawImage(frame, this.x, this.y, width, height, null);

        assert player != null;
        drawString(g, player.getName(), 27, 20, new SuperPixelFont(32), new Color(183, 109, 79));

        double rawHealth = player.getHealth().getHealth();
        double rawMana = player.getMana().getMana();

        // Format values with trailing zeroes (e.g., 1200.00)
        String healthText = String.format("%.0f", rawHealth);
        String manaText = String.format("%.0f", rawMana);

        drawString(g, healthText, 92, 34, new SimplePixelFont(16), new Color(183, 109, 79));
        drawString(g, manaText, 92, 41, new SimplePixelFont(16), new Color(183, 109, 79));
    }

    private void renderHealthManaBars(Graphics g) {
        double maxHealth = player.getHealth().getBaseHealth();
        double maxMana = player.getMana().getBaseMana();
        double maxDefense = player.getDefense().getBaseDefense();

        // Calculate the width for health and mana bars
        int healthWidth = (int) ((health / maxHealth) * 65);
        int manaWidth = (int) ((mana / maxMana) * 65);
        int defenseWidth = (int) ((defense / maxDefense) * 65);

        drawImage(g, healthBar, 23, 29, healthWidth, 4);
        drawImage(g, manaBar, 23, 36, manaWidth, 4);
        drawImage(g, defenseBar, 23, 42, defenseWidth, 4);
    }


    @Override
    public void onClick(MouseEvent e) {

    }
}
