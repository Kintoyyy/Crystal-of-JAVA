package Views.Battle;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Frame.EnemyFrame;
import Components.Layouts.CharacterLayout;
import Components.Menu.BattleSkillMenu;
import Enemies.Kai;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class BattleView extends View {
    public BattleView(ViewManager viewManager) {
        super(viewManager);

        handler.getGameState().getEnemies().add(new Kai());

        components.init(
                new CharacterLayout(handler)
                        .setLocation(100, 300)
                        .scale(6),

                new BattleSkillMenu(handler)
                        .setLocation(500, 650),

//                new EnemyLayout(handler)
//                        .setLocation(100, 100)
//                        .showBounds()
//                        .scale(6),

                new EnemyFrame(handler.getGameState().getEnemies().getFirst())
                        .setLocation(700, 300),


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

        components.tick();
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


}
