package Components.Layouts;

import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Components.Component;
import Components.Button.CharacterButton;
import Game.Handler;
import Battles.BattleManager;

import java.awt.*;

public class CharacterLayout extends Layout {
    private final CharacterManager characters;
    private final Handler handler;
    public CharacterLayout(BattleManager battleManager) {
        super();
        this.handler = battleManager.getHandler();
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

        for (int i = 0; i < characters.getCharacters().size(); i++) {
            if (handler.getKeyManager().isKeyPressed(String.valueOf(i + 1)).ignoreCaps()) {
                characters.setPlayer(i);
                // need to rerender the character layout
                break; // Only one character can be selected per tick
            }
        }
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
