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
    private ArrayList<String> dialogs = new ArrayList<String>();


    public Game() {
        this.map = handler.getWorldManager();

        initComponent();
    }

    @Override
    public void tick() {
        components.tick();
        if (viewManager.isViewActive(Views.BATTLE)) {
            return;
        }
        map.tick();
    }

    @Override
    public void setData(Object data) {
        if (data instanceof ArrayList) {
            this.dialogs = (ArrayList<String>) data;
            viewManager.setView(Views.DIALOG);
        }
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
