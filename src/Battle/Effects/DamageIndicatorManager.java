package Battle.Effects;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DamageIndicatorManager {
    private static final Color DAMAGE_COLOR = new Color(255, 50, 50);
    private static final Color DODGE_COLOR = new Color(50, 150, 255);
    private static final long INDICATOR_DURATION = 1000; // 1 second
    private static final float OFFSET_Y = 40f; // Vertical offset above entity

    private final List<DamageIndicator> indicators = new ArrayList<>();

    public void addDamageIndicator(double damage, float x, float y) {
        String text = String.format("%.0f", damage);
        indicators.add(new DamageIndicator(text, x, y, DAMAGE_COLOR, INDICATOR_DURATION));
    }

    public void addDodgeIndicator(float x, float y) {
        indicators.add(new DamageIndicator("DODGE!", x, y - OFFSET_Y, DODGE_COLOR, INDICATOR_DURATION));
    }

    public void update() {
        Iterator<DamageIndicator> it = indicators.iterator();
        while (it.hasNext()) {
            DamageIndicator indicator = it.next();
            indicator.update();
            if (indicator.isDone()) {
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (DamageIndicator indicator : indicators) {
            indicator.render(g);
        }
    }
}
