package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A keyboard listener for dynamically tracking key states with fluent API.
 */
public class InputKeyboardListener implements KeyListener {

    // World to dynamically track the state of keys
    private final Map<Integer, Boolean> keyStates;

    // Temporary variable to store the key code being queried
    private int queriedKeyCode;

    public InputKeyboardListener() {
        keyStates = new HashMap<>();
    }

    /**
     * Updates the state of a key in the map when it's pressed.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyStates.put(e.getKeyCode(), true);
    }

    /**
     * Updates the state of a key in the map when it's released.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keyStates.put(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Initiates a query for the state of a specific key by its character.
     *
     * @param keyChar the key character to query (e.g., 'w' or 'W')
     * @return this instance for chaining
     */
    public InputKeyboardListener isKeyPressed(String keyChar) {
        char key = keyChar.length() == 1 ? keyChar.charAt(0) : '\0';
        queriedKeyCode = KeyEvent.getExtendedKeyCodeForChar(key);
        return this;
    }

    /**
     * Initiates a query for the state of a specific key by its key code.
     *
     * @param keyCode the KeyEvent key code (e.g., KeyEvent.VK_UP)
     * @return this instance for chaining
     */
    public InputKeyboardListener isKeyPressed(int keyCode) {
        queriedKeyCode = keyCode;
        return this;
    }

    /**
     * Checks the state of the queried key, ignoring case sensitivity if applicable.
     *
     * @return true if the key is pressed, false otherwise
     */
    public boolean ignoreCaps() {
        if (Character.isLetter(queriedKeyCode)) {
            int lowerKeyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toLowerCase(queriedKeyCode));
            int upperKeyCode = KeyEvent.getExtendedKeyCodeForChar(Character.toUpperCase(queriedKeyCode));
            return keyStates.getOrDefault(lowerKeyCode, false) || keyStates.getOrDefault(upperKeyCode, false);
        }
        return keyStates.getOrDefault(queriedKeyCode, false);
    }

    /**
     * Directly checks the state of the queried key without any additional logic.
     *
     * @return true if the key is pressed, false otherwise
     */
    public boolean exactMatch() {
        return keyStates.getOrDefault(queriedKeyCode, false);
    }
}
