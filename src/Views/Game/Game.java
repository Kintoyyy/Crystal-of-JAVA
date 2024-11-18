package Views.Game;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Card.CharacterCard;
import Entities.Characters.CharacterManager;
import Map.Movement.Movement;
import Map.Map;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import Map.Render;
import Views.enums.Views;
import Worlds.Enums.WorldNames;
import Worlds.Forest.Forest;
import Worlds.World;
import Worlds.WorldManager;

import java.awt.*;
import java.util.HashMap;

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

                new Button("battle")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(Views.BATTLE);
                            }
                        })
                        .setLocation(680, 20),

                new Components.Menu.CharacterMenu(handler)
                        .setLocation(350, 700)
                        .scale(6),

                new CharacterCard(handler)
                        .setLocation(12, 12)
                        .scale(6)
        );
    }
}
