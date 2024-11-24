package Assets;

import java.awt.image.BufferedImage;

import Utils.SpriteSheet;
import Utils.ImageUtils;

public class Assets {

    private static final int width = 32; //Size of each tile in sprite sheet
    private static final int height = 32;

    public static BufferedImage[] ui_frame = new BufferedImage[2];
    public static BufferedImage background;

    public static BufferedImage  bush,bar,
            critBar, barGrass, grassPlatform, enemyDescription, enemyHealthBar, slash, monsterLevel, monsterBar, playerDescription,
            greySquare, coin, xp;

    public static BufferedImage[] buttonStart, trees, attackButton, escapeButton, alphabet, numbers, slashAnimation, attackBar, damageNumbers,
            miniAlphabet, statsButton, redNumbers, arrow, moneyNumbers, xpNumbers, monsters;

    public static void init() {


        SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/textures/Tiles.png"));

        SpriteSheet treeSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Trees.png"));
        SpriteSheet barSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Bar.png"));
        SpriteSheet monsterSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Monsters.png"));

        SpriteSheet ui_bars = new SpriteSheet(ImageUtils.loadImage("/ui/UI_Bars.png"));
        SpriteSheet ui_inventory = new SpriteSheet(ImageUtils.loadImage("/ui/UI_Premade.png"));

        ui_frame[0] = ui_bars.crop(96, 6, 46, 22);
        ui_frame[1] = ui_inventory.crop(33, 25, 196, 160);



        SpriteSheet backgroundSheet = new SpriteSheet(ImageUtils.loadImage("/Backgrounds/Background_1.png"));

        background = backgroundSheet.crop(0,0,800,678);

        bush = sheet.crop(0, height * 3, width, height);


        buttonStart = new BufferedImage[2];
        buttonStart[0] = sheet.crop(0, height, width * 2, height);
        buttonStart[1] = sheet.crop(0, height * 2, width * 2, height);

        attackButton = new BufferedImage[2];
        attackButton[0] = sheet.crop(40, 0, 49, 17);
        attackButton[1] = sheet.crop(89, 0, 49, 17);

        statsButton = new BufferedImage[2];
        statsButton[0] = sheet.crop(40, 17, 49, 17);
        statsButton[1] = sheet.crop(89, 17, 49, 17);

        escapeButton = new BufferedImage[2];
        escapeButton[0] = sheet.crop(40, 34, 49, 17);
        escapeButton[1] = sheet.crop(89, 34, 49, 17);

        attackBar = new BufferedImage[2];
        attackBar[0] = sheet.crop(32, 0, 4, 34);
        attackBar[1] = sheet.crop(36, 0, 4, 34);

        arrow = new BufferedImage[2];
        arrow[0] = barSheet.crop(235, 76, 12, 12);
        arrow[1] = barSheet.crop(235, 76, 12, 12);

        trees = new BufferedImage[4];
        trees[0] = treeSheet.crop(0, 0, width * 3, height * 3);
        trees[1] = treeSheet.crop(width * 3, 0, width * 3, height * 3);
        trees[2] = treeSheet.crop(0, height * 3 + 10, width * 3, height * 3);
        trees[3] = treeSheet.crop(width * 3, height * 3 + 10, width * 3, height * 3);

        monsters = new BufferedImage[1];
        monsters[0] = monsterSheet.crop(0, 0, 38, 27);
        //monsters[1] = monsterSheet.crop(0, 27, 23, 41);

        slashAnimation = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {
            slashAnimation[i] = barSheet.crop(187 + i * 6, 52, 6, 25);
        }

        alphabet = new BufferedImage[28];
        for (int i = 0; i < 28; i++) {
            alphabet[i] = barSheet.crop(101 + i * 6, 32, 5, 8);
        }

        miniAlphabet = new BufferedImage[28];
        int count = 0;
        for (int i = 0; i < 28; i++) {
            if (i == 12) {
                count++;
                miniAlphabet[i] = barSheet.crop(101 + i * 5, 25, 5, 6);
            } else if (i == 22) {
                count++;
                miniAlphabet[i] = barSheet.crop(101 + i * 5 + 1, 25, 5, 6);
            } else if (i == 27) {
                miniAlphabet[i] = barSheet.crop(101 + i * 5, 25, 3, 6);
            } else {
                miniAlphabet[i] = barSheet.crop(101 + i * 5 + count, 25, 4, 6);
            }
        }

        numbers = new BufferedImage[11];
        for (int i = 0; i < 10; i++) {
            numbers[i] = barSheet.crop(246 + i * 5, 25, 4, 6);
        }
        numbers[10] = barSheet.crop(296, 25, 4, 6);

        damageNumbers = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            damageNumbers[i] = barSheet.crop(187 + i * 7, 40, 7, 8);
        }

        redNumbers = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            redNumbers[i] = barSheet.crop(234 + i * 5, 49, 4, 6);
        }

        moneyNumbers = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            moneyNumbers[i] = barSheet.crop(235 + i * 6, 58, 5, 8);
        }

        xpNumbers = new BufferedImage[11];
        for (int i = 0; i < 11; i++) {
            xpNumbers[i] = barSheet.crop(235 + i * 6, 67, 5, 8);
        }


        //bar = barSheet.crop(0, 0, 300, 25);
        critBar = barSheet.crop(299, 0, 6, 25);
        barGrass = barSheet.crop(7, 25, 94, 85);

        enemyDescription = barSheet.crop(102, 77, 102, 33);
        enemyHealthBar = barSheet.crop(204, 88, 77, 10);
        slash = barSheet.crop(187, 57, 6, 20);
        monsterLevel = barSheet.crop(204, 77, 23, 10);
        monsterBar = barSheet.crop(0, 110, 20, 26);
        playerDescription = barSheet.crop(0, 110, 105, 38);
        greySquare = barSheet.crop(105, 110, 185, 55);
        bar = barSheet.crop(105, 165, 150, 13);
        coin = barSheet.crop(204, 98, 8, 8);
        xp = barSheet.crop(212, 98, 10, 8);
    }

}
