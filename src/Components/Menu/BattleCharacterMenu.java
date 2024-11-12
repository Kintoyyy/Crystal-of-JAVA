package Components.Menu;

import Characters.Character;
import Components.Component;
import Components.Frame.CharacterMenuFrame;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;

public class BattleCharacterMenu extends Menu {
    private Character currentCharacter;
    private final ArrayList<Character> characters;
    private final Handler handler;

    public BattleCharacterMenu(Handler handler) {
        super();
        this.handler = handler;
        this.characters = handler.getGameState().getCharacters();

        // Create frames for each character
        initCharacterFrames();
    }


//    private int[] framePositions(int size) {
//        int size = characters.size();
//        int cols = 1;
//        int rows = size;
//
//        if (size > 1) {
//            cols = (int) Math.ceil(Math.sqrt(size));
//            rows = (int) Math.ceil((double) size / cols);
//        }
//
//        return new int[]{cols, rows};
//    }


    private void initCharacterFrames() {
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);

            // Define a final variable to capture the index for lambda usage
            final int index = i;

            CharacterMenuFrame frame = (CharacterMenuFrame) new CharacterMenuFrame(character)
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
            if (component instanceof CharacterMenuFrame frame) {
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
