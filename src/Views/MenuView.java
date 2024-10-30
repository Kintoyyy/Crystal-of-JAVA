package Views;

import Assets.Assets;
import Utils.SuperPixelFont;

import java.awt.*;

public class MenuView extends View {

    public MenuView(ViewManager viewManager) {
        super(viewManager);
        if(this.handler.getCharacter() == null){
            System.out.println("No character selected...");
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.background ,0,0, handler.getWidth(), handler.getHeight(), null);

        g.setColor(Color.white);
        g.setFont(new SuperPixelFont(40));
        g.drawString("Crystal of JAVA", 250, 300);
    }
}
