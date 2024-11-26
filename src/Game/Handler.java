package Game;

import Entities.Characters.CharacterManager;
import Inputs.InputMouseListener;
import Inputs.InputKeyboardListener;
import Map.Map;
import Map.Movement.Movement;
import Utils.DebugMode;
import Battle.BattleManager;
import Views.ViewManager;

public class Handler {
    private static Handler instance; // Singleton instance
    private final Game game;
    private DebugMode debugMode;
    private ViewManager viewManager;
    private CharacterManager characterManager;
    private Map map;
    private BattleManager battleManager;
    private Movement movement;


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

    // Public getter methods
    public InputKeyboardListener getKeyManager() {
        return game.getKeyManager();
    }

    public InputMouseListener getInputMouseListener() {
        return game.getInputMouseListener();
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

    public Map getWorldManager() {
        return map;
    }

    public void setWorldManager(Map map) {
        this.map = map;
    }

    public BattleManager getBattleManager() {
        return battleManager;
    }

    public void setBattleManager(BattleManager battleManager) {
        this.battleManager = battleManager;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public void setCharacterManager(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public void setDebugMode(DebugMode debugMode) {
        this.debugMode = debugMode;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Movement getMovement() {
        return movement;
    }
}
