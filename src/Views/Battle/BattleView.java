package Views.Battle;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Menu.SkillMenu;
import Components.Menu.StatsBarMenu;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

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

                new SkillMenu(handler)
                        .setLocation(20, 600),

                new Components.Menu.CharacterMenu(handler)
                        .setLocation(200, 550)
                        .scale(6),

                new PauseButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(900, 20),

                new Button("exit")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.GAME);
                            }
                        })
                        .setLocation(680, 20)
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
