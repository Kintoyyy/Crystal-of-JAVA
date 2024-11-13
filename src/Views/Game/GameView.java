package Views.Game;

import Components.Button.Button;
import Components.Button.PauseButton;
import Components.Menu.CharacterTopLeftMenu;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;
import World.World;

import java.awt.*;

public class GameView extends View {
    private final World world;

    public GameView(ViewManager viewManager) {
        super(viewManager);
        world = handler.getWorld();

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

                new CharacterTopLeftMenu(handler)
                        .setLocation(12, 12)
                        .scale(6)
        );
    }

    @Override
    public void tick() {
        components.tick();
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        components.render(g);
    }
}
