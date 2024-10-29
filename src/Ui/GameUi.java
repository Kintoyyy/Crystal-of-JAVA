package Ui;

import Assets.Assets;

import java.awt.*;

public class GameUi extends Ui {

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ui_frame[0], 0,0, 300, 130, null);
        g.drawImage(Assets.ui_frame[1], 120,120, 739, 600, null);
    }
}
