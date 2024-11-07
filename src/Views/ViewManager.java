package Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import Components.ComponentManager;
import Game.Handler;
import enums.ViewEnums;

public class ViewManager {
    private final HashMap<ViewEnums, View> views = new HashMap<>();
    private final ArrayList<View> layers = new ArrayList<>();
    private final Handler handler;

    public ViewManager(Handler handler) {
        this.handler = handler;
        this.handler.setViewManager(this);
        // set the views
        views.put(ViewEnums.BATTLE, new BattleView(this));
        views.put(ViewEnums.GAME, new GameView(this));
        views.put(ViewEnums.MAIN_MENU, new MenuView(this));
        views.put(ViewEnums.TEST, new TestView(this));

        layers.add(views.get(ViewEnums.TEST));
    }

    public void setComponentManager(ComponentManager componentManager) {

    }

    public void setView(ViewEnums viewEnum) {

        if (views.get(viewEnum) == null){

            return;
        }
        System.out.println(viewEnum);
//        if (!views.get(viewEnum).isOverlay){
            layers.clear();
            layers.add(views.get(viewEnum));
//            return;
//        }
//
//        layers.add(views.get(viewEnum));
    }

    public boolean hasLayers(){
        return !layers.isEmpty();
    }

    public Handler getHandler(){
        return handler;
    }

    public void tick(){
        if (layers.isEmpty()){
            return;
        }
        layers.forEach(View::tick);
    }

    public void render(Graphics g) {
//        layers.forEach(view -> view.render(g));

        for (int i = 0; i < layers.size(); i++) {
//            System.out.println(layers.size());
            layers.get(i).render(g);
            if (i != layers.size() - 1) {
                overlay(g);
            }
        }
    }

    private void overlay(Graphics g){
        //  TODO: Change to blur image next time
        Color gray = new Color(200, 200, 200, 128); // RGBA (Red, Green, Blue, Alpha)
        
        g.setColor(gray);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
    }


}
