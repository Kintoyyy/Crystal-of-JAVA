package Components.Menu;

import Components.Component;
import Components.Frame.CharacterFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Characters.Character;

public class CharacterMenu extends Menu {
    private ArrayList<CharacterFrame> characterFrames = new ArrayList<>(4);
    private static final int FRAME_SPACING = 4; // Spacing between frames

    public CharacterMenu(ArrayList<Character> characters) {
        super(0, 0, 200, 60);

        int xOffset = 0; // Initial x position for the first frame
        for (Character character : characters) {
            CharacterFrame frame = new CharacterFrame(character);
            frame.setLocation(xOffset, (int) this.y); // Set position based on xOffset and current y position of menu
            characterFrames.add(frame);

            // Update xOffset for the next frame, considering width and spacing
            xOffset += frame.getWidth() + FRAME_SPACING;
        }
    }

    @Override
    public void tick() {
        for (CharacterFrame frame : characterFrames) {
            frame.tick();
        }
    }

    // TODO: SHOULD BE USING THE CHILD COMPONENTS INSTEAD OF THE FRAMES
    public void onMouseMove(MouseEvent e) {
        for (CharacterFrame frame : characterFrames) {
            frame.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (CharacterFrame frame : characterFrames) {
            frame.onMouseRelease(e);
        }
    }

    @Override
    public void render(Graphics g) {
        for (CharacterFrame frame : characterFrames) {
            frame.render(g);
        }
    }

    @Override
    public void onClick() {
        for (CharacterFrame frame : characterFrames) {
            frame.onClick();
        }
    }
}
