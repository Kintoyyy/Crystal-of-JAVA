package Components.Menu;

import Characters.CharacterManager;
import Components.Component;
import Components.Button.CharacterSelector;
import Characters.Character;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class CharacterMenu extends Menu {
    private final CharacterManager characterManager;

    public CharacterMenu(Handler handler) {
        super();
        characterManager = handler.getGameState().getCharacterManger();
        initCharacterFrames();
        ensureMinimumFrames(4);
    }

    private void initCharacterFrames() {
        for (int i = 0; i < characterManager.getSize(); i++) {
            Character character = characterManager.getCharacterByIndex(i);

            final int index = i;

            CharacterSelector frame = (CharacterSelector) new CharacterSelector(character)
                    .setAction(() -> {
                        characterManager.setPlayer(index);
                    });
            childComponents.add(frame);
        }
    }

    private void ensureMinimumFrames(int minFrames) {
        while (childComponents.size() < minFrames) {
            childComponents.add(new CharacterSelector(null));
        }
    }

    @Override
    public void tick() {
        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        Character player = characterManager.getPlayer();
        int xOffset = (int) this.x;

        for (Component component : childComponents) {
            if (component instanceof CharacterSelector frame) {
                frame.setLocation(xOffset, (int) this.y);
                frame.isActive(player != null && player.equals(frame.getPlayer()));
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
