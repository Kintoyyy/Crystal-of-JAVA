package Enemies;

import java.util.ArrayList;

public class EnemyManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int attackingEnemyIndex = 0;
    private int currentEnemyIndex = 0;
    private boolean autoSelectEnemy = false;

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemy() {
        return enemies.get(currentEnemyIndex);
    }

    public void loadEnemies(ArrayList<Enemy> enemies){
        this.enemies.clear();
        this.enemies.addAll(enemies);
    }

    public void tick() {

    }

    public void updateTurns() {
        if (attackingEnemyIndex < enemies.size() - 1) {
            attackingEnemyIndex++;
        } else {
            attackingEnemyIndex = 0;
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public int getSize() {
        return enemies.size();
    }

    public Enemy getEnemyByIndex(int i) {
        return enemies.get(i);
    }

    public void setCurrentEnemy(int index) {
        this.currentEnemyIndex = index;
    }

    public Enemy getCurrentEnemy() {
//        if(autoSelectEnemy) {
//            int size = enemies.size();
//            int randomIndex = (int) (Math.random() * size);
//            return enemies.get(randomIndex);
//        }
        return enemies.get(currentEnemyIndex);
    }

    public boolean isAutoSelectEnemy() {
        return autoSelectEnemy;
    }

    public void setAutoSelectEnemy(boolean autoSelectEnemy) {
        this.autoSelectEnemy = autoSelectEnemy;
    }
}
