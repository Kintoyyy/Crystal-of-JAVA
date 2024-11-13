package Enemies;

import java.util.ArrayList;

public class EnemyManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int currentEnemyIndex = 0;

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
        return enemies.get(currentEnemyIndex);
    }
}
