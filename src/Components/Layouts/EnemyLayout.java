package Components.Layouts;

import Battle.BattleManager;
import Components.Component;
import Components.Button.EnemyButton;
import Entities.Enemies.*;

import java.awt.*;

public class EnemyLayout extends Layout {
    private final BattleManager battleManager;

    public EnemyLayout(BattleManager battleManager) {
        super();
        this.battleManager = battleManager;
        initEnemyFrames();
    }

    private void initEnemyFrames() {
        childComponents.clear();
        for (int i = 0; i < battleManager.getEnemies().size(); i++) {
            Enemy enemy = battleManager.getEnemies().get(i);
            final int index = i; // Capture index for lambda use

            EnemyButton frame = (EnemyButton) new EnemyButton(enemy)
                    .setAction(() -> {
                        System.out.println("Enemy " + enemy.getName() + " clicked");
                        // Uncomment if needed:
                        // currentEnemyManager.setAutoSelectEnemy(false);
                        battleManager.setCurrentEnemy(index);
                    });
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
        if (!battleManager.isDataLoaded()) {
            System.out.println("EnemyLayout: Data not loaded");
            initEnemyFrames();
        }
        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;
        Enemy enemy = (battleManager != null)
                ? battleManager.getCurrentEnemy()
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
