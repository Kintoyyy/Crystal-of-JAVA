package Components.Layouts;

import Components.ComponentEventListener;
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
                    .setEventListener(new ComponentEventListener() {
                        @Override
                        public void onComponentClick(MouseEvent event) {
                            characters.setPlayerByIndex(index);
                        }

                        @Override
                        public void onMouseEnter(MouseEvent event) {

                        }

                        @Override
                        public void onMouseExit(MouseEvent event) {

                        }
                    })
                    .scale(scale);
            children.add(frame);
        }
    }

    @Override
    public void tick() {
        for (int i = 0; i < characters.getCharacters().size(); i++) {
            if(handler == null) return;
            if (handler.getKeyManager().isKeyPressed(String.valueOf(i + 1)).ignoreCaps()) {
                characters.setPlayer(i);
                // need to rerender the character layout
                break; // Only one character can be selected per tick
            }
        }
        children.forEach(UIComponent::tick);
    }

    @Override
    public void render(Graphics g) {
        Character player = handler.getCharacterManager().getPlayer();
        int xOffset = (int) this.x;

        for (UIComponent UIComponent : children) {
            if (UIComponent instanceof CharacterButton frame) {
                frame.setLocation(xOffset, (int) this.y);
                xOffset += frame.getWidth();
                UIComponent.render(g);
            }
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        children.forEach(component -> component.onClick(e));
    }
}
