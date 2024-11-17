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

import java.awt.*;

public class Game extends View {
    private final Render render;

    public Game(ViewManager viewManager) {
        super(viewManager);

        Map world = new Map("res/Maps/world_1.tmx");



        CharacterManager characterManager = handler.getGameState().getCharacterManger();

        Movement movement = new Movement(handler, world, characterManager);

        this.render = new Render(world, movement);

        initComponent();
    }

    @Override
    public void tick() {
        components.tick();
        render.tick();
    }

    @Override
    public void render(Graphics g) {
        render.render(g);
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
