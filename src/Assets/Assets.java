package Assets;

import java.awt.image.BufferedImage;

import ImageStuff.ImageLoader;
import ImageStuff.SpriteSheet;

public class Assets {

    private static final int width = 16; //Size of each tile in sprite sheet
    private static final int height = 16;

    private static final int pWidth = 32;
    private static final int pHeight = 32;

    public static BufferedImage grass, rock, ledgeMiddleLeft, tree, bush, leftCornerLedge, ledge, bottomMiddleLedge, bottomRightLedge,
            ledgeMiddleRight, ledgeTopLeft, ledgeTopMiddle, ledgeTopRight, ledgeMiddleMiddle, grassLedge, ledgeCornerTopRight, ledgeCornerTopLeft, bar,
            critBar, barGrass, grassPlatform, enemyDescription, enemyHealthBar, slash, monsterLevel, monsterBar, playerDescription,
            greySquare, barOutline, coin, xp; //images from sprite sheet
    public static BufferedImage[][] player_animation, grass_tiles;
    public static BufferedImage[] buttonStart, trees, attackButton, escapeButton, alphabet, numbers, slashAnimation, attackBar, damageNumbers,
            miniAlphabet, statsButton, redNumbers, arrow, moneyNumbers, xpNumbers, monsters;

    private static BufferedImage[][] loadAnimations(SpriteSheet spriteSheet) {
        BufferedImage[][] animationFrames = new BufferedImage[13][8];
        for (int i = 0; i < animationFrames.length; i++) {
            for (int j = 0; j < animationFrames[i].length; j++) {
                animationFrames[i][j] = spriteSheet.crop(j * pWidth, i * pHeight, pWidth, pHeight);
            }
        }

        return animationFrames;
    }

    private static BufferedImage[] loadTileSet(SpriteSheet sheet, int x, int y, int tileWidth, int tileHeight) {
        BufferedImage[] tile = new BufferedImage[13]; // Total of 13 tiles: 9 from 3x3 and 4 from 2x2

        // Load 3x3 tiles
        for (int i = 0; i < 3; i++) { // Rows for the 3x3 section
            for (int j = 0; j < 3; j++) { // Columns for the 3x3 section
                tile[i * 3 + j] = sheet.crop((x + j) * tileWidth, (y + i) * tileHeight, tileWidth, tileHeight);
            }
        }

        // Load 2x2 tiles below the 3x3 tiles
        int startIdx = 9; // Start placing 2x2 tiles after the 9 tiles from 3x3
        for (int i = 0; i < 2; i++) { // Rows for the 2x2 section
            for (int j = 0; j < 2; j++) { // Columns for the 2x2 section
                tile[startIdx + i * 2 + j] = sheet.crop((x + j) * tileWidth, (y + 3) * tileHeight, tileWidth, tileHeight);
            }
        }

        return tile;
    }


    public static void init() {


        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Tiles.png"));

        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/Player/Player_New/Player_Anim/Player_Idle_Run_Death_Anim.png"));
        player_animation = loadAnimations(playerSheet);


        SpriteSheet ledgeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/LedgeTiles2.png"));
        SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Trees.png"));
        SpriteSheet barSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Bar.png"));
        SpriteSheet monsterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Monsters.png"));


        SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/Tiles/Grass/Grass_Tiles_1.png"));
        grass_tiles = new BufferedImage[4][13];

        grass_tiles[0] = loadTileSet(grassSheet, 0, 0, 16, 16);
        grass_tiles[1] = loadTileSet(grassSheet, 3, 0, 16, 16);
        grass_tiles[2] = loadTileSet(grassSheet, 0, 5, 16, 16);
        grass_tiles[3] = loadTileSet(grassSheet, 3, 5, 16, 16);

        grass = ImageLoader.loadImage("/Tiles/Grass/Grass_1_Middle.png");


        grassPlatform = barSheet.crop(101, 40, 86, 37);




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
