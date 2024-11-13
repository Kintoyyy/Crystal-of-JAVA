package Views.Battle;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Button.EnemyButton;
import Components.Layouts.CharacterLayout;
import Components.Layouts.EnemyLayout;
import Components.Menu.SkillMenu;
import Enemies.Kai;
import Enemies.Orc;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class BattleView extends View {

    public BattleView(ViewManager viewManager) {
        super(viewManager);
        handler.getGameState().getEnemies().add(new Kai());
        handler.getGameState().getEnemies().add(new Orc());
//
        handler.getGameState().newBattle(handler.getGameState().getEnemies());

        components.init(

                new CharacterLayout(handler)
                        .setLocation(100, 300)
                        .scale(6),

                new SkillMenu(handler)
                        .setLocation(500, 650),

                new EnemyLayout(handler)
                        .setLocation(700, 300)
                        .showBounds()
                        .scale(6),

                new PauseButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(900, 20),

                new Button("exit")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(ViewEnums.GAME);
                            }
                        })
                        .setLocation(680, 20)
        );
    }

    @Override
    public void tick() {

        if (handler.getGameState().getEnemies().isEmpty()) {
            viewManager.setView(ViewEnums.GAME);
        }

        components.tick();
    }

    @Override
    public void render(Graphics g) {
        // return to the game view if there are no enemies left


        components.render(g);

        // Display the skill names at the specified position
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(handler.getGameState().getPlayer().getSkills().toString(), 20, 50);
        g.drawString(handler.getGameState().getEnemies().toString(), 20, 70);
    }


}
