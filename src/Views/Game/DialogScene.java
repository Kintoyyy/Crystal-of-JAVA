package Views.Game;

import Components.Button.Button;
import Components.Text.Text;
import Views.View;
import Views.enums.Views;
import fonts.SuperPixelFont;

import java.awt.*;
import java.util.ArrayList;

public class DialogScene extends View {
    private int dialogIndex = 0;
//    private final Dialog dialog;
    private final ArrayList<String> preBattleDialogs;
    private final Text text;

    public DialogScene(ArrayList<String> preBattleDialogs) {
        this.preBattleDialogs = preBattleDialogs;
        isOverlay = true;

        text = (Text) new Text("")
                .typing()
                .setFont(new SuperPixelFont(24))
                .setColor(new Color(116, 63, 58))
                .setLocation(400, 400)
                .showBounds();

        components.addComponents(
                new Button("Next")
                        .setRightClickAction(() ->
                                dialogIndex = dialogIndex == preBattleDialogs.toArray().length - 1 ? 0 : dialogIndex + 1
                        )
                        .setLocation(780, 720),

                new Button("Exit")
                        .setRightClickAction(() ->
                                viewManager.setView(Views.GAME)
                        )
                        .setLocation(30, 320)
        );
    }

    @Override
    public void tick() {
        text.tick();
        text.setText(preBattleDialogs.get(dialogIndex));
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        text.render(g);

        components.render(g);
    }

    @Override
    public void setData(Object data) {
    }
}
