package Components.Layouts;

import Enemies.Enemy;
import Game.Handler;

import java.util.ArrayList;

public class EnemyLayout extends Layout{

    private final ArrayList<Enemy> enemies;
    private final Handler handler;

    EnemyLayout(Handler handler) {
        super();
        this.handler = handler;
        this.enemies = handler.getGameState().getEnemies();

    }

}
