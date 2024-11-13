
package Characters;

import java.util.ArrayList;

public class CharacterManager {
    private ArrayList<Character> characters;
    private int currentCharacterIndex = 0;

    public CharacterManager() {
        this.characters = new ArrayList<>(4);

        this.characters.add(new Kent());
        this.characters.add(new Cedi());
        this.characters.add(new Nathan());
        this.characters.add(new Zeith());
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public Character getPlayer() {
        return characters.get(currentCharacterIndex);
    }

    public void setPlayerByIndex(int index) {
        if (index < 0 || index >= characters.size()) {
            System.out.println("Invalid index");
            return;
        }
        this.currentCharacterIndex = index;
    }

    public int getCurrentCharacterIndex() {
        return this.currentCharacterIndex;
    }

    public void tick() {
        for (Character character : characters) {
            character.regenHealth();
            character.regenMana();
        }
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public void removeCharacter(int index) {
        if (index < 0 || index >= characters.size()) {
            System.out.println("Invalid index");
            return;
        }
        characters.remove(index);
    }

    public int getSize() {
        return characters.size();
    }

    public Character getCharacterByIndex(int i) {
        return characters.get(i);
    }

    public void setPlayer(int index) {
        this.currentCharacterIndex = index;
    }
}
