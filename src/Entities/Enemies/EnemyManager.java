package Entities.Enemies;

import Game.Handler;

import java.util.ArrayList;

public class EnemyManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int currentEnemyIndex = 0;

    public EnemyManager(Handler handler){

    }

    public void loadEnemies(ArrayList<Enemy> enemies) {
        System.out.println(enemies);
        this.currentEnemyIndex = 0;
        this.enemies.clear();
        this.enemies.addAll(enemies);
    }

    public void setCurrentEnemy(int index) {
        System.out.println("Enemy Selected: " + index);
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

    public boolean isEmpty() {
        return enemies.isEmpty();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void clearEnemies() {
        enemies.clear();
    }
}
