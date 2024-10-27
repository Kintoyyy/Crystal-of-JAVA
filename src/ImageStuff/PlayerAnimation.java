package ImageStuff;

import Utils.ImageUtils;

import java.awt.image.BufferedImage;

import static Constants.PlayerAnimation.*;

public class PlayerAnimation {
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[][] frames;
    private int action;
    ImageUtils img = new ImageUtils();


    public PlayerAnimation(int speed, BufferedImage[][] frames) {
        this.speed = speed;
        this.frames = frames;
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
}
