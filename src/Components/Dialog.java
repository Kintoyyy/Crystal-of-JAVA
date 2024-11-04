    package Components;

    import Utils.ImageUtils;
    import Utils.SpriteSheet;
import fonts.SimplePixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Dialog extends Component {

    private final BufferedImage dialogImage;
    private Text text;

    public Dialog(String textContent) {
        super(0, 0, 500, 50);

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_.png"));
        dialogImage = sheet.crop(0, 0, 100, 100);

        this.text = (Text) new Text(textContent)
                .typing()
                .setFont(new SimplePixelFont(16))
                .setColor(Color.WHITE)
                .setParent(this)
                .setDimensions(840, 50)
                .setLocation(10, 10);
    }

    public Dialog overrideText(Text text) {
        this.text = text;
        return this;
    }

    @Override
    public void tick() {
        text.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(dialogImage, bounds.x, bounds.y, width, height, null);

        if (text != null) {
            text.render(g);
        }

        g.setColor(Color.BLUE);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void onClick() {
        // Define behavior on dialog click
    }
}
