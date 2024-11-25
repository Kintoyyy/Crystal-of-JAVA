package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A keyboard listener class for dynamically tracking the state of keys.
 * It supports querying key states using a fluent API for flexibility.
 */
public class InputKeyboardListener implements KeyListener {

    // Parser to track the pressed/released state of keys using their key codes.
    private final Map<Integer, Boolean> keyStates;

    // Temporary variable to store the key code being queried.
    private int queriedKeyCode;

    /**
     * Constructs an InputKeyboardListener with an internal state map for tracking key states.
     */
    public InputKeyboardListener() {
        keyStates = new HashMap<>();
    }

    /**
     * Records the state of a key as pressed.
     *
     * @param e the KeyEvent triggered when a key is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyStates.put(e.getKeyCode(), true);
    }

    /**
     * Records the state of a key as released.
     *
     * @param e the KeyEvent triggered when a key is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keyStates.put(e.getKeyCode(), false);
    }

    /**
     * Currently unused method for handling key typed events.
     *
     * @param e the KeyEvent triggered when a key is typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Begins a query for the state of a specific key using its character representation.
     *
     * @param keyChar the character of the key to query (e.g., 'a', 'W').
     * @return this InputKeyboardListener instance for chaining.
     */
    public InputKeyboardListener isKeyPressed(String keyChar) {
        char key = keyChar.length() == 1 ? keyChar.charAt(0) : '\0';
        queriedKeyCode = KeyEvent.getExtendedKeyCodeForChar(key);
        return this;
    }

    /**
     * Begins a query for the state of a specific key using its key code.
     *
     * @param keyCode the key code of the key to query (e.g., KeyEvent.VK_UP).
     * @return this InputKeyboardListener instance for chaining.
     */
    public InputKeyboardListener isKeyPressed(int keyCode) {
        queriedKeyCode = keyCode;
        return this;
    }

    /**
     * Checks if the queried key is pressed, ignoring case sensitivity for letters.
     *
     * @return true if the key is pressed, false otherwise.
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
     * Checks if the queried key is pressed without any additional logic.
     *
     * @return true if the key is pressed, false otherwise.
     */
    public boolean exactMatch() {
        return keyStates.getOrDefault(queriedKeyCode, false);
    }
}
