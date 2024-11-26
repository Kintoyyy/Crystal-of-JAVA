package Components.Button;

import Components.UIComponent;
import Components.Text.Text;
import Utils.CallBackAction;
import Utils.DebugMode;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Components.enums.Alignment;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Components.enums.States.*;

public class Button extends UIComponent {
    private static final Color IDLE_COLOR = new Color(184, 111, 80);
    private static final Color PRESSED_COLOR = new Color(116, 63, 57);

    protected final BufferedImage[] buttonSheet;
    protected SpriteSheet sheet;
    protected final Text text;
    protected boolean isTextHidden = false;

    private CallBackAction rightClickAction;
    private CallBackAction leftClickAction;

    public Button(String textContent) {
        super();

        // Set default dimensions
        this.width = 200;
        this.height = 60;

        // Load button state images
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));
        this.buttonSheet = new BufferedImage[]{
                sheet.crop(0, 0, 47, 14),   // Idle state
                sheet.crop(48, 0, 47, 14),  // Hovered state
                sheet.crop(96, 0, 47, 14)   // Pressed state
        };

        // Create and configure text
        this.text = (Text) new Text(textContent)
                .setFont(new SuperPixelFont(30))
                .setAlignment(Alignment.CENTER)
                .setColor(IDLE_COLOR)
                .setDimensions(this.width, this.height)
                .setParent(this);
    }

    public Button hideText() {
        this.isTextHidden = true;
        return this;
    }

    public Button showText() {
        this.isTextHidden = false;
        return this;
    }

    public void setActive(boolean isActive) {
        this.state = isActive ? PRESSED : IDLE;
    }

    @Override
    public void tick() {
        text.tick();
    }

    @Override
    public void render(Graphics g) {
        // Render button image
        BufferedImage buttonImage = getButtonImageForState();
        if (buttonImage != null) {
            g.drawImage(buttonImage, this.x, this.y, width, height, null);
        }

        // Render text
        if (!isTextHidden) {
            updateTextAppearance();
            text.render(g);
        }

        // Render debug bounds
        if (DebugMode.isShowComponents()) {
            g.setColor(Color.BLUE);
            g.drawRect(this.x, this.y, this.width, this.height);
        }
    }

    private BufferedImage getButtonImageForState() {
        return switch (state) {
            case HOVERED -> buttonSheet[1];
            case PRESSED -> buttonSheet[2];
            default -> buttonSheet[0];
        };
    }

    private void updateTextAppearance() {
        switch (state) {
            case HOVERED -> {
                text.setLocation(0, 4);
                text.setColor(IDLE_COLOR);
            }
            case PRESSED -> {
                text.setLocation(0, 8);
                text.setColor(PRESSED_COLOR);
            }
            default -> {
                text.setLocation(0, 0);
                text.setColor(IDLE_COLOR);
            }
        }
        text.setDimensions(this.width, this.height);
    }

    @Override
    public void onClick(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && rightClickAction != null) {
            rightClickAction.onAction();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (leftClickAction != null) {
                leftClickAction.onAction();
            } else if (rightClickAction != null) {
                rightClickAction.onAction();
            }
        }
    }

    public Button setRightClickAction(CallBackAction action) {
        this.rightClickAction = action;
        return this;
    }

    public Button setLeftClickAction(CallBackAction action) {
        this.leftClickAction = action;
        return this;
    }
}