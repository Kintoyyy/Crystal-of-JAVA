package Views.Overlay;

import Components.Button.Button;
import Components.ComponentEventListener;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Pause extends View {

    public Pause() {
        this.isOverlay = true;
        initComponents();
    }

    private void initComponents() {
        components.addComponents(
                createChangeViewButton("Resume", 330, Views.GAME),
                createChangeViewButton("Settings", 400, Views.SETTINGS),
                createChangeViewButton("Menu", 470, Views.MENU)
        );
    }

    private Button createChangeViewButton(String label, int yPosition, Views targetView) {
        return (Button) new Button(label)
                .setEventListener(new ComponentEventListener() {
                    @Override
                    public void onComponentClick(MouseEvent event) {
                        viewManager.setView(targetView);
                    }

                    @Override
                    public void onMouseEnter(MouseEvent event) {

                    }

                    @Override
                    public void onMouseExit(MouseEvent event) {

                    }
                })
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
