package Battle;

import Entities.Characters.CharacterManager;
import Entities.Enemies.EnemyManager;
import Game.Handler;
import Utils.Timer;
import Views.ViewManager;
import Views.enums.Views;
import Worlds.Battle;
import Worlds.Enums.Turn;
import Worlds.WorldManager;

import java.util.LinkedList;
import java.util.Queue;

public class BattleManager {
    protected final Handler handler;
    private final EnemyManager enemyManager;
    private final ViewManager viewManager;
    private final WorldManager worldManager;

    private final Timer timer = new Timer();

    protected Queue<Turn> turnqueue = new LinkedList<>();

    public BattleManager(Handler handler, WorldManager worldManager) {
        handler.setBattleManager(this);
        this.handler = handler;
        this.viewManager = handler.getViewManager();
        this.worldManager = worldManager;
        this.enemyManager = new EnemyManager();


        // set the gameBattleManagen i hendler

        turnqueue.add(Turn.PLAYER);
        turnqueue.add(Turn.ENEMY);
    }

    public void startBattle(String battleName) {
        Battle battle = worldManager.getBattle(battleName);

        if(battle == null) {
            System.out.println("Battle not found: " + battleName);
            return;
        }

        System.out.println(" world: " + worldManager.getCurrentWorld().getName() + " enemies: " + battle.getEnemies());

        enemyManager.loadEnemies(battle.getEnemies());

        viewManager.setView(Views.BATTLE);
        // start battle
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
