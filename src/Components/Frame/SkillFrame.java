package Components.Frame;

import Characters.Character;
import Components.Menu.MiniHealthBar;
import Skills.Skill;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static enums.ComponentStateEnums.PRESSED;

public class SkillFrame extends Frame {
    private final BufferedImage skillImage;
    private final Skill Skill;

    public SkillFrame(Skill Skill) {
        super("Skill Frame");
        hideText();
        this.Skill = Skill;
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        buttonSheet[0] = sheet.crop(56, 0, 28, 28); // default
        buttonSheet[1] = sheet.crop(28, 0, 28, 28); // hovered
        buttonSheet[2] = sheet.crop(0, 0, 28, 28);  // pressed
        skillImage = sheet.crop(135, 0, 18, 18  );

        setDimensions(28, 28);
        scale(3);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };
//
        g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);

        g.drawImage(skillImage, bounds.x + (5 * scale), bounds.y + (5 * scale), width - (10 * scale) , height - (10 * scale), null);

        g.drawString(Skill.getName(), bounds.x, bounds.y - 10);
    }
}
