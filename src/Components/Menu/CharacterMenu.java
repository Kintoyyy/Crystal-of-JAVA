package Components.Menu;

import Components.Component;
import Components.Frame.CharacterFrame;

import java.awt.*;
import java.util.ArrayList;
import Characters.Character;
import Game.CallBackAction;
import Game.Handler;

public class CharacterMenu extends Menu {
    private Character currentCharacter;
    private ArrayList<Character> characters;
    private final Handler handler;

    public CharacterMenu(Handler handler) {
        super(0, 0, 0, 0);
        this.handler = handler;
        characters = handler.getGameState().getCharacters();

        int characterIndex = 0;

        // Create frames for existing characters without setting locations
        for (Character character : characters) {
            int finalIndex = characterIndex;

            CharacterFrame frame = (CharacterFrame) new CharacterFrame(character).setAction(new CallBackAction() {
                @Override
                public void onClick() {
                    currentCharacter = character;
                    System.out.println("Selected: " + currentCharacter.getName());

                    handler.getGameState().setPlayerByIndex(finalIndex);
                }
            });

            childComponents.add(frame);
            characterIndex++;
        }

        // Add empty frames if fewer than 4 characters
        while (childComponents.size() < 4) {
            CharacterFrame emptyFrame = (CharacterFrame) new CharacterFrame(null);
            childComponents.add(emptyFrame);
        }
    }

    @Override
    public void tick() {
        for (Component component : childComponents) {
            component.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        Character player = handler.getGameState().getPlayer();
        int xOffset = 0;

        for (Component component : childComponents) {
            if (component instanceof CharacterFrame frame) {

                // Dynamically set location during render
                frame.setLocation(xOffset, (int) this.y);

                // Activate frame if its character matches the current player
                frame.isActive(player != null && player.equals(frame.getPlayer()));

                // Increment xOffset for the next frame position
                xOffset += frame.getWidth();
            }
            // Render each component
            component.render(g);
        }
    }

    @Override
    public void onClick() {
        for (Component component : childComponents) {
            component.onClick();
        }
    }
}
