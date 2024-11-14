package Views.Battle;

import Components.Button.*;
import Components.Button.Button;
import Components.Layouts.*;
import Components.Menu.SkillMenu;
import Enemies.*;
import Game.BattleManager;
import Game.TurnState;
import Utils.Timer;
import Views.*;
import enums.ViewEnums;

import java.awt.*;
import java.util.ArrayList;

public class BattleView extends View {
    private final BattleManager battleManager;

    private int seconds = 0;
    private Timer timer = new Timer();

    public BattleView(ViewManager viewManager) {
        super(viewManager);

        EnemyManager enemies = new EnemyManager();

        enemies.addEnemy(new Kai());
        enemies.addEnemy(new Orc());
        enemies.addEnemy(new Goblin());

        battleManager = new BattleManager(handler);

        battleManager.newBattle(enemies);

        components.init(

                new CharacterLayout(battleManager)
                        .setLocation(100, 300)
                        .scale(6),

                new SkillMenu(battleManager)
                        .setLocation(500, 650),

                new EnemyLayout(battleManager)
                        .setLocation(700, 300)
                        .showBounds()
                        .scale(6),

                new PauseButton()
                        .setAction(() -> viewManager.setView(ViewEnums.PAUSE))
                        .setLocation(900, 20),

                new Button("exit")
                        .setAction(() -> viewManager.setView(ViewEnums.GAME))
                        .setLocation(680, 20)
        );
    }

    @Override
    public void tick() {
        battleManager.tick();
//        if (handler.getGameState().getEnemies().isEmpty()) {
//            viewManager.setView(ViewEnums.GAME);
//        }

        components.tick();
    }

    @Override
    public void render(Graphics g) {
        // return to the game view if there are no enemies left

        components.render(g);

        // Display the skill names at the specified position
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(handler.getGameState().getCharacterManger().getPlayer().getSkills().toString(), 20, 50);
        g.drawString((battleManager.getTurnState() == TurnState.PLAYER ? "Player" : "Enemy") + " turn", 500, 400);
//        g.drawString(handler.getGameState(), 20, 70);
    }


}
