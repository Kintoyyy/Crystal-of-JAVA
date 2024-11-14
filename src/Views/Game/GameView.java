package Views.Game;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Card.CharacterCard;
import Rendering.GameRenderer;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class GameView extends View {
//    private final World world;
    private final GameRenderer gameRenderer;

    public GameView(ViewManager viewManager) {
        super(viewManager);
//        world = handler.getWorld();
        gameRenderer = new GameRenderer(handler);

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

    @Override
    public void tick() {
        components.tick();
        gameRenderer.tick();
//        world.tick();
    }

    @Override
    public void render(Graphics g) {
//        world.render(g);

        gameRenderer.render(g);

        components.render(g);
    }
}
