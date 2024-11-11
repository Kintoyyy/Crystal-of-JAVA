package Views.Battle;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Dialog.Dialog;
import Components.Menu.StatsBarMenu;
import Game.CallBackAction;
import Skills.Skill;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;

public class BattleView extends View { // Extend JComponent instead of View

    StringBuilder skillsString = new StringBuilder();

    public BattleView(ViewManager viewManager) {
        super(viewManager);

        components.init(
                new StatsBarMenu(handler)
                        .setLocation(20, 20)
                        .scale(4),

                new StatsBarMenu(handler)
                        .setLocation(600, 550)
                        .scale(4),

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
                                viewManager.setView(ViewEnums.GAME);
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

        // Display the skill names at the specified position
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(handler.getGameState().getPlayer().getSkills().toString(), 20, 400);


    }

    @Override
    public void tick() {
        components.tick();

    }
}
