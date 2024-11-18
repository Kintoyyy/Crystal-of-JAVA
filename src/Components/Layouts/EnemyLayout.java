package Components.Layouts;

import Components.Component;
import Components.Button.EnemyButton;
import Entities.Enemies.*;
import Worlds.BattleManager;

import java.awt.*;


public class EnemyLayout extends Layout {
    private final EnemyManager enemyManager;

    public EnemyLayout(BattleManager battleManager) {
        super();
        this.enemyManager = battleManager.getEnemyManager();
        initEnemyFrames();
    }

    private void initEnemyFrames() {
        childComponents.clear();
        for (int i = 0; i < enemyManager.getSize(); i++) {
            Enemy enemy = enemyManager.getEnemyByIndex(i);
            final int index = i; // Capture index for lambda use

            EnemyButton frame = (EnemyButton) new EnemyButton(enemy)
                    .setAction(() -> {
                        System.out.println("Enemy " + enemy.getName() + " clicked");
                        enemyManager.setAutoSelectEnemy(false);
                        enemyManager.setCurrentEnemy(index);
                    });
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;
        Enemy enemy = enemyManager.getCurrentEnemy();
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
