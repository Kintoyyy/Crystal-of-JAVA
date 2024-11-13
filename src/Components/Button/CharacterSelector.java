package Components.Button;

import Characters.Character;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import enums.Alignment;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

import static enums.ComponentStateEnums.*;

public class CharacterSelector extends Button {
    private final BufferedImage playerProfile;
    private final Text tooltip;
    private final Character player;

    public CharacterSelector(Character player) {
        super("Character Frame");
        hideText();
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));

        buttonSheet[0] = sheet.crop(58, 92, 28, 28); // default
        buttonSheet[1] = sheet.crop(29, 92, 28, 28); // hovered
        buttonSheet[2] = sheet.crop(0, 92, 28, 28);  // pressed

        setDimensions(28, 28);
        scale(3);

        this.player = player;
        this.playerProfile = player != null ? player.getProfile() : null;

        tooltip = (Text) new Text(player != null ? player.getName() : "")
                .setAlignment(Alignment.CENTER)
                .setColor(new Color(234, 212, 170))
                .setFont(new SuperPixelFont(15))
                .setDimensions(width, height);
    }

    public void isActive(boolean isActive) {
        state = isActive ? PRESSED : state;
    }

    @Override
    public void tick() {
        super.tick();
        tooltip.tick();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        g.setColor(new Color(184, 111, 80));
        g.fillRect(bounds.x + 10, bounds.y + 10, width - 20, height - 20);

        if (playerProfile != null) {
            g.drawImage(playerProfile, bounds.x + 1, bounds.y + 8, width, height, null);
        }

        g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);

        if (state == HOVERED) {
            tooltip.setLocation(bounds.x, bounds.y - 30);
            tooltip.render(g);
        }

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public Character getPlayer() {
        return player;
    }
}
