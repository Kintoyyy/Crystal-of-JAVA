package Components.Layouts;

import Characters.Character;
import Components.Component;
import Components.Frame.CharacterFrame;
import Components.Frame.CharacterMenuFrame;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class CharacterLayout extends Layout{
    private Characters.Character currentCharacter;
    private final ArrayList<Character> characters;
    private final Handler handler;

    public CharacterLayout(Handler handler) {
        super();
        this.handler = handler;
        this.characters = handler.getGameState().getCharacters();

        initCharacterFrames();
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
