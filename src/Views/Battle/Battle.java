package Views.Battle;

import Components.Card.BattleStatistic;
import Components.ComponentEventListener;
import Components.Menu.SkillMenu;
import Components.Button.*;
import Components.Button.Button;
import Components.Layouts.*;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Battle extends View {

    private final Text text;
    private String backgroundPath;

    public Battle() {
        components.addComponents(new CharacterLayout(battleManager).scale(8) // not working
                        .setLocation(80, 400),

                new SkillMenu(battleManager).scale(3).setLocation(500, 615),

                new BattleStatistic(battleManager).scale(3).setLocation(80, 610),

                new EnemyLayout(battleManager).setLocation(700, 400).showBounds(),

                new PauseButton().setLocation(900, 20),

                new Button("exit")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                battleManager.abortBattle();
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        }).setLocation(680, 20));

        text = (Text) new Text("Hello").setLocation(100, 100);
    }

    @Override
    public void tick() {
        battleManager.tick();
        components.tick();
        text.tick();
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void render(Graphics g) {

        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage(handler.getWorldManager().getCurrentWorld().getBattleBackground()));

        g.drawImage(backgroundSheet.crop(0, 0, 962, 972), 0, 0, handler.getWidth(), handler.getHeight(), null);

        components.render(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString(handler.getCharacterManager().getPlayer().getSkills().toString(), 20, 50);
//        g.drawString((battleManagerOld.getTurnState() == Turn.PLAYER ? "Player" : "Enemy") + " turn", 500, 400);
//        g.drawString(handler.getGameState(), 20, 70);
//        System.out.println(battleManager.isPlayersTurn());
        text.setText(battleManager.isPlayersTurn() ? "Players turn" : "Enemy Turn : " + battleManager.getTimer());
        text.render(g);
    }


}
