package Components.Layouts;

import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Components.Component;
import Components.Button.CharacterButton;
import Views.Battle.BattleManager;

import java.awt.*;

public class CharacterLayout extends Layout {
    private final CharacterManager characters;

    public CharacterLayout(BattleManager battleManager) {
        super();
        characters = battleManager.getCharacterManager();
        initCharacterFrames();
    }

    private void initCharacterFrames() {
        for (int i = 0; i < characters.getCharacters().size(); i++) {
            Character character = characters.getCharacters().get(i);
            // Define a final variable to capture the index for lambda usage
            final int index = i;
            CharacterButton frame = (CharacterButton) new CharacterButton(character)
                    .setAction(() -> {
                        characters.setPlayerByIndex(index);
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
        Character player = characters.getPlayer();
        int xOffset = (int) this.x;

        for (Component component : childComponents) {
            if (component instanceof CharacterButton frame) {
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
