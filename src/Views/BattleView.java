package Views;

import Components.Button.Button;
import Components.Button.PlayButton;
import Game.CallBackAction;
import enums.ViewEnums;

import javax.swing.*;
import java.awt.*;

public class BattleView extends View { // Extend JComponent instead of View
    public BattleView(ViewManager viewManager) {
        super(viewManager);

        components.init(
                new Components.Button.Button("run")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.START_MENU);
                            }
                        })
                        .setLocation(400, 400),

                new Button("attack")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("attack clicked");
                            }
                        })
                        .setLocation(400, 480)
        );

    }

    @Override
    public void render(Graphics g) {
        components.render(g);
    }

    @Override
    public void tick() {
        components.tick();
    }
}
