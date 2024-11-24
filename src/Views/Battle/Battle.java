package Views.Battle;

import Components.Card.BattleStatistic;
import Components.Menu.SkillMenu;
import Components.Button.*;
import Components.Button.Button;
import Components.Layouts.*;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.*;
import Views.enums.Views;
import Battle.BattleManager;

import java.awt.*;

public class Battle extends View {
    private final BattleManager battleManager;
    public Battle(ViewManager viewManager) {
        super(viewManager);
        //should be gameState
        this.battleManager = handler.getBattleManager();

        components.init(
                new CharacterLayout(battleManager)
//                        .scale(8) // not working
                        .setLocation(80, 400),

                new SkillMenu(battleManager)
                        .setLocation(500, 650),

                new BattleStatistic(battleManager)
                        .setLocation(100, 650),

                new EnemyLayout(battleManager)
                        .setLocation(700, 400)
                        .showBounds(),

                new PauseButton()
                        .setAction(() -> viewManager.setView(Views.PAUSE))
                        .setLocation(900, 20),

                new Button("exit")
                        .setAction(battleManager::abortBattle)
                        .setLocation(680, 20)
        );
    }

    @Override
    public void tick() {
        battleManager.tick();
        components.tick();
    }

    @Override
    public void render(Graphics g) {

        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Forest.png"));

        g.drawImage(backgroundSheet.crop(0, 0, 962, 972), 0, 0, handler.getWidth(), handler.getHeight(), null);

        components.render(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(handler.getCharacterManager().getPlayer().getSkills().toString(), 20, 50);
//        g.drawString((battleManagerOld.getTurnState() == Turn.PLAYER ? "Player" : "Enemy") + " turn", 500, 400);
//        g.drawString(handler.getGameState(), 20, 70);


    }


}
