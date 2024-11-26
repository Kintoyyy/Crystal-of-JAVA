package Views.Game;

import Components.Button.Button;
import Components.Dialog.Dialog;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PreBattleScene extends View {
    private String[] dialogText = new String[]{"World", "World"};
    private int dialogIndex = 0;
    private final Dialog dialog;

    public PreBattleScene(ArrayList<String> string) {

        dialog = (Dialog) new Dialog(dialogText[0])

                .showBounds()
                .setLocation(150, 580)
                .scale(2);

        components.init(
                new Button("Next")
                        .setRightClickAction(() ->
                                dialogIndex = dialogIndex == dialogText.length - 1 ? 0 : dialogIndex + 1
                        )
                        .setLocation(780, 720),
                new Button("Back")
                        .setRightClickAction(() ->
                                dialogIndex = dialogIndex == 0 ? dialogText.length - 1 : dialogIndex - 1
                        )
                        .setLocation(30, 720)
        );
    }

    @Override
    public void tick() {
        dialog.tick();
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_1.png"));

        BufferedImage background = backgroundSheet.crop(0, 0, 800, 678);

        g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);

        dialog.render(g);

        components.render(g);
    }

    @Override
    public void setData(Object data) {
        dialogText = (String[]) data;
    }
}
