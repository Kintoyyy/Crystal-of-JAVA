package Worlds;

import Entities.Enemies.Enemy;

import java.util.ArrayList;
import java.util.Arrays;

public class Battle {
    protected String battleKey;
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected ArrayList<Reward> rewards = new ArrayList<>();

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
}
