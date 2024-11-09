package Views.Game;

import Assets.*;
import Characters.Character;
import Components.Button.BackButton;
import Components.Button.Button;
import Components.CharacterMenu;
import Components.Frame.CharacterFrame;
import Components.Text.Text;
import Entities.Player;
import Game.CallBackAction;
import Views.View;
import Views.ViewManager;
import enums.Alignment;
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

    private int i = 0;

    private CharacterMenu characterMenu;

    public GameView(ViewManager viewManager) {
        super(viewManager);
        world = new World(handler, "res/worlds/world_1.tmx");
        handler.setWorld(world);
        Character player = handler.getGameState().getPlayer();
        playerDescription = new Description(2, "asdasd", 100, 100, 69, handler);
//        handler.getWidth()
        components.init(
                new BackButton()
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {
                                viewManager.setView(ViewEnums.PAUSE);
                            }
                        })
                        .setLocation(900, 20),

                new Button("change")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onClick() {

                                i++;
                                handler.getGameState().setPlayer(i % 4);

                                System.out.println(i % 4 + " " + handler.getGameState().getPlayer().getName());
                            }
                        })
                        .setLocation(600, 60),

                new Components.Menu.CharacterMenu(handler.getGameState().getCharacters()).setLocation(300,300)
//                new CharacterFrame(handler.getGameState().getPlayer()).isActive(true).setLocation(300, 300)
//                new CharacterMenu(handler.getGameState().getCharacters())
        );

    }

    @Override
    public void tick() {
        components.tick();
        world.tick();
//        characterMenu.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        components.render(g);

        g.drawString(handler.getGameState().getPlayer().getName(), 100, 200);
//        g.drawImage(Assets.ui_frame[0], 0, 0, 300, 130, null);

//        g.drawImage(Assets.ui_frame[1], 120,120, 739, 600, null);
//		playerDescription.render(g);
//		coinsText = new Text(coins + "", 50, 126, 4, 4);
//		coinsText.render(g);
    }


}
