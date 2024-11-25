package Components.Dialog;

import Components.Component;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Dialog extends Component {

    private final BufferedImage dialogImage;

    public Dialog(String textContent) {
        super();
        this.width = 183;
        this.height = 46;

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        dialogImage = sheet.crop(0, 45, 183, 46);

        Text text = (Text) new Text(textContent)
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
        tickChildren();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(dialogImage, bounds.x, bounds.y, width, height, null);
        renderChildren(g);

        if (showBounds) {
            g.setColor(Color.PINK);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        // Define behavior on dialog click
    }
}
