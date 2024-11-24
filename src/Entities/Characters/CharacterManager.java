package Entities.Characters;

import Entities.Characters.Skills.Skill;
import Game.Handler;

import java.util.ArrayList;

/**
 * Manages the characters in the game.
 * <p>
 * The {@code CharacterManager} is responsible for keeping track of all the characters,
 * switching between the current player, and updating the game state for each character.
 * This includes managing health, mana, and skill turns.
 * </p>
 */
public class CharacterManager {
    private static final ArrayList<Character> characters = new ArrayList<>(4);  // List of characters in the game
    private int currentCharacterIndex = 0;  // Index of the currently active character

    /**
     * Initializes the {@code CharacterManager} with default characters.
     */
    public CharacterManager(Handler handler) {
        handler.setCharacterManager(this);
        addCharacter(new Kent());
        addCharacter(new Cedi());
        addCharacter(new Nathan());
        addCharacter(new Zeith());
    }

    /**
     * Sets the player by the provided index.
     * <p>
     * This method updates the {@code currentCharacterIndex} to the specified index.
     * If the index is invalid (out of bounds), an error message is printed.
     * </p>
     *
     * @param index the index of the character to set as the player
     */
    public void setPlayerByIndex(int index) {
        if (index < 0 || index >= characters.size()) {
            System.out.println("Invalid index");
            return;
        }
        this.currentCharacterIndex = index;
    }

    /**
     * Adds a character to the list of characters.
     *
     * @param character the character to add
     */
    private static void addCharacter(Character character) {
        characters.add(character);
    }

    /**
     * Retrieves the list of all characters.
     *
     * @return the list of characters
     */
    public ArrayList<Character> getCharacters() {
        return characters;
    }

    /**
     * Retrieves the current player (active character).
     *
     * @return the current player (active character)
     */
    public Character getPlayer() {
        return characters.get(currentCharacterIndex);
    }

    /**
     * Checks if the current player is alive.
     * <p>
     * This method checks the health of the current character and determines if they are alive.
     * </p>
     *
     * @return {@code true} if the player is alive, {@code false} otherwise
     */
    public boolean isPlayerAlive() {
        return !characters.get(currentCharacterIndex).getHealth().isDead();
    }

    /**
     * Updates the game state for each character.
     * <p>
     * This method regenerates health and mana for each character on every tick.
     * </p>
     */
    public void tick() {
        for (Character character : characters) {
            character.regenHealth();
            character.regenMana();
        }
    }

    /**
     * Removes a character from the list.
     *
     * @param character the character to remove
     */
    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    /**
     * Removes a character from the list by their index.
     * <p>
     * This method removes the character at the specified index. If the index is invalid,
     * an error message is printed.
     * </p>
     *
     * @param index the index of the character to remove
     */
    public void removeCharacter(int index) {
        if (index < 0 || index >= characters.size()) {
            System.out.println("Invalid index");
            return;
        }
        characters.remove(index);
    }

    /**
     * Retrieves the number of characters.
     *
     * @return the number of characters
     */
    public int getSize() {
        return characters.size();
    }

    /**
     * Retrieves a character by their index.
     *
     * @param i the index of the character to retrieve
     * @return the character at the specified index
     */
    public Character getCharacterByIndex(int i) {
        return characters.get(i);
    }

    /**
     * Sets the current player by their index.
     *
     * @param index the index of the character to set as the player
     */
    public void setPlayer(int index) {
        this.currentCharacterIndex = index;
    }

    /**
     * Updates the turns of all characters' skills.
     * <p>
     * This method updates the turn counter for each skill of every character.
     * </p>
     */
    public void updateTurns() {
        for (Character character : characters) {
            for (Skill skill : character.getSkills()) {
                skill.updateTurns();
            }
        }
    }
}
