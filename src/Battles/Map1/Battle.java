package Battles.Map1;

import Entities.Enemies.Enemy;

import java.util.ArrayList;

public abstract class Battle {
    protected final ArrayList<Enemy> enemies = new ArrayList<>();

    public Battle() {
    }
}
