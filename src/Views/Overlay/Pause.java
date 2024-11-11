package Views.Overlay;

import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

import static enums.ViewEnums.*;

public class Pause extends View {

    public Pause(ViewManager viewManager) {
        super(viewManager);
        this.isOverlay = true;

        components.init(
                createChangeViewButton("resume", 330, GAME),
                createChangeViewButton("setting", 400, SETTINGS),
                createChangeViewButton("Menu", 470, MENU)
        );
    }

    private Button createChangeViewButton(String label, int yPosition, ViewEnums targetView) {
        return (Button) new Button(label)
                .setAction(() -> viewManager.setView(targetView))
                .setLocation(400, yPosition);
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
