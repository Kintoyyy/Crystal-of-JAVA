package Battle;

import Entities.Characters.CharacterManager;
import Entities.Enemies.EnemyManager;
import Game.Handler;
import Utils.Timer;
import Views.ViewManager;
import Views.enums.Views;
import Worlds.Battle;
import Worlds.Enums.Turn;

import java.util.LinkedList;
import java.util.Queue;

public class BattleManager {
    protected final Handler handler;
    private final EnemyManager enemyManager;
    private final ViewManager viewManager;

    private final Timer timer = new Timer();

    protected Queue<Turn> turnqueue = new LinkedList<>();


    public BattleManager(Handler handler) {
        this.handler = handler;
        this.viewManager = handler.getViewManager();
        this.enemyManager = new EnemyManager();

        // set the gameBattleManagen i hendler
        handler.setBattleManager(this);
        turnqueue.add(Turn.PLAYER);
        turnqueue.add(Turn.ENEMY);
    }

    public void startBattle(String battleName) {
        // Execption
        System.out.println("Starting battle: " + battleName + " world: " + " enemies: " + handler.getWorldManager().getBattle(battleName).getEnemies());

        Battle battle = handler.getWorldManager().getBattle(battleName);

        enemyManager.loadEnemies(battle.getEnemies());

        // start battle
        viewManager.setView(Views.BATTLE);
    }

    public void updateTurn() {
        Turn turn = turnqueue.remove();
        turnqueue.add(turn);
    }

    public Turn getCurrentTurn() {
        return turnqueue.peek();
    }

    public void skipTurn() {
        Turn turn = turnqueue.remove();
        turnqueue.add(turn);
        turnqueue.add(turn);
    }

    public void tick() {
        timer.update();
    }

    public Handler getHandler() {
        return handler;
    }

    public CharacterManager getCharacterManager() {
        return handler.getGameState().getCharacterManger();
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public void abortBattle() {
        enemyManager.getEnemies().clear();
        System.out.println("Aborting battle: " + enemyManager.getEnemies());
        viewManager.setView(Views.GAME);
    }
}
