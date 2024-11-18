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
    protected Queue<Turn> turnqueue = new LinkedList<>();
    // rewards[]
    // dialog scenes

    public Battle() {
        enemies.add(new Kai());
        enemies.add(new Orc());
        enemies.add(new Goblin());
    }

    public Queue<Turn> getTurnqueue() {
        return turnqueue;
    }

    public void addQueue(Turn turn) {
        turnqueue.add(turn);
    }
}
