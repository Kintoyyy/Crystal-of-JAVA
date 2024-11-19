package Components.Layouts;

import Components.Component;
import Components.Button.EnemyButton;
import Entities.Enemies.*;
import Battle.BattleManager;

import java.awt.*;
import java.util.List;

public class EnemyLayout extends Layout {
    private List<Enemy> cachedEnemies;       // Cache the enemies list
    private final EnemyManager enemyManager;


    public EnemyLayout(EnemyManager enemyManager) {
        super();
        this.enemyManager = enemyManager;
        this.cachedEnemies = List.of(); // Empty cached enemies initially
        initEnemyFrames();
    }

    private void initEnemyFrames() {
        childComponents.clear();

        cachedEnemies = enemyManager.getEnemies();

        for (int i = 0; i < cachedEnemies.size(); i++) {
            Enemy enemy = cachedEnemies.get(i);
            final int index = i; // Capture index for lambda use

            EnemyButton frame = (EnemyButton) new EnemyButton(enemy)
                    .setAction(() -> {
                        System.out.println("Enemy " + enemy.getName() + " clicked");
                        // Uncomment if needed:
                        // currentEnemyManager.setAutoSelectEnemy(false);
                        enemyManager.setCurrentEnemy(index);
                    });
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
//        if (enemyManager.getEnemies().isEmpty()) {
//            System.out.println("EnemyLayout ticked");
//            initEnemyFrames();
//        }

//        System.out.println("EnemyLayout ticked" + enemyManager.getEnemies().size());

        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;
        Enemy enemy = (enemyManager != null)
                ? enemyManager.getEnemy()
                : null;
        assert enemyManager != null;

//        System.out.println("EnemyLayout rendered" + enemyManager.getEnemies().size());

        for (Component component : childComponents) {
            if (component instanceof EnemyButton frame) {
                frame.setLocation(xOffset, (int) this.y);
//                frame.isActive(enemy != null && enemy.equals(frame.getEnemy()));
                xOffset += frame.getWidth();
            }
            component.render(g);
        }
    }

    @Override
    public void onClick() {
        childComponents.forEach(Component::onClick);
    }
}
