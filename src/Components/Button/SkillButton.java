package Components.Button;

import Battle.Skills.Skill;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SimplePixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SkillButton extends Button {
    private final Skill Skill;

    public SkillButton(Skill Skill) {
        super("Skill Frame");
        hideText();
        this.Skill = Skill;
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Battle_UI.png"));

        buttonSheet[0] = sheet.crop(56, 0, 28, 28); // default
        buttonSheet[1] = sheet.crop(28, 0, 28, 28); // hovered
        buttonSheet[2] = sheet.crop(0, 0, 28, 28);  // pressed

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
        g.setColor(new Color(232, 210, 169));
        g.fillRect(this.x + (5 * scale), this.y + (5 * scale), width - (10 * scale), height - (10 * scale));

        g.drawImage(Skill.getSkillImage(), this.x + (5 * scale), this.y + (5 * scale), width - (10 * scale), height - (10 * scale), null);

        g.drawImage(buttonImage, this.x, this.y, width, height, null);

        g.setColor(new Color(0, 0, 0, 39)); // Semi-transparent black
        g.fillRect(this.x - 4, this.y + height - 4, 104, 40);

        g.setColor(Color.WHITE);

        g.setFont( new SimplePixelFont(8));
        g.drawString(Skill.getName(), this.x, this.y + height + 10);
        g.drawString("Cost: " + Skill.getCost(), this.x, this.y + height + 20);
        g.drawString("Cooldown: " + Skill.getCooldown(), this.x, this.y + height + 30);


    }
}
