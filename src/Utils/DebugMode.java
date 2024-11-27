package Utils;

import Game.Handler;

public class DebugMode {
    Handler handler;
    private static boolean isDebug = false;
    private static int layerIndex = 0;
    private static boolean showObjects = true;
    private static boolean showComponents = false;

    public DebugMode(Handler handler) {
        handler.setDebugMode(this);
//        this.handler = Handler.getInstance();

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


    public static boolean isShowObjects() {
        return showObjects;
    }

    public static void toggleShowObject() {
        showObjects = !showObjects;
    }

    public static boolean isShowComponents() {
        return showComponents;
    }
}
