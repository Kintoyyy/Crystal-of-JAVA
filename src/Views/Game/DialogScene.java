package Views.Game;

import Assets.Assets;
import Components.Button.Button;
import Components.Dialog.Dialog;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;

import java.awt.*;

public class DialogScene extends View {
    public DialogScene(ViewManager viewManager) {
        super(viewManager);
        if (this.handler.getCharacter() == null) {
            System.out.println("No character selected...");
        }

        String testText = "In the heart of Cytu, where myths linger like morning mist, fate calls upon a new hero. Unknown forces stir in the shadows, and the hour grows dark. It is here, on the edge of the Whispering Woods, that Nathaniel begins his journey.";

        components.init(
                new Dialog(testText)
                        .showBounds()
                        .setLocation(50, 480)
                        .scale(2),
//                        .setDimensions(900, 260)
                new Button("Test button")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
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
        g.drawImage(Assets.background, 0, 0, handler.getWidth(), handler.getHeight(), null);
        components.render(g);
    }
}
