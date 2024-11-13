package Components;

import Characters.Character;
import Entities.Player;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Components.Button.Button;

public class CharacterMenu extends Component {
    private ArrayList<Character> characters;
    protected SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Game_UI.png"));
    protected BufferedImage[] buttonSheet = new BufferedImage[3];
    private Button button;

    public CharacterMenu(ArrayList<Character> characters) {
        super();
        this.characters = characters;

        buttonSheet[1] = sheet.crop(29, 92, 28, 28);
        buttonSheet[0] = sheet.crop(58, 92, 28, 28);
        buttonSheet[2] = sheet.crop(0, 92, 28, 28);

        button = (Button) new Button("Character")
                .setButtonSheet(buttonSheet)
                .hideText()
                .setDimensions(28, 28)
                .scale(3);
    }

    @Override
    public void tick() {
        System.out.println(button.state);
        button.tick();
    }

    @Override
    public void render(Graphics g) {
        int x = 350;
        for (int i = 0; i < 4; i++) {
            button.setLocation(x, 700);
            button.render(g);
            x += 80;
        }
    }

    @Override
    public void onClick() {
        button.onClick();
    }
}
