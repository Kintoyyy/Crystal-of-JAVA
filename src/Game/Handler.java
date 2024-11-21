package Game;

import Inputs.InputMouseListener;
import Inputs.InputKeyboardListener;
import Utils.DebugMode;
import Battle.BattleManager;
import Views.ViewManager;
import Worlds.WorldManager;

public class Handler {
    private final Game game;
    private DebugMode debugMode;
    private ViewManager viewManager;
    private WorldManager worldManager;
    private BattleManager battleManager;
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

    public void setDebugMode(DebugMode debugMode) {
        this.debugMode = debugMode;
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

    // need to move this
    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    // need to move this
    public BattleManager getBattleManager() {
        return battleManager;
    }

    public void setBattleManager(BattleManager battleManager) {
        this.battleManager = battleManager;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }
}
