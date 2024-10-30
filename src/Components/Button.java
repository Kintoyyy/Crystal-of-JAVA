package Components;

import Utils.SpriteSheet;
import Utils.ImageUtils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Button extends Component {
    BufferedImage buttonSheet[] = new BufferedImage[3];
    private int x;
    private int y;
    private int width;
    private int height;

    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        SpriteSheet buttonImage = new SpriteSheet(ImageUtils.loadImage("/ui/UI_Buttons.png"));
        buttonSheet[0] = buttonImage.crop(142,280,49,15);
        buttonSheet[1] = buttonImage.crop(192,280,49,15);
        buttonSheet[2] = buttonImage.crop(238,280,49,15);
//        super(image, height, width);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering) {
            g.drawImage(buttonSheet[2], (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(buttonSheet[1], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onMouseMove(MouseEvent e) {

    }

    @Override
    public void onMouseClick() {
        System.out.println("Clicked");
    }

    @Override
    public void onMouseRelease(MouseEvent e) {

    }
}

