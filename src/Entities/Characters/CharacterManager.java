
package Entities.Characters;

import Entities.Characters.Movement.Movement;
import Game.Handler;
import Skills.Skill;

import java.awt.*;
import java.util.ArrayList;

public class CharacterManager {
    private static ArrayList<Character> characters = new ArrayList<>(4);
    private int currentCharacterIndex = 0;

    public CharacterManager() {
        addCharacter(new Kent());
        addCharacter(new Cedi());
        addCharacter(new Nathan());
        addCharacter(new Zeith());
    }

    public void setPlayerByIndex(int index) {
        if (index < 0 || index >= characters.size()) {
            System.out.println("Invalid index");
            return;
        }
        this.currentCharacterIndex = index;
    }

    private static void addCharacter(Character character) {
        characters.add(character);
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public Character getPlayer() {
        return characters.get(currentCharacterIndex);
    }

    public boolean isPlayerAlive() {
        return !characters.get(currentCharacterIndex).getHealth().isDead();
    }



    public void tick() {
        for (Character character : characters) {
            character.regenHealth();
            character.regenMana();
        }
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

    public void updateTurns() {
        for (Character character : characters) {
            for (Skill skill : character.getSkills()) {
                skill.updateTurns();
            }
        }
    }
}
