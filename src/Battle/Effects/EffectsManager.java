package Battle.Effects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EffectsManager {
    private final List<Effect> activeEffects = new ArrayList<>();

    public void addEffect(Effect effect) {
        activeEffects.add(effect);
    }

    public void processEffects() {
        Iterator<Effect> iterator = activeEffects.iterator();

        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            effect.apply();

            effect.reduceTurn();
            if (effect.isExpired()) {
                effect.remove();
                iterator.remove();
            }
        }
    }
}
