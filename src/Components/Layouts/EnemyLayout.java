package Components.Layouts;

import Battle.BattleManager;
import Components.UIComponent;
import Components.Button.EnemyButton;
import Entities.Enemies.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EnemyLayout extends Layout {
    private final BattleManager battleManager;

    public EnemyLayout(BattleManager battleManager) {
        super();
        this.battleManager = battleManager;
        initEnemyFrames();
    }

    private void initEnemyFrames() {
        children.clear();
        for (int i = 0; i < battleManager.getEnemies().size(); i++) {
            Enemy enemy = battleManager.getEnemies().get(i);
            final int index = i; // Capture index for lambda use

            EnemyButton frame = (EnemyButton) new EnemyButton(enemy)
                    .setRightClickAction(() -> {
                        System.out.println("Enemy " + enemy.getName() + " clicked");
                        // Uncomment if needed:
                        // currentEnemyManager.setAutoSelectEnemy(false);
                        battleManager.setCurrentEnemy(index);
                    });
            children.add(frame);
        }
        battleManager.setDataLoaded(true);
    }

    @Override
    public void tick() {
        if (!battleManager.isDataLoaded()) {
            System.out.println("Loading data: Data not loaded");
            initEnemyFrames();
        }

        children.forEach(UIComponent::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;
        Enemy enemy = (battleManager != null)
                ? battleManager.getCurrentEnemy()
                : null;


        for (UIComponent UIComponent : children) {
            if (UIComponent instanceof EnemyButton frame) {
                frame.setLocation(xOffset, (int) this.y);
                frame.isActive(enemy != null && enemy.equals(frame.getEnemy()));
                xOffset += frame.getWidth();
            }
            UIComponent.render(g);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        children.forEach(component -> component.onClick(e));
    }
}
