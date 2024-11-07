package Views.Overlay;

import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;

import java.awt.*;

import static enums.ViewEnums.*;

public class Pause extends View { // Extend JComponent instead of View
    public Pause(ViewManager viewManager) {
        super(viewManager);
        this.isOverlay = true;
        components.init(
                new Button("resume")
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

                new Button("Menu")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(MENU);
                            }
                        })
                        .setLocation(400, 470)
        );
    }

    @Override
    public void render(Graphics g) {
        components.render(g);
    }

    @Override
    public void tick() {
        components.tick();
    }
}
