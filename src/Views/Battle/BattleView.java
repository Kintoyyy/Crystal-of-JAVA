package Views.Battle;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Layouts.CharacterLayout;
import Components.Menu.BattleCharacterMenu;
import Components.Menu.SkillMenu;
import Components.Menu.StatsBarMenu;
import Enemies.Kai;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class BattleView extends View { // Extend JComponent instead of View
    public BattleView(ViewManager viewManager) {
        super(viewManager);

        // test enemy
        handler.getGameState().getEnemies().add(new Kai());

        components.init(
                new SkillMenu(handler)
                        .setLocation(20, 600),

                new CharacterLayout(handler)
                        .setLocation(100, 300)
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
        // return to the game view if there are no enemies left
        if(handler.getGameState().getEnemies().isEmpty()) {
            viewManager.setView(ViewEnums.GAME);
        }

        components.render(g);

        // Display the skill names at the specified position
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(handler.getGameState().getPlayer().getSkills().toString(), 20, 50);
        g.drawString(handler.getGameState().getEnemies().toString(), 20, 70);
    }

    @Override
    public void tick() {
        components.tick();

    }
}
