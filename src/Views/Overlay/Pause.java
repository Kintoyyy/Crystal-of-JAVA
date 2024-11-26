package Views.Overlay;

import Components.Button.Button;
import Views.View;
import Views.ViewManager;
import Views.enums.Views;

import java.awt.*;

public class Pause extends View {

    public Pause() {
        this.isOverlay = true;
        initComponents();
    }

    private void initComponents() {
        components.init(
                createResumeButton(),
                createChangeViewButton("Settings", 400, Views.SETTINGS),
                createChangeViewButton("Menu", 470, Views.MENU)
        );
    }

    private Button createResumeButton() {
        return (Button) new Button("Resume")
                .setRightClickAction(() -> viewManager.removeView(Views.PAUSE))
                .setLocation(400, 330);
    }

    private Button createChangeViewButton(String label, int yPosition, Views targetView) {
        return (Button) new Button(label)
                .setRightClickAction(() -> viewManager.setView(targetView))
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

    @Override
    public void setData(Object data) {

    }
}
