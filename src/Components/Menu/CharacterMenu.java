package Components.Menu;

import Entities.Characters.CharacterManager;
import Components.UIComponent;
import Components.Button.CharacterSelector;
import Entities.Characters.Character;
import Game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CharacterMenu extends Menu {
    private final CharacterManager characters;
    private final Handler handler;

    public CharacterMenu(Handler handler) {
        super();
        this.handler = handler;
        if (handler == null || handler.getCharacterManager() == null) {
            throw new IllegalArgumentException("Handler, GameState, or CharacterManager cannot be null");
        }

        this.characters = handler.getCharacterManager();
        this.children = new ArrayList<UIComponent>();

        initCharacterFrames();
        ensureMinimumFrames(4); // Ensure at least 4 frames
    }

    private void initCharacterFrames() {
        for (int i = 0; i < characters.getSize(); i++) {
            Character character = characters.getCharacterByIndex(i);

            final int index = i;
            CharacterSelector frame = (CharacterSelector) new CharacterSelector(character)
                    .setRightClickAction(() -> characters.setPlayer(index));

            children.add(frame);
        }
    }

    private void ensureMinimumFrames(int minFrames) {
        while (children.size() < minFrames) {
            children.add(new CharacterSelector(null)); // Adds empty frames if necessary
        }
    }

    @Override
    public void tick() {
        // Update all child components
        children.forEach(UIComponent::tick);

        // Handle character switching via keys (1-4)
        for (int i = 0; i < characters.getCharacters().size(); i++) {
            if (handler.getKeyManager().isKeyPressed(String.valueOf(i + 1)).ignoreCaps()) {
                characters.setPlayer(i);
                break; // Only one character can be selected per tick
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Character currentPlayer = characters.getPlayer();
        int xOffset = (int) this.x;

        for (UIComponent UIComponent : children) {
            if (UIComponent instanceof CharacterSelector frame) {
                frame.setLocation(xOffset, (int) this.y);
                frame.isActive(currentPlayer != null && currentPlayer.equals(frame.getPlayer()));
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
