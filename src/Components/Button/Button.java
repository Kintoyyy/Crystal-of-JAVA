package Components.Button;

import Components.Component;
import Components.Text.Text;
import Game.CallBackAction;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import enums.Alignment;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Component {
    protected BufferedImage[] buttonSheet = new BufferedImage[3];
    protected SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));
    private CallBackAction clicker;
    private boolean hideText = false;
    private final Text text;

    public Button(String textContent) {
        super();

        this.width = 200;
        this.height = 60;

        buttonSheet[0] = sheet.crop(0, 0, 47, 14);
        buttonSheet[1] = sheet.crop(48, 0, 47, 14);
        buttonSheet[2] = sheet.crop(96, 0, 47, 14);

        this.text = (Text) new Text(textContent)
                .setFont(new SuperPixelFont(30))
                .setAlignment(Alignment.CENTER)
                .setColor(new Color(184, 111, 80))
                .setParent(this)
                .setDimensions(this.width, this.height);
    }

    public Button setButtonSheet(BufferedImage[] buttonSheet) {
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

    @Override
    public void tick() {
        text.tick();
    }

    @Override
    public void render(Graphics g) {
        // Choose image based on state
        BufferedImage buttonImage = switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };

        if (buttonImage != null) {
            g.drawImage(buttonImage, bounds.x, bounds.y, width, height, null);
        }

        if (!hideText) {
            updateTextAppearance();
            text.render(g);
        }

        if (showBounds) {
            g.setColor(Color.BLUE);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public void onClick() {
        if (clicker != null) {
            clicker.onClick();
        } else {
            System.out.println("Warning: No action defined for button click.");
        }
    }

    private void updateTextAppearance() {
        switch (state) {
            case HOVERED -> text.setLocation(0, 4);
            case PRESSED -> {
                text.setLocation(0, 8);
                text.setColor(new Color(116, 63, 57));
            }
            default -> {
                text.setLocation(0, 0);
                text.setColor(new Color(184, 111, 80));
            }
        }
        text.setDimensions(bounds.width, bounds.height);
    }
}