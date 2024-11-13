package Views.Menu;

import Components.Button.Button;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class SelectCharacter extends View {
    public SelectCharacter(ViewManager viewManager) {
        super(viewManager);
        this.isOverlay = true;
        components.init(
                new Button("play")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(ViewEnums.BATTLE);
                            }
                        })
                        .setLocation(400, 400),

                new Button("Exit")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
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
