package Components.Frame;

import Characters.Character;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import enums.Alignment;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

import static enums.ComponentStateEnums.HOVERED;
import static enums.ComponentStateEnums.PRESSED;

public class PlayerFrame extends Frame {
    private BufferedImage playerProfile;
    private final Text tooltip;
    private Character player;

    public PlayerFrame(Character player) {
        super("Character Frame");
        this.hideText();
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        buttonSheet[1] = sheet.crop(29, 92, 28, 28);
        buttonSheet[0] = sheet.crop(58, 92, 28, 28);
        buttonSheet[2] = sheet.crop(0, 92, 28, 28);

        this.setDimensions(28, 28);
        this.scale(3);

        this.player = player;
        if (player != null) {
            this.playerProfile = player.getProfile();
        }

        tooltip = (Text) new Text(this.player != null ? this.player.getName() : "")
                .setAlignment(Alignment.CENTER)
                .setColor(new Color(234, 212, 170))
                .setFont(new SuperPixelFont(15))
                .setDimensions(this.width, this.height);
    }

    public PlayerFrame isActive(boolean isActive) {
        if (isActive) {
            state = PRESSED;
        }
        return this;
    }

    @Override
    public void tick() {
        tooltip.tick();
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        if (buttonImage != null) {
            g.setColor(new Color(184, 111, 80));
            g.fillRect(bounds.x + 10, bounds.y + 10, width - 20, height - 20);

            // Only draw the player profile if it's not null
            if (playerProfile != null) {
                g.drawImage(playerProfile, bounds.x + 1, bounds.y + 8, width, height, null);
            }

            g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);
        }

        if(state == HOVERED){
            tooltip.setLocation(bounds.x , bounds.y - 30);
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
