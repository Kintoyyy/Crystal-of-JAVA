package Views.Battle;

import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class BattleView extends View { // Extend JComponent instead of View
    public BattleView(ViewManager viewManager) {
        super(viewManager);

        components.init(
                new Components.Button.Button("pause")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(700, 400),

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
