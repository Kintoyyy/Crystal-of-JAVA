package Views.Overlay;

import Components.Button.Button;
import Components.Dialog.Dialog;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.util.ArrayList;

public class BattleDialog extends View {
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

        components.addComponents(
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
        dialog.render(g);

        components.render(g);
    }

    @Override
    public void setData(Object data) {
    }
}
