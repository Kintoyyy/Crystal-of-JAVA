package Components.Button;

import Utils.ImageUtils;
import Utils.SpriteSheet;

public class CharacterSelect extends Button {
    public CharacterSelect(String textContent) {
        super(textContent);

        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));

        buttonSheet[0] = sheet.crop(0, 16, 47, 14);
        buttonSheet[1] = sheet.crop(48, 16, 47, 14);
        buttonSheet[2] = sheet.crop(96, 16, 47, 14);

        this.hideText();
    }
}
