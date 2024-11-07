package Components;

import Game.CallBackAction;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SimplePixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Component {
    private BufferedImage[] buttonSheet = new BufferedImage[3];
    private boolean hideText = false;
    private Text text;
    private CallBackAction clicker;

    public Button(String textContent) {
        super(0, 0, 200, 60);

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));

        buttonSheet[0] = sheet.crop(0, 16, 47, 14);
        buttonSheet[1] = sheet.crop(48, 16, 47, 14);
        buttonSheet[2] = sheet.crop(96, 16, 47, 14);

        this.text = (Text) new Text(textContent)
                .setFont(new SimplePixelFont(24))
                .setColor(Color.WHITE)
                .setParent(this)
                .setLocation(0, 0)
                .setDimensions(160, 32);
    }

    public Button setButtonSheet(BufferedImage[] buttonSheet) {
        if (buttonSheet.length > 3) {
            throw new IllegalArgumentException("At most 3 images allowed.");
        }
        this.buttonSheet = buttonSheet;
        return this;
    }

    public Button setAction(CallBackAction clicker) {
        this.clicker = clicker;
        return this;
    }

    public Button hideText() {
        this.hideText = true;
        return this;
    }

    public Button overrideText(Text text) {
        this.text = text;
        return this;
    }

    @Override
    public void tick() {
        text.tick();
    }

    @Override
    public void render(Graphics g) {
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        if (buttonImage != null) {
            g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);
        }

        if (!hideText) {
            text.setDimensions(bounds.width, bounds.height);
            text.render(g);
        }

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
