package Components.Layouts;

import Components.Component;
import Components.Button.EnemyButton;
import Enemies.Enemy;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class EnemyLayout extends Layout {
    private ArrayList<Enemy> enemies;
    private final Handler handler;

    public EnemyLayout(Handler handler) {
        super();
        this.handler = handler;
        this.enemies = new ArrayList<>(handler.getGameState().getEnemies()); // Initialize with a copy

        initEnemyFrames();
    }

    private void initEnemyFrames() {
        childComponents.clear(); // Clear existing components

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            final int index = i; // Capture index for lambda use

            EnemyButton frame = (EnemyButton) new EnemyButton(enemy)
                    .setAction(() -> System.out.println("Enemy " + enemy.getName() + " clicked"));
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
        ArrayList<Enemy> currentEnemies = handler.getGameState().getEnemies();

        // Check if enemies have changed
        if (!enemies.equals(currentEnemies)) {
            enemies = new ArrayList<>(currentEnemies); // Update enemy list
            initEnemyFrames(); // Reinitialize frames if enemies have changed
        }

        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;

        for (Component component : childComponents) {
            if (component instanceof EnemyButton frame) {
                frame.setLocation(xOffset, (int) this.y);
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
