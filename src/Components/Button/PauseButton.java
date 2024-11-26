package Components.Button;

import Components.ComponentEventListener;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.enums.Views;

import java.awt.event.MouseEvent;

public class PauseButton extends Button {
    public PauseButton() {
        super("Pause");
        this.hideText();
        sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Menu_UI.png"));

        buttonSheet[0] = sheet.crop(144, 48, 14, 14);
        buttonSheet[1] = sheet.crop(160, 48, 14, 14);
        buttonSheet[2] = sheet.crop(176, 48, 14, 14);

        this.setDimensions(64, 64);

        this.setEventListener(new ComponentEventListener() {
            @Override
            public void onComponentClick(MouseEvent event) {
                viewManager.setView(Views.PAUSE);
            }

            @Override
            public void onMouseEnter(MouseEvent event) {

            }

            @Override
            public void onMouseExit(MouseEvent event) {

            }
        });
    }
}
