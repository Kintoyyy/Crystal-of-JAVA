package Views.Overlay;

import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

public class Pause extends View {

    public Pause(ViewManager viewManager) {
        super(viewManager);
        this.isOverlay = true;

        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager) {
        components.init(
                createResumeButton(viewManager),
                createChangeViewButton("Settings", 400, ViewEnums.SETTINGS),
                createChangeViewButton("Menu", 470, ViewEnums.MENU)
        );
    }

    private Button createResumeButton(ViewManager viewManager) {
        return (Button) new Button("Resume")
                .setAction(() -> viewManager.removeView(ViewEnums.PAUSE))
                .setLocation(400, 330);
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
