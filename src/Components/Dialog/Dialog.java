    package Components.Dialog;

    import Components.Component;
    import Components.Text.Text;
    import Utils.ImageUtils;
    import Utils.SpriteSheet;
    import fonts.SuperPixelFont;

    import java.awt.*;
import java.awt.image.BufferedImage;

public class Dialog extends Component {

    private final BufferedImage dialogImage;
    private final String path = "/ui/Game_UI.png";

    public Dialog(String textContent) {
        super(0, 0, 183, 46);

        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        dialogImage = sheet.crop(0, 45, 183, 46);

        this.children(
                new Text(textContent)
                        .typing()
                        .setFont(new SuperPixelFont(24))
                        .setColor(new Color(116,63,58))
                        .showBounds()
                        .setParent(this)
                        .setLocation(20, 20)
        );
    }

    @Override
    public void tick() {
        tickChildren();
    }


    public Dialog setScale(int scale) {
        this.width = width * scale;
        this.height = height * scale;
        return this;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(dialogImage, bounds.x, bounds.y, width, height, null);

        renderChildren(g);

        if(showBounds){
            g.setColor(Color.PINK);
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }

    @Override
    public void onClick() {
        // Define behavior on dialog click
    }
}
