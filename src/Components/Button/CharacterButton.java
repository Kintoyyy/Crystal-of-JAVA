package Components.Button;

import Animations.Animation;
import Animations.Entities.CharacterAnimation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Characters.Character;
import Components.ToolTip.HealthBar;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static enums.ComponentStateEnums.PRESSED;

@SuppressWarnings("SingleStatementInBlock")
public class CharacterButton extends Button {
    private final HealthBar healthBar;
    private final Character player;

    Animation characterAnimation;

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

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        if (characterAnimation != null) {
            g.drawImage(player.getHealth().isDead() ? characterAnimation.getFrame(TYPE.GHOST, DIRECTION.RIGHT) : characterAnimation.getFrame(TYPE.IDLE, DIRECTION.RIGHT), bounds.x - 4, bounds.y + 2, width + 10, height + 10, null);
        }

        g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);

        healthBar.setLocation(bounds.x + 10, bounds.y + 90);
        healthBar.render(g);

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public Character getPlayer() {
        return player;
    }
}
