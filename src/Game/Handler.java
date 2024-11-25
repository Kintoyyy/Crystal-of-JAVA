package Game;

import Entities.Characters.CharacterManager;
import Inputs.InputMouseListener;
import Inputs.InputKeyboardListener;
import Utils.DebugMode;
import Battle.BattleManager;
import Views.ViewManager;
import Worlds.WorldManager;

public class Handler {
    private static Handler instance; // Singleton instance
    private final Game game;
    private DebugMode debugMode;
    private ViewManager viewManager;
    private CharacterManager characterManager;
    private WorldManager worldManager;
    private BattleManager battleManager;

    // Private constructor for Singleton
    Handler(Game game) {
        this.game = game;
    }

    // Public method to initialize the Singleton with a Game instance
    public static Handler getInstance(Game game) {
        if (instance == null) {
            instance = new Handler(game);
        }
        return instance;
    }

    // General access method after initialization
    public static Handler getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Handler has not been initialized yet!");
        }
        return instance;
    }

    // Initialization of managers
    public void initManagers(ViewManager viewManager, CharacterManager characterManager, WorldManager worldManager, BattleManager battleManager) {
        this.viewManager = viewManager;
        this.characterManager = characterManager;
        this.worldManager = worldManager;
        this.battleManager = battleManager;
    }

    // Public getter methods
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

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public BattleManager getBattleManager() {
        return battleManager;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public void setCharacterManager(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    public void setBattleManager(BattleManager battleManager) {
        this.battleManager = battleManager;
    }
}
