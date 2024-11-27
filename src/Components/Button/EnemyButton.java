package Components.Button;

import Animations.Animation;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Components.ToolTip.EnemyHealthBar;
import Entities.Enemies.Enemy;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SimplePixelFont;

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

        buttonSheet[0] = sheet.crop(106, 0, 1, 1); // default
        buttonSheet[1] = sheet.crop(106, 0, 1, 1); // hovered
        buttonSheet[2] = sheet.crop(106, 0, 28, 28);  // pressed

        setDimensions(28, 28);
        scale(3);

        this.enemy = enemy;

        animation = enemy.getAnimation();

        this.healthBar = (EnemyHealthBar) new EnemyHealthBar(enemy)
                .scale(3);
    }


    @Override
    public void tick() {

        healthBar.tick();
        if (animation != null) {
            animation.tick();
        }
    }

    @Override
    public void render(Graphics g) {

        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        buttonImage = battleManager.getCurrentEnemy() == enemy ? buttonSheet[2] : buttonImage;

        // Draw button image
        g.drawImage(buttonImage, this.x, this.y, width, height, null);

        healthBar.setLocation(this.x + 10, this.y + 110);
        healthBar.render(g);

        // Render selectedEnemy name above the frame
        if (enemy != null) {
            g.setColor(Color.WHITE);
            g.setFont(new SimplePixelFont(12));
            g.drawString(enemy.getName(), this.x + 18, this.y + 110);
        }

        if (animation != null) {
            assert enemy != null;

            int height = animation.getHeight();
            int width = animation.getWidth();


            if (!enemy.getHealth().isDead()) {
                g.drawImage(animation.getFrame(TYPE.IDLE, DIRECTION.LEFT), this.x - width / 2 + 8, this.y - height / 2 + 8, width * scale, height * scale, null);
            } else {
                g.drawImage(animation.getFrame(TYPE.DEAD), this.x - width / 2 + 8, this.y - height / 2 + 8, width * scale, height * scale, null);
            }

            // NOT WORKING
//            drawImage(g, enemy.getHealth().isDead() ? animation.getFrame(TYPE.GHOST, DIRECTION.LEFT) : animation.getFrame(TYPE.IDLE, DIRECTION.LEFT), this.x, this.y, width, height);
        }
    }
}
