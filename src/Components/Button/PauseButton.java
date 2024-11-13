package Components.Button;

import Utils.ImageUtils;
import Utils.SpriteSheet;

public class PauseButton extends Button {
    public PauseButton() {
        super("Pause");

        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));

        buttonSheet[0] = sheet.crop(144, 48, 14, 14);
        buttonSheet[1] = sheet.crop(160, 48, 14, 14);
        buttonSheet[2] = sheet.crop(176, 48, 14, 14);

        this.setDimensions(64, 64);
        this.hideText();
    }
}
