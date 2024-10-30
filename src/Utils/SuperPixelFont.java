package Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SuperPixelFont extends Font {
    private final String fontPath = "res/fonts/SuperPixel.ttf";

    public SuperPixelFont(float size) {
        super("SuperPixel", Font.PLAIN, Math.round(size)); // Initialize the superclass

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(size);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            this.name = customFont.getName();
            this.size = customFont.getSize();
            this.style = customFont.getStyle();

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            this.name = "Serif";
            this.size = Math.round(size);
            this.style = Font.PLAIN;
        }
    }
}
