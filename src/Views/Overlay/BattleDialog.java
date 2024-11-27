package Views.Overlay;

import Components.Button.Button;
import Components.ComponentEventListener;
import Components.Dialog.Dialog;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BattleDialog extends View {
    private int dialogIndex = 0;
    private final Dialog dialog;
    private final ArrayList<String> preBattleDialogs;
    private final Runnable onComplete;

    public BattleDialog(ArrayList<String> preBattleDialogs, Runnable onComplete) {
        this.preBattleDialogs = preBattleDialogs;
        this.onComplete = onComplete;
        isOverlay = true;

        dialog = (Dialog) new Dialog(preBattleDialogs.get(dialogIndex))
                .showBounds()
                .setLocation(40, 580)
                .scale(2);

        components.addComponents(
                new Button("Next")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                if (dialogIndex < preBattleDialogs.size() - 1) {
                                    dialogIndex++;
                                    dialog.getTextObj().setText(preBattleDialogs.get(dialogIndex));
                                } else {
                                    onComplete.run();
                                }
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
                        .setLocation(780, 320),

                new Button("Exit")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                viewManager.setView(Views.GAME);
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
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
        dialog.setText(preBattleDialogs.get(dialogIndex));
        dialog.render(g);
        components.render(g);
    }

    @Override
    public void setData(Object data) {
    }
}
