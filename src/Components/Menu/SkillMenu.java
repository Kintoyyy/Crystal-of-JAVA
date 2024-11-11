package Components.Menu;

import Characters.Character;
import Components.Component;
import Components.Frame.CharacterFrame;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class SkillMenu extends Menu {
    private Character currentCharacter;
    private final ArrayList<Character> characters;
    private final Handler handler;

    public SkillMenu(Handler handler) {
        super();
        this.handler = handler;
        this.characters = handler.getGameState().getCharacters();

        // Create frames for each character
        initCharacterFrames();
        ensureMinimumFrames(4);  // Ensures we always have at least 4 frames
    }

    private void initCharacterFrames() {
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);

            // Define a final variable to capture the index for lambda usage
            final int index = i;

            CharacterFrame frame = (CharacterFrame) new CharacterFrame(character)
                    .setAction(() -> {
                        currentCharacter = character;
                        System.out.println("Selected: " + currentCharacter.getName());
                        handler.getGameState().setPlayerByIndex(index);
                    });
            childComponents.add(frame);
        }
    }

    private void ensureMinimumFrames(int minFrames) {
        while (childComponents.size() < minFrames) {
            childComponents.add(new CharacterFrame(null));
        }
    }

    @Override
    public void tick() {
        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        Character player = handler.getGameState().getPlayer();
        int xOffset = (int) this.x;

        for (Component component : childComponents) {
            if (component instanceof CharacterFrame frame) {
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
