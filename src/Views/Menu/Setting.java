package Views.Menu;

import Battle.BattleManager;
import Components.Button.Button;
import Utils.CallBackAction;
import Views.View;
import Views.ViewManager;
import Views.enums.Views;

import java.awt.*;

public class Setting extends View {
    private final BattleManager battleManager;

    public Setting(ViewManager viewManager) {
        super(viewManager);
        this.isOverlay = true;
        this.battleManager = handler.getBattleManager();

        components.init(
                new Button("back")
                        .setAction(new CallBackAction() {
                            @Override
                            public void onAction() {
                                viewManager.setView(Views.PAUSE);
                            }
                        })
                        .setLocation(100, 100),
                
                new Button("kill all")
                        .setAction(battleManager::killAllEnemies)
                        .setLocation(680, 80)
        );
    }

    @Override
    public void tick() {
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        components.render(g);
    }
}
