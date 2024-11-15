package Views.Game;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Card.CharacterCard;
import Entities.Characters.CharacterManager;
import Entities.Characters.Movement.Movement;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import World.ParseWorld;
import World.RenderWorld;
import enums.ViewEnums;

import java.awt.*;

public class GameView extends View {
    private final RenderWorld renderWorld;

    public GameView(ViewManager viewManager) {
        super(viewManager);

        ParseWorld world = new ParseWorld("res/worlds/world_1.tmx");

        CharacterManager characterManager = handler.getGameState().getCharacterManger();

        Movement movement = new Movement(handler, world, characterManager);

        this.renderWorld = new RenderWorld(world, movement);

        initComponent();
    }

    @Override
    public void tick() {
        components.tick();
        renderWorld.tick();
    }

    @Override
    public void render(Graphics g) {
        renderWorld.render(g);
        components.render(g);
    }

    public void initComponent() {
        components.init(
                new PauseButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(900, 20),

                new Button("battle")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(ViewEnums.BATTLE);
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
