package Views.Menu;

import Battle.BattleManager;
import Components.Button.Button;
import Components.ComponentEventListener;
import Utils.CallBackAction;
import Views.View;
import Views.enums.Views;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Setting extends View {
    private final BattleManager battleManager;

    public Setting() {
//        super(viewManager);
        this.isOverlay = true;
        this.battleManager = handler.getBattleManager();

        components.addComponents(
                new Button("back")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                viewManager.setView(Views.PAUSE);
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
                        .setLocation(100, 100),
                
                new Button("kill all")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                battleManager.killAllEnemies();
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
                        .setLocation(680, 80)
        );
    }

    @Override
    public void tick() {
        components.tick();
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public void render(Graphics g) {
        components.render(g);
    }
}
