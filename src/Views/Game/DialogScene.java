package Views.Game;

import Components.Button.Button;
import Components.Dialog.Dialog;
import Utils.CallBackAction;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.View;
import Views.ViewManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogScene extends View {
    public DialogScene(ViewManager viewManager) {
        super(viewManager);
        String testText = "In the heart of Cytu, where myths linger like morning mist, fate calls upon a new hero. Unknown forces stir in the shadows, and the hour grows dark. It is here, on the edge of the Whispering Woods, that Nathaniel begins his journey.";

        components.init(
                new Dialog(testText)
                        .showBounds()
                        .setLocation(50, 480)
                        .scale(2),
//                        .setDimensions(900, 260)
                new Button("Test button")
                        .setRightClickAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                System.out.println("Button clicked");
                            }
                        })
                        .hideText()
                        .setLocation(740, 700)
        );
    }

    @Override
    public void tick() {
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_1.png"));

        BufferedImage background = backgroundSheet.crop(0, 0, 800, 678);

        g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);
        components.render(g);
    }
}
