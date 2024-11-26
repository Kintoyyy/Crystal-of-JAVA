package Views.Overlay;

import Components.Button.Button;
import Components.Dialog.Dialog;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.util.ArrayList;

public class BattleDialog extends View {
    private String[] dialogText = new String[]{"World", "World"};
    private int dialogIndex = 0;
    private final Dialog dialog;
    private final ArrayList<String> preBattleDialogs;

    public BattleDialog(ArrayList<String> preBattleDialogs) {
        this.preBattleDialogs = preBattleDialogs;
        isOverlay = true;

        dialog = (Dialog) new Dialog(preBattleDialogs.get(dialogIndex))
                .showBounds()
                .setLocation(40, 580)
                .scale(2);

        components.init(
//                new Button("Next")
//                        .setRightClickAction(() ->
//                                dialogIndex = dialogIndex == dialogText.length - 1 ? 0 : dialogIndex + 1
//                        )
//                        .setLocation(780, 720),
//
//                new Button("Back")
//                        .setRightClickAction(() ->
//                                dialogIndex = dialogIndex == 0 ? dialogText.length - 1 : dialogIndex - 1
//                        )
//                        .setLocation(30, 720),

                new Button("Battle")
                        .setRightClickAction(() ->
                                viewManager.setView(Views.BATTLE)
                        )
                        .setLocation(780, 320),

                new Button("Exit")
                        .setRightClickAction(() ->
                                viewManager.setView(Views.GAME)
                        )
                        .setLocation(30, 320)
        );
    }

    @Override
    public void tick() {
        dialog.tick();
        components.tick();
    }

    @Override
    public void render(Graphics g) {
//        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_1.png"));
//
//        BufferedImage background = backgroundSheet.crop(0, 0, 800, 678);

//        g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);

        dialog.render(g);

        components.render(g);
    }

    @Override
    public void setData(Object data) {
        dialogText = (String[]) data;
    }
}
