package Views.Menu;

import Assets.Assets;
import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums.*;

import java.awt.*;

import static enums.ViewEnums.*;

public class MenuView extends View {
    public MenuView(ViewManager viewManager) {
        super(viewManager);

        if (this.handler.getCharacter() == null) {
            System.out.println("No character selected...");
        }

        components.init(
                new Button("play")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(GAME);
                            }
                        })
                        .setLocation(400, 330),

                new Button("setting")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(SETTINGS);
                            }
                        })
                        .setLocation(400, 400),

                new Button("Exit")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("Exit clicked");
                            }
                        })
                        .setLocation(400, 470)
        );
    }

    @Override
    public void tick() {
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
        components.render(g);
    }
}
