package Worlds;

import Entities.Enemies.Enemy;

import java.util.ArrayList;

public abstract class Battle {
    protected String name;
    protected String objectId;
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    // rewards[]
    // dialog scenes
    public Battle() {

    }
}
