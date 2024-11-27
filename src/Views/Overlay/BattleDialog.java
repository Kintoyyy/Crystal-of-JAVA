package Views.Overlay;

import Components.Button.Button;
import Components.ComponentEventListener;
import Components.Dialog.Dialog;
import Components.Text.Text;
import Utils.ImageUtils;
import Utils.SpriteSheet;
import Views.View;
import Views.enums.Views;
import fonts.SuperPixelFont;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BattleDialog extends View {
    private int dialogIndex = 0;
    private final ArrayList<String> preBattleDialogs;
    private final Runnable onComplete;
    private Text text;
    private final BufferedImage dialogImage;

    public BattleDialog(ArrayList<String> preBattleDialogs, Runnable onComplete) {
        this.preBattleDialogs = preBattleDialogs;
        this.onComplete = onComplete;
        isOverlay = true;


        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
        dialogImage = sheet.crop(0, 45, 183, 46);

        text = (Text) new Text(preBattleDialogs.get(dialogIndex))
                .setFont(new SuperPixelFont(24))
                .setColor(new Color(116, 63, 58))
                .setLocation(150, 560)
                .showBounds();

        components.addComponents(
                new Button("Next")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                if (dialogIndex < preBattleDialogs.size() - 1) {
                                    dialogIndex++;
                                } else {
                                    onComplete.run();
                                }
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
                        .setLocation(text.getX() + (183 * 4) - 220, (int) (text.getY() + (46 * 4) - 20)),


                new Button("Exit")
                        .setEventListener(new ComponentEventListener() {
                            @Override
                            public void onComponentClick(MouseEvent event) {
                                viewManager.setView(Views.GAME);
                            }

                            @Override
                            public void onMouseEnter(MouseEvent event) {

                            }

                            @Override
                            public void onMouseExit(MouseEvent event) {

                            }
                        })
                        .setLocation(text.getX() - 20, (int) (text.getY() + (46 * 4) - 20))

        );
    }

    @Override
    public void tick() {
        text.tick();
        components.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage((Image) dialogImage, (int) (text.getX() - 20), (int) (text.getY() - 20), 183 * 4, 46 * 4, null);
        text.setText(preBattleDialogs.get(dialogIndex));
        text.render(g);
        components.render(g);
    }

    @Override
    public void setData(Object data) {
    }
}
