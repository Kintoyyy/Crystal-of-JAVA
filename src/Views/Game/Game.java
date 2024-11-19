package Views.Game;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Card.CharacterCard;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import Views.enums.Views;
import Worlds.WorldManager;

import java.awt.*;

public class Game extends View {
    private final WorldManager worldManager;
    public Game(ViewManager viewManager) {
        super(viewManager);
        this.worldManager = new WorldManager(handler);

        initComponent();
    }

    @Override
    public void tick() {
        components.tick();
        worldManager.tick();
    }

    @Override
    public void render(Graphics g) {
        worldManager.render(g);
        components.render(g);
    }

    public void initComponent() {
        components.init(
                new PauseButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(Views.PAUSE);
                            }
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
