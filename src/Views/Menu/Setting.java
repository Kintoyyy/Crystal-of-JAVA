package Views.Menu;

import Components.Button.Button;
import Components.Button.PlayButton;
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
                new PlayButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
//                                viewManager.setView(ViewEnums.SELECT_CHARACTER);

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
        components.render(g);
    }
}
