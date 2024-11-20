package Map.Tile;

/**
 * Represents a Wang tile with boolean states for eight directions:
 * Top, Top-right, Right, Bottom-right, Bottom, Bottom-left, Left, and Top-left.
 *
 * The states are parsed from a comma-separated string of 1s and 0s,
 * where each index corresponds to a specific direction.
 */
public class WangTile {
    // Array to store boolean states for the eight directions
    private final boolean[] wangStates = new boolean[8];

    /**
     * Constructs a WangTile instance by parsing the specified wangId.
     *
     * @param wangId a comma-separated string of 1s and 0s, representing the
     *               boolean states of the directions in the following order:
     *               Top, Top-right, Right, Bottom-right, Bottom, Bottom-left, Left, Top-left.
     */
    public WangTile(String wangId) {
        parseWangTile(wangId);
    }

    /**
     * Parses the wangId string and initializes the boolean states for the directions.
     *
     * @param wangId a comma-separated string of 1s and 0s.
     */
    private void parseWangTile(String wangId) {
        String[] wangIds = wangId.split(",");
        for (int i = 0; i < wangIds.length && i < 8; i++) {
            wangStates[i] = wangIds[i].equals("1");
        }
    }

    /**
     * @return true if the top direction is enabled, false otherwise.
     */
    public boolean isTop() {
        return wangStates[0];
    }

    /**
     * @return true if the top-right direction is enabled, false otherwise.
     */
    public boolean isTopRight() {
        return wangStates[1];
    }

    /**
     * @return true if the right direction is enabled, false otherwise.
     */
    public boolean isRight() {
        return wangStates[2];
    }

    /**
     * @return true if the bottom-right direction is enabled, false otherwise.
     */
    public boolean isBottomRight() {
        return wangStates[3];
    }

    /**
     * @return true if the bottom direction is enabled, false otherwise.
     */
    public boolean isBottom() {
        return wangStates[4];
    }

    /**
     * @return true if the bottom-left direction is enabled, false otherwise.
     */
    public boolean isBottomLeft() {
        return wangStates[5];
    }

    /**
     * @return true if the left direction is enabled, false otherwise.
     */
    public boolean isLeft() {
        return wangStates[6];
    }

    /**
     * @return true if the top-left direction is enabled, false otherwise.
     */
    public boolean isTopLeft() {
        return wangStates[7];
    }
}
