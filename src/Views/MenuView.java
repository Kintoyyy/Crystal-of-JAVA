package Views;

import Assets.Assets;
import Components.Button.Button;
import Components.Dialog;
import Game.CallBackAction;

import java.awt.*;

public class MenuView extends View {
    public MenuView(ViewManager viewManager) {
        super(viewManager);
        if (this.handler.getCharacter() == null) {
            System.out.println("No character selected...");
        }

        String testText = "In the heart of Cytu, where myths linger like morning mist, fate calls upon a new hero. Unknown forces stir in the shadows, and the hour grows dark. It is here, on the edge of the Whispering Woods, that Nathaniel begins his journey.";

        components.init(
//                new Text(testText)
//                        .typing()
//                        .setFont(new SimplePixelFont(16))
//                        .setColor(Color.WHITE)
//                        .setAlignment(Alignment.LEFT)
//                        .setLocation(40, 30)
//                        .setDimensions(880, 200),

                new Dialog(testText)
                        .setScale(5)
                        .showBounds()
                        .setLocation(50, 480),
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
