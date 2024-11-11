package Views.Menu;

import Assets.Assets;
import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;

import java.awt.*;

import static enums.ViewEnums.*;

public class MenuView extends View {

    public MenuView(ViewManager viewManager) {
        super(viewManager);

        if (this.handler.getCharacter() == null) {
            System.out.println("No character selected...");
        }

        components.init(
                createChangeViewButton("play", 330, GAME),
                createChangeViewButton("setting", 400, SETTINGS),
                new Button("Exit")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                System.out.println("Exit clicked");
                            }
                        })
                        .setLocation(400, 470)
        );
    }

    private Button createChangeViewButton(String label, int yPosition, ViewEnums targetView) {
        return (Button) new Button(label)
                .setAction(() -> viewManager.setView(targetView))
                .setLocation(400, yPosition);
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
