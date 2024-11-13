package Views.Menu;

import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class Setting extends View {
    public Setting(ViewManager viewManager) {
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
        components.render(g);
    }
}
