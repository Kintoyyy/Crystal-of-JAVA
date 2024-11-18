package Game;

import Inputs.InputMouseListener;
import Inputs.InputKeyboardListener;
import Utils.DebugMode;
import Views.ViewManager;
import Worlds.World;
import Worlds.WorldManager;

public class Handler {
    private final Game game;
    private DebugMode debugMode;
    private ViewManager viewManager;
    private WorldManager worldManager;
    private GameState gameState;

    public Handler(Game game) {
        this.game = game;
    }

    public InputKeyboardListener getKeyManager() {
        return game.getKeyManager();
    }

    public InputMouseListener getInputMouseListener() {
        return game.getInputMouseListener();
    }

    public DebugMode getDebugMode() {
        return debugMode;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

}
