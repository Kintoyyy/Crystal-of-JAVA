package Views.Menu;

import Assets.Assets;
import Components.Button.Button;
import Utils.CallBackAction;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.View;
import Views.ViewManager;
import Views.enums.Views;

import java.awt.*;

import static Views.enums.Views.*;

public class Menu extends View {

    public Menu(ViewManager viewManager) {
        super(viewManager);
        components.init(
                createChangeViewButton("play", 330, GAME),
                createChangeViewButton("setting", 400, SETTINGS),
                new Button("Exit")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                handler.getGame().stop();
                            }
                        })
                        .setLocation(400, 470)
        );
    }

    private Button createChangeViewButton(String label, int yPosition, Views targetView) {
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


        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_1.png"));

        g.drawImage(backgroundSheet.crop(0, 0, 962, 972), 0, 0, handler.getWidth(), handler.getHeight(), null);

        components.render(g);
    }
}
