package Components.Dialog;

import Components.UIComponent;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Dialog extends UIComponent {
    private Text text;
    public final BufferedImage dialogImage;

    private boolean renderText = true;

    public Dialog(String textContent) {
        super();
        this.width = 183;
        this.height = 46;
        this.scale(2);

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        dialogImage = sheet.crop(0, 45, 183, 46);

        text = (Text) new Text(textContent)
                .typing()
                .setFont(new SuperPixelFont(24))
                .setColor(new Color(116, 63, 58))
                .setLocation(20, 20)
                .setParent(this)
                .showBounds();

        this.addChildren(text);
    }

    @Override
    public void tick() {
        text.tick();
    }

    @Override
    public void render(Graphics g) {

        if (renderText) {
            text.render(g);
        }

        if (showBounds) {
            g.setColor(Color.PINK);
            g.drawRect(this.x, this.y, this.width, this.height);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        if (text.isTypingComplete()) {
            text.resetTyping();
        }
    }

    public Text getTextObj() {
        return text;
    }

    public void setText(String newText) {
        text.setText(newText);
        text.resetTyping();
    }
}
