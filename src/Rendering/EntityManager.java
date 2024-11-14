package Rendering;

import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Entities.Enemies.Enemy;
import Entities.Entity;
import Game.Handler;

import java.util.ArrayList;

public class EntityManager {
    private Handler handler;
    private CharacterManager characterManager;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler) {
        this.handler = handler;
        this.entities = new ArrayList<>();
    }
}
