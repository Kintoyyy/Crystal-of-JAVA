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

    private boolean isDataLoaded = false;

    public BattleManager(Handler handler, WorldManager worldManager) {
        handler.setBattleManager(this);
        this.handler = handler;
        this.viewManager = handler.getViewManager();
        this.worldManager = worldManager;

        timer.setDelay(5).setAction(() -> {
            System.out.println("Timer action");
            abortBattle();
        });

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

        this.isDataLoaded = false;
        currentEnemyIndex = 0;

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
        // Check if all enemies are dead
        boolean allEnemiesDead = true;
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                allEnemiesDead = false;
                break; // No need to continue if one enemy is alive
            }
        }

        if (allEnemiesDead && !timer.isActive()) {
            System.out.println("All enemies dead");
            timer.start(); // Start the timer if all enemies are dead
        }

        System.out.println("Battle tick: " + timer.getTime()+ " enemies left");


        // If there are no enemies left in the list
        if (enemies.isEmpty()) {
            System.out.println("Battle ended");
            viewManager.setView(Views.GAME);
            return;
        }

        timer.update(); // Update the timer regardless
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
        return isDataLoaded;
    }

    public void setDataLoaded(boolean isDataLoaded) {
        this.isDataLoaded = isDataLoaded;
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
        if (enemies.isEmpty()) {
            return null;
        }
        return enemies.get(currentEnemyIndex);
    }

    public void killAllEnemies() {
        for (Enemy enemy : enemies) {
            enemy.getHealth().setHealth(0);
        }
    }
}
