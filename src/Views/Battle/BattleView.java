package Views.Battle;

import Components.Button.Button;
import Components.Button.PauseButton;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class BattleView extends View { // Extend JComponent instead of View
    public BattleView(ViewManager viewManager) {
        super(viewManager);

        components.init(
                new PauseButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(900, 20),

                new Button("chicken")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("chicken out");
                            }
                        })
                        .setLocation(680, 20),

                new Button("skill1")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("attack 1 clicked");
                            }
                        })
                        .setLocation(20, 700),

                new Button("skill1")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("attack 2 clicked");
                            }
                        })
                        .setLocation(230, 700),

                new Button("skill1")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("attack 3 clicked");
                            }
                        })
                        .setLocation(440, 700)
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
