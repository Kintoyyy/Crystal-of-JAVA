package Views.Battle;

import Components.Card.BattleStatistic;
import Components.Menu.SkillMenu;
import Components.Button.*;
import Components.Button.Button;
import Components.Layouts.*;
import Views.*;
import Views.enums.Views;
import Battle.BattleManager;

import java.awt.*;

public class Battle extends View {
    private final BattleManager battleManager;
    public Battle(ViewManager viewManager) {
        super(viewManager);
        this.battleManager = handler.getBattleManager();

        components.init(
                new CharacterLayout(battleManager)
                        .setLocation(100, 300)
                        .scale(6),

                new SkillMenu(battleManager)
                        .setLocation(500, 650),

                new BattleStatistic(battleManager)
                        .setLocation(100, 100),

                new EnemyLayout(battleManager)
                        .setLocation(700, 300)
                        .showBounds()
                        .scale(6),

                new PauseButton()
                        .setAction(() -> viewManager.setView(Views.PAUSE))
                        .setLocation(900, 20),



                new Button("exit")
                        .setAction(battleManager::abortBattle)
                        .setLocation(680, 20)
        );
    }

    @Override
    public void tick() {
        battleManager.tick();
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        components.render(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(handler.getGameState().getCharacterManger().getPlayer().getSkills().toString(), 20, 50);
//        g.drawString((battleManagerOld.getTurnState() == Turn.PLAYER ? "Player" : "Enemy") + " turn", 500, 400);
//        g.drawString(handler.getGameState(), 20, 70);
    }


}
