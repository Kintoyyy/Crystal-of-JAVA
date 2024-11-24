package Components.Card;

import Entities.Characters.Character;
import Battle.BattleManager;

import java.awt.*;

public class BattleStatistic extends Card {
    private final BattleManager battleManager;
    private Character player;

    public BattleStatistic(BattleManager battleManager) {
        super();
        this.battleManager = battleManager;
        this.player = battleManager.getCharacterManager().getPlayer();
    }

    @Override
    public void tick() {
        player = battleManager.getCharacterManager().getPlayer();
    }

    @Override
    public void render(Graphics g) {
        g.drawString("Battle Statistic", 100, (int) y);
        g.drawString("Player Name: " + player.getName(), 100, (int) y + 20);
        g.drawString("Player Health: " + player.getHealth().getHealth() + " / " + player.getHealth().getBaseHealth(), 100, (int) y + 30);
        g.drawString("Player Mana: " + player.getMana().getMana() + " / " + player.getMana().getBaseMana(), 100,  (int) y + 40);
    }

    @Override
    public void onClick() {

    }
}
