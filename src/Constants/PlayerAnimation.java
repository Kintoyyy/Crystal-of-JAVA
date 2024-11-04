package Constants;

public final class PlayerAnimation {
    // ANIMATION ROW INDEX
    public static final int ANIMATION_RUN_DOWN = 3;
    public static final int ANIMATION_RUN_UP = 5;
    public static final int ANIMATION_RUN_LEFT_RIGHT = 4;

    public static final int ANIMATION_IDLE_DOWN = 0;
    public static final int ANIMATION_IDLE_UP = 2;
    public static final int ANIMATION_IDLE_LEFT_RIGHT = 1;

    public static final int ANIMATION_DEATH_DOWN = 7;
    public static final int ANIMATION_DEATH_UP = 9;
    public static final int ANIMATION_DEATH_LEFT_RIGHT = 8;

    public static final int ANIMATION_IDLE_BLINKING_DOWN = 10;
    public static final int ANIMATION_IDLE_BLINKING_LEFT_RIGHT = 11;

    public static final int ANIMATION_IDLE_DANCE_DOWN = 12;

    // ACTIONS
    public static final int ATTACK = 0;
    public static final int IDLE = 1;
    public static final int RUNNING = 2;

    // MOVEMENTS
    public static final String DOWN = "down";
    public static final String UP = "up";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    public static int getSpriteAmount(int player_action) {
        return switch (player_action) {
            case IDLE, RUNNING -> 6;
            case ATTACK -> 4;
            default -> 1;
        };
    }

    public static int getFrameSet(int action, String direction) {
        return switch (action) {
            case ATTACK -> switch (direction) {
                case UP, LEFT, RIGHT -> 8;
                case DOWN -> 6;
                default -> 0;
            };
            case IDLE -> switch (direction) {
                case UP -> 2;
                case LEFT, RIGHT -> 1;
                default -> 0;
            };
            case RUNNING -> switch (direction) {
                case UP -> 5;
                case DOWN -> 3;
                case LEFT, RIGHT -> 4;
                default -> 0;
            };
            default -> action;
        };
    }
}
