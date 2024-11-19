package Worlds;

import Entities.Enemies.Enemy;
import Entities.Enemies.Goblin;
import Entities.Enemies.Kai;
import Entities.Enemies.Orc;
import Worlds.Enums.Turn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Battle {
    protected String name;
    protected ArrayList<Enemy> enemies = new ArrayList<>();

    // rewards[]
    // dialog scenes

    public Battle() {

    }

    public void start() {

    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
}
