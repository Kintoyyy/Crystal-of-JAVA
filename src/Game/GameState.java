package Game;

import Battle.BattleManager;
import Entities.Characters.*;
import Views.ViewManager;
import Worlds.WorldManager;
import Worlds.Worlds;

import java.awt.*;

public class GameState {
    private final CharacterManager characters;
    private final WorldManager worldManager;
    private final BattleManager battleManager;
    private final Handler handler;

    public GameState(Handler handler) {
        handler.setGameState(this);

        this.handler = handler;

        this.characters = new CharacterManager();

        this.battleManager = new BattleManager(handler);

        this.worldManager = new WorldManager(this, handler);
    }

    public void tick() {
        if (handler.getViewManager().isInGame()) {
            characters.tick();
        }
        worldManager.tick();
    }

    public CharacterManager getCharacterManger() {
        return this.characters;
    }

    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public BattleManager getBattleManager() {
        return battleManager;
    }

    public ViewManager getViewManager() {
        return handler.getViewManager();
    }

    public Handler getHandler() {
        return handler;
    }


    public void render(Graphics g) {
        worldManager.render(g);
    }
}
