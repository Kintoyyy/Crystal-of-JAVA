package Components.Layouts;

import Components.UIComponent;
import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Components.Button.CharacterButton;
import Game.Handler;
import Battle.BattleManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CharacterLayout extends Layout {
    private final CharacterManager characters;
    public CharacterLayout(BattleManager battleManager) {
        super();
        characters = Handler.getInstance().getCharacterManager();
        initCharacterFrames();
    }

    private void initCharacterFrames() {
        for (int i = 0; i < characters.getCharacters().size(); i++) {
            Character character = characters.getCharacters().get(i);
            // Define a final variable to capture the index for lambda usage
            final int index = i;
            CharacterButton frame = (CharacterButton) new CharacterButton(character)
                    .setRightClickAction(() -> {
                        characters.setPlayerByIndex(index);
                    })
                    .scale(scale);
            children.add(frame);
        }
    }

    @Override
    public void tick() {
        children.forEach(UIComponent::tick);
        for (int i = 0; i < characters.getCharacters().size(); i++) {
            if(handler == null) return;
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

        for (UIComponent UIComponent : children) {
            if (UIComponent instanceof CharacterButton frame) {
                frame.setLocation(xOffset, (int) this.y);
                frame.isActive(player != null && player.equals(frame.getPlayer()));
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
