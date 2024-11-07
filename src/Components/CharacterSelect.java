package Components;

import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CharacterSelect extends Component {

    BufferedImage background;
    BufferedImage[] characterFrames = new BufferedImage[3];

    public CharacterSelect(String textContent) {
        super(0, 0, 183, 110);

        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));
        background = backgroundSheet.crop(0, 80, 183, 110);

        characterFrames[0] = backgroundSheet.crop(143, 0, 41, 46);
        characterFrames[1] = backgroundSheet.crop(184, 0, 41, 46);
        characterFrames[2] = backgroundSheet.crop(143, 0, 41, 46);

        this.children(

        );
    }

    @Override
    public void tick() {
        tickChildren();
    }


    public CharacterSelect setScale(int scale) {
        this.width = width * scale;
        this.height = height * scale;
        return this;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, width, height, null);

        g.drawImage(characterFrames[0], 7, 8, 40, 46, null);

        renderChildren(g);

        if (showBounds) {
            g.setColor(Color.PINK);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public void onClick() {
        // Define behavior on dialog click
    }
}
