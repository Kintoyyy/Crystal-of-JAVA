package Components.Layouts;

import Components.Component;
import Components.Button.EnemyButton;
import Entities.Enemies.*;
import Battle.BattleManager;

import java.awt.*;
import java.util.List;

public class EnemyLayout extends Layout {
    private EnemyManager currentEnemyManager; // Tracks the active EnemyManager
    private List<Enemy> cachedEnemies;       // Cache the enemies list

    public EnemyLayout(EnemyManager enemyManager) {
        super();
        this.cachedEnemies = List.of(); // Empty cached enemies initially
    }

    private void initEnemyFrames() {
        childComponents.clear();

        for (int i = 0; i < cachedEnemies.size(); i++) {
            Enemy enemy = cachedEnemies.get(i);
            final int index = i; // Capture index for lambda use

            EnemyButton frame = (EnemyButton) new EnemyButton(enemy)
                    .setAction(() -> {
                        System.out.println("Enemy " + enemy.getName() + " clicked");
                        // Uncomment if needed:
                        // currentEnemyManager.setAutoSelectEnemy(false);
                         currentEnemyManager.setCurrentEnemy(index);
                    });
            childComponents.add(frame);
        }
    }

    private boolean updateEnemyManager() {
        EnemyManager newEnemyManager = enemyManager;

        if (newEnemyManager != currentEnemyManager) {
            currentEnemyManager = newEnemyManager;

            // Update the cached enemies list
            if (currentEnemyManager != null) {
                cachedEnemies = currentEnemyManager.getEnemies();
            } else {
                cachedEnemies = List.of(); // Reset to empty list if no manager
            }
            return true;
        }
        return false;
    }

    private boolean hasEnemiesChanged() {
        List<Enemy> newEnemies = (currentEnemyManager != null)
                ? currentEnemyManager.getEnemies()
                : List.of();

        if (!cachedEnemies.equals(newEnemies)) {
            cachedEnemies = newEnemies; // Update the cached list
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        // Check for changes in the EnemyManager or enemy list
        if (updateEnemyManager() || hasEnemiesChanged()) {
            initEnemyFrames();
        }

        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;
        Enemy enemy = (currentEnemyManager != null)
                ? currentEnemyManager.getEnemy()
                : null;
        for (Component component : childComponents) {
            if (component instanceof EnemyButton frame) {
                frame.setLocation(xOffset, (int) this.y);
                frame.isActive(enemy != null && enemy.equals(frame.getEnemy()));
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
