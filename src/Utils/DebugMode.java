package Utils;

import Game.Handler;

public class DebugMode {
    Handler handler;
    private static boolean isDebug = false;
    private static int layerIndex = 0;

    public DebugMode(Handler handler) {
        this.handler = handler;
    }

    public static boolean debugMode() {
        return isDebug;
    }

    public static void SetDebugMode(boolean isDebug) {
        DebugMode.isDebug = isDebug;
    }

    public static int getRenderedLayerIndex() {
        return layerIndex;
    }

    public static void setRenderedLayerIndex(int layerIndex) {
        DebugMode.layerIndex = layerIndex;
    }
}
