package Components.Button;

import Utils.ImageUtils;
import Utils.SpriteSheet;

public class RoundedButton extends Button {
    public RoundedButton(String textContent) {
        super(textContent);

        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));

        buttonSheet[0] = sheet.crop(0, 0, 47, 14);
        buttonSheet[1] = sheet.crop(48, 0, 47, 14);
        buttonSheet[2] = sheet.crop(96, 0, 47, 14);
    }
}
