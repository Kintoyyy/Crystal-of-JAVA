package Views.Menu;

import Components.Button.Button;
import Components.ComponentEventListener;
import Utils.CallBackAction;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.awt.event.MouseEvent;

import static Views.enums.Views.*;

public class Menu extends View {

    public Menu() {
        components.addComponents(
                createChangeViewButton("play", 330, DIALOG),
                createChangeViewButton("setting", 400, SETTINGS),
                new Button("Exit")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                handler.getGame().stop();
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
                        .setLocation(400, 470)
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
    public void tick() {
        components.tick();
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void render(Graphics g) {


        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_1.png"));

        g.drawImage(backgroundSheet.crop(0, 0, 962, 972), 0, 0, handler.getWidth(), handler.getHeight(), null);

        components.render(g);
    }
}
