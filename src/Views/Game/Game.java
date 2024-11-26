package Views.Game;

import Components.Button.PauseButton;
import Components.Card.CharacterCard;
import Map.Map;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.util.ArrayList;

public class Game extends View {
    private final Map map;
    //    private final BattleManager battleManager;
    private ArrayList<String> dialogs = new ArrayList<String>();


    public Game() {
        this.map = handler.getWorldManager();
        // SHOULD BE IN VIEW MANAGER

//        this.map = new Map(handler);
//        battleManager = new BattleManager(handler, map);

        initComponent();
    }

    @Override
    public void tick() {
        components.tick();
        map.tick();
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void render(Graphics g) {
        map.render(g);
        components.render(g);
    }

    public void showInteraction() {

    }

    public void initComponent() {
        components.addComponents(
                new PauseButton()
                        .setRightClickAction(() -> {
                            System.out.println("Pause Button Clicked");
                            viewManager.setView(Views.PAUSE);
                        })
                        .setLocation(900, 20),

                new Components.Menu.CharacterMenu(handler)
                        .setLocation(350, 700)
                        .scale(6),

                new CharacterCard(handler)
                        .setLocation(12, 12)
                        .scale(6)
        );
    }
}
