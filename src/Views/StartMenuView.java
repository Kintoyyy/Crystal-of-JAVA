package Views;

import Components.Button.Button;
import Components.Button.PlayButton;
import Game.CallBackAction;
import enums.ViewEnums;

import java.awt.*;

public class StartMenuView extends View {
    public StartMenuView(ViewManager viewManager) {
        super(viewManager);
        components.init(
                new PlayButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.GAME);
                            }
                        })
                        .showBounds()
                        .setLocation(400, 320),

                new Button("play")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.BATTLE);
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
                        .setLocation(400, 480)
        );
    }

    @Override
    public void tick() {
        components.tick();
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
        components.render(g);
    }
}
