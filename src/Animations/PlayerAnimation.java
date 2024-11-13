package Animations;

import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.image.BufferedImage;

import static Constants.PlayerAnimation.*;

public class PlayerAnimation {
    private final int speed;
    private int index;
    private long lastTime, timer;
    private final BufferedImage[][] frames;
    private int action;
    ImageUtils img = new ImageUtils();

    private static final int width = 32; //Size of each tile in sprite sheet
    private static final int height = 32;

    private static final int pWidth = 32;
    private static final int pHeight = 32;

    public PlayerAnimation(int speed, SpriteSheet sheet) {
        this.speed = speed;
        this.frames = loadAnimations(sheet);;
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            timer = 0;
            index++;
            if (index >= getSpriteAmount(action)) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame(String direction, int action) {
        this.action = action;
        int frameSet = getFrameSet(action, direction);
        if (LEFT.equals(direction)) {
            return img.flipX(frames[frameSet][index]);
        }
        return frames[frameSet][index];
    }

    private static BufferedImage[][] loadAnimations(SpriteSheet spriteSheet) {
        BufferedImage[][] animationFrames = new BufferedImage[13][8];
        for (int i = 0; i < animationFrames.length; i++) {
            for (int j = 0; j < animationFrames[i].length; j++) {
                animationFrames[i][j] = spriteSheet.crop(j * pWidth, i * pHeight, pWidth, pHeight);
            }
        }
        return animationFrames;
    }
}
