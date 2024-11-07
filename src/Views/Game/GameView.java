package Views.Game;

import Assets.*;
import Components.Button.BackButton;
import Components.Button.Button;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.ViewEnums;
import World.World;

import java.awt.*;

public class GameView extends View {
    private Player player;
    private World world;
    public static boolean flag;
    private Description playerDescription;

    public static int coins = 0;
    public static int xp = 0;
    private Text coinsText;

    public GameView(ViewManager viewManager) {
        super(viewManager);
        world = new World(handler, "res/worlds/world_1.tmx");
        handler.setWorld(world);
//        playerDescription = new Description(2, Player.name, Player.health, Player.baseHealth, Player.level, handler);
//        handler.getWidth()
        components.init(
                new BackButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(900, 20)
        );

    }

    @Override
    public void tick() {
        components.tick();
        world.tick();

    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        components.render(g);

//        g.setColor(Color.white);
//        g.setFont(new SuperPixelFont(30));

//        g.drawString("Hello World", 100, 200);
//        g.drawImage(Assets.ui_frame[0], 0, 0, 300, 130, null);

//        g.drawImage(Assets.ui_frame[1], 120,120, 739, 600, null);
//		playerDescription.render(g);
//		coinsText = new Text(coins + "", 50, 126, 4, 4);
//		coinsText.render(g);
    }


}
