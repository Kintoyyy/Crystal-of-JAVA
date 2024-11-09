package Game;

import Characters.Character;
import Inputs.InputMouseListener;
import Inputs.InputKeyboardManager;
import Utils.DebugMode;
import Views.ViewManager;
import World.World;

public class Handler {
    private Game game;
    private World world;
    private DebugMode debugMode;
    private Character character;
    private ViewManager viewManager;
    private GameState gameState;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public InputKeyboardManager getKeymanager() {

        return game.getKeyManager();
    }

    public InputMouseListener getInputMouseListener() {
        return game.getInputMouseListener();
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

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public DebugMode getDebugMode() {
        return debugMode;
    }

    // TODO: Character should be inside the game class();
    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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
}
