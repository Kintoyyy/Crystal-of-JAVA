package Components;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class ImageButton {

    private Image iconImage;
    private Rectangle bounds;  // To track the button’s position and size

    public ImageButton( int x, int y, int width, int height) {
        loadImage(width, height);
        bounds = new Rectangle(x, y, width, height);
    }

    private void loadImage( int width, int height) {
        ImageIcon icon = new ImageIcon("/ui/UI_Buttons.png");
        this.iconImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void render(Graphics g) {
        g.drawImage(iconImage, bounds.x, bounds.y, bounds.width, bounds.height, null);
    }

    // For mouse handling, check if the button is clicked
    public boolean isClicked(int mouseX, int mouseY) {
        return bounds.contains(mouseX, mouseY);
    }
}
