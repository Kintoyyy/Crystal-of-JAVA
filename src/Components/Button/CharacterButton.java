package Components.Button;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Characters.Character;
import Components.ToolTip.HealthBar;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SimplePixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Components.enums.States.PRESSED;

@SuppressWarnings("SingleStatementInBlock")
public class CharacterButton extends Button {
    private final HealthBar healthBar;
    private final Character player;

    private final Animation characterAnimation;

    public CharacterButton(Character player) {
        super("Character Frame");
        hideText();
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        buttonSheet[0] = sheet.crop(106, 0, 1, 1); // default
        buttonSheet[1] = sheet.crop(106, 0, 1, 1); // hovered
        buttonSheet[2] = sheet.crop(106, 0, 28, 28);  // pressed

        setDimensions(32, 32);
        scale(3);

        this.player = player;
        assert player != null;
        characterAnimation = player.getAnimation();

        healthBar = (HealthBar) new HealthBar(player)
                .scale(3);
    }

    public void isActive(boolean isActive) {
        state = isActive ? PRESSED : state;
    }

    @Override
    public void tick() {
        super.tick();
        healthBar.tick();
        if (characterAnimation != null) {
            characterAnimation.tick();
        }
    }


    public void getDyingAnimation() {
        if (!player.getHealth().isDead()) {
            return;
        }


    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        if (characterAnimation != null) {

            if (player.getHealth().isDead()) {
                characterAnimation.getFrame(TYPE.GHOST, DIRECTION.RIGHT);
            }

            g.drawImage(player.getHealth().isDead() ? characterAnimation.getFrame(TYPE.GHOST, DIRECTION.RIGHT) : characterAnimation.getFrame(TYPE.IDLE, DIRECTION.RIGHT), this.x - 4, this.y + 2, width + 10, height + 10, null);
        }

        g.drawImage(buttonImage, this.x, this.y, width, height, null);

        healthBar.setLocation(this.x + 16, this.y + 110);
        healthBar.render(g);

        g.setColor(Color.WHITE);
        g.setFont(new SimplePixelFont(12));
        g.drawString(player.getName(), this.x + 18, this.y + 110);

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(this.x, this.y, this.width, this.height);
        }
    }

    public Character getPlayer() {
        return player;
    }
}
