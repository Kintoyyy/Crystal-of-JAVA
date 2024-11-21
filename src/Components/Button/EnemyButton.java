package Components.Button;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Components.ToolTip.EnemyHealthBar;
import Entities.Enemies.Enemy;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Components.enums.States.PRESSED;


public class EnemyButton extends Button {
    private final Enemy enemy;
    private final EnemyHealthBar healthBar;

    private final Animation animation;


    public EnemyButton(Enemy enemy) {
        super("Character Frame");
        hideText();
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        buttonSheet[0] = sheet.crop(135, 0, 1, 1); // Default
        buttonSheet[1] = sheet.crop(135, 0, 1, 1); // Hovered
        buttonSheet[2] = sheet.crop(135, 0, 28, 28);  // Pressed

        setDimensions(28, 28);
        scale(3);
        this.enemy = enemy;

        animation = enemy.getAnimation();

        this.healthBar = (EnemyHealthBar) new EnemyHealthBar(enemy)
                .scale(3);
    }


    @Override
    public void tick() {
        super.tick();
        if (animation != null) {
            animation.tick();
        }
        healthBar.tick();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        // Draw button image
        g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);

        healthBar.setLocation(bounds.x + 10, bounds.y + 90);
        healthBar.render(g);

        // Render enemy name above the frame
        if (enemy != null) {
            g.drawString(enemy.getName(), bounds.x, bounds.y - 10);
        }

        if (animation != null) {
            assert enemy != null;
            g.drawImage(enemy.getHealth().isDead() ? animation.getFrame(TYPE.GHOST, DIRECTION.LEFT) : animation.getFrame(TYPE.IDLE, DIRECTION.LEFT), bounds.x - 4, bounds.y + 2, width + 10, height + 10, null);
        }

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void isActive(boolean b) {
        state = b ? PRESSED : state;
    }
}
