package Battle;

import Entities.Characters.CharacterManager;
import Entities.Enemies.Enemy;
import Game.Handler;
import Utils.Timer;
import Views.ViewManager;
import Views.enums.Views;
import Worlds.Battle;
import Worlds.Enums.Turn;
import Worlds.WorldManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BattleManager {
    private final Handler handler;
    private final ViewManager viewManager;
    private final WorldManager worldManager;

    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int currentEnemyIndex = 0;

    private final Timer timer = new Timer();

    private final Queue<Turn> turnqueue = new LinkedList<>();

    public BattleManager(Handler handler, WorldManager worldManager) {
        handler.setBattleManager(this);
        this.handler = handler;
        this.viewManager = handler.getViewManager();
        this.worldManager = worldManager;

        turnqueue.add(Turn.PLAYER);
        turnqueue.add(Turn.ENEMY);
    }

    public void startBattle(String battleName) {
        Battle battle = worldManager.getBattle(battleName);

        if (battle.isComplete()) {
            System.out.println("Battle already completed: " + battleName);
            return;
        }

        if (battle == null) {
            System.out.println("Battle not found: " + battleName);
            return;
        }

        System.out.println(" world: " + worldManager.getCurrentWorld().getName() + " enemies: " + battle.getEnemies());

        loadEnemies(battle.getEnemies());

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
        if (enemies.isEmpty()) {
            System.out.println("Battle ended");
            viewManager.setView(Views.GAME);
            return;
        }
        timer.update();
    }

    public Handler getHandler() {
        return handler;
    }

    public CharacterManager getCharacterManager() {
        return handler.getGameState().getCharacterManger();
    }

    public void abortBattle() {
        enemies.clear();
        System.out.println("Aborting battle: " + enemies);
        viewManager.setView(Views.GAME);
    }

    public boolean isDataLoaded() {
        return !enemies.isEmpty();
    }

    public void loadEnemies(ArrayList<Enemy> enemies) {
        this.enemies.clear();
        this.enemies.addAll(enemies);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setCurrentEnemy(int index) {
        this.currentEnemyIndex = index;
    }

    public Enemy getCurrentEnemy() {
        if(enemies.isEmpty()) {
            return null;
        }
        return enemies.get(currentEnemyIndex);
    }
}
