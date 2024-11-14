
package Entities.Characters;

import Game.Handler;
import Skills.Skill;

import java.util.ArrayList;

public class CharacterManager {
    private static ArrayList<Character> characters;
    private int currentCharacterIndex = 0;
    private static Handler handler;

    public CharacterManager(Handler handler) {
        characters = new ArrayList<>(4);
        CharacterManager.handler = handler;
        addCharacter(new Kent());
        addCharacter(new Cedi());
        addCharacter(new Nathan());
        addCharacter(new Zeith());
    }

    private static void addCharacter(Character character) {
//        character.getMovement().setCamera(handler.getGameCamera());
//        character.getMovement().setKeyManager(handler.getKeymanager());
        character.getMovement().setControllers(handler.getGameCamera(), handler.getKeymanager());
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
            // temporary fix
            character.getMovement().setControllers(handler.getGameCamera(), handler.getKeymanager());
//            System.out.println("Character: " + character.getMovement().getCamera() + " " + character.getMovement().getKeyManager());
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
