package Views;

import Assets.*;
import Game.ClickListener;
import Game.UIImageButton;
import Game.UIManager;
import fonts.SuperPixelFont;
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

        playerDescription = new Description(2, Player.name, Player.health, Player.baseHealth, Player.level, handler);

    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);

        g.setColor(Color.white);
        g.setFont(new SuperPixelFont(30));

        g.drawString("Hello World", 100, 200);
        g.drawImage(Assets.ui_frame[0], 0, 0, 300, 130, null);

//        g.drawImage(Assets.ui_frame[1], 120,120, 739, 600, null);
//		playerDescription.render(g);
//		coinsText = new Text(coins + "", 50, 126, 4, 4);
//		coinsText.render(g);
    }


}
