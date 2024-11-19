package Views.Battle;

import Game.Handler;
import Views.ViewManager;
import Views.enums.Views;
import Worlds.Battle;
import Worlds.Enums.Turn;
import Worlds.Forest.Battles.*;

import java.nio.channels.Channel;
import java.util.LinkedList;
import java.util.Queue;

public class BattleManager {
    private Battle battle;
    private final ViewManager viewManager;
    protected Queue<Turn> turnqueue = new LinkedList<>();
    protected final Handler handler;

    public BattleManager(Handler handler) {
        this.handler = handler;
        this.viewManager = handler.getViewManager();
        handler.setBattleManager(this);
        turnqueue.add(Turn.PLAYER);
        turnqueue.add(Turn.ENEMY);
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

    public void startBattle(String battleName) {
        // Execption
        battle = handler.getWorldManager().getBattle(battleName);

        if (battle == null) return;

        System.out.println("Battle loaded: " + battleName);

        // start battle
        viewManager.setView(Views.BATTLE);
    }

    public void updateTurn() {
        Turn turn = turnqueue.remove();
        turnqueue.add(turn);
    }

    public Turn getCurrentTurn() {
        return turnqueue.peek();
    }

    public void skipTurn() {
        Turn turn = turnqueue.remove();
        turnqueue.add(turn);
        turnqueue.add(turn);
    }
}
