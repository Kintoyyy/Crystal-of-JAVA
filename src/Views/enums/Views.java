package Views.enums;

/**
 * Enum representing the different views (screens or states) in the game.
 * This enum is used to identify the current active view in the game and
 * to switch between different screens such as the main menu, game, battle,
 * pause, settings, and character selection.
 *
 * <p>The available views are:</p>
 * <ul>
 *     <li>{@link #GAME} - Represents the main game screen where the gameplay happens.</li>
 *     <li>{@link #MENU} - Represents the main menu screen, typically shown when the game is started.</li>
 *     <li>{@link #BATTLE} - Represents the battle screen, where the game engages in combat mode.</li>
 *     <li>{@link #PAUSE} - Represents the pause screen, typically shown when the game is paused.</li>
 *     <li>{@link #SETTINGS} - Represents the settings screen, where the selectedPlayer can adjust game settings.</li>
 *     <li>{@link #SELECT_CHARACTER} - Represents the character selection screen before the game starts.</li>
 * </ul>
 *
 * <p>This enum is used in conjunction with the ViewManager to manage transitions
 * between these views, and it helps in organizing the game state.</p>
 */
public enum Views {
    GAME,             // The main game view.
    MENU,             // The menu view, usually the entry point to the game.
    BATTLE,           // The battle view where combat or challenges occur.
    PAUSE,            // The overlay that pauses the game.
    SETTINGS,         // The settings view for adjusting game configurations.
    BATTLE_DIALOG,
    SELECT_CHARACTER  // The character selection view before gameplay begins.
}
