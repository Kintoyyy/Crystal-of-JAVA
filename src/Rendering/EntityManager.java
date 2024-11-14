package Rendering;

import Entities.Characters.CharacterManager;
import Entities.Entity;
import Game.Handler;

import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {
    private Handler handler;
    private CharacterManager characterManager;
    private final ArrayList<Entity> entities = new ArrayList<>();

    public EntityManager(Handler handler) {
        this.handler = handler;
//        entities.add(characterManager.getPlayer());
    }

    public void tick() {
//        for (Entity entity : entities) {
//            entity.tick();
//        }
    }

    public void render() {
//        entities.sort(Comparator.comparingInt(Entity::getRenderOrder));
//        for (Entity entity : entities) {
//            entity.render();
//        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

}
