package Views.Overlay;

import Components.Button.Button;
import Components.Dialog.Dialog;
import Views.View;
import Views.ViewManager;
import Views.enums.Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BattleDialog extends View {
    private ArrayList<String> dialogs = new ArrayList<>();

    public BattleDialog(ViewManager viewManager) {
        super(viewManager);
        this.isOverlay = true;

        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager) {
        components.init(
                new Dialog("hello").setLocation(20, 20),

                new Button("Next").setAction(
                        () -> {
                            System.out.println("Next!");
                        }
                ).setLocation(40, 40)
        );
    }

    @Override
    public void render(Graphics g) {
        components.render(g);
    }

    @Override
    public void tick() {
        components.tick();
    }

    public void setDialogs(String... dialogsString) {
        dialogs.addAll(List.of(dialogsString));
    }
}
