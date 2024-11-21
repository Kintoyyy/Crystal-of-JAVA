package Worlds;

import Entities.Enemies.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battle {
    private final String battleKey;
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Reward> rewards = new ArrayList<>();
    private final ArrayList<String> preBattleDialogs = new ArrayList<>();
    private final ArrayList<String> postBattleDialogs = new ArrayList<>();

    // rewards[]
    // dialog scenes

    public Battle(String battleKey) {
        this.battleKey = battleKey;
    }

    public Battle enemies(Enemy... enemies) {
        this.enemies.addAll(Arrays.asList(enemies));
        return this;
    }

    public Battle rewards(Reward... rewards) {
        this.rewards.addAll(Arrays.asList(rewards));
        return this;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public String getKey() {
        return battleKey;
    }

    public boolean isComplete() {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                return false;
            }
        }
        return true;
    }

    // TODO: Add dialog scenes
    public Battle dialogBefore(String... s) {
        preBattleDialogs.addAll(List.of(s));
        return this;
    }

    public Battle dialogAfter(String... s) {
        postBattleDialogs.addAll(List.of(s));
        return this;
    }

    public ArrayList<String> getPostBattleDialogs() {
        return postBattleDialogs;
    }

    public ArrayList<String> getPreBattleDialogs() {
        return preBattleDialogs;
    }
}
