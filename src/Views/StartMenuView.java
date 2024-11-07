package Views;

import Components.Button;
import Game.CallBackAction;
import enums.ViewEnums;

import java.awt.*;

public class StartMenuView extends View {
    public StartMenuView(ViewManager viewManager) {
        super(viewManager);
        components.init(
                new Button("Test button")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.GAME);
                            }
                        })
                        .hideText()
                        .showBounds()
                        .setLocation(400, 320),

                new Button("Test button")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.MAIN_MENU);
                            }
                        })
                        .hideText()
                        .setLocation(400, 400),
                new Button("Test button")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("Exit clicked");
                            }
                        })
                        .hideText()
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
