package Animations;

import Animations.enums.DIRECTION;
import Animations.enums.TYPE;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Animation {
    protected int speed = 120;

    private final HashMap<String, BufferedImage[]> animations = new HashMap<>();
    private String key;

    private long lastTime, timer;
    private int index;

    // Default animation key and frames
    private static final String DEFAULT_KEY = "DEFAULT";
    private BufferedImage[] defaultFrames;

    public Animation() {
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    public final void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            timer = 0;
            index++;
            if (index >= getFramesForKey(key).length) {
                index = 0;
            }
        }
    }

    public final BufferedImage getFrame(TYPE type) {
        key = type.name();
        return getFramesForKey(key)[index];
    }

    public final BufferedImage getFrame(TYPE type, DIRECTION direction) {
        key = type.name() + "_" + direction.name();
        return getFramesForKey(key)[index];
    }

    public final void addAnimation(TYPE type, BufferedImage[] frames) {
        animations.put(type.name(), frames);
    }

    public final void addAnimation(TYPE type, DIRECTION direction, BufferedImage[] frames) {
        animations.put(type.name() + "_" + direction.name(), frames);
    }

    public final void setDefaultAnimation(BufferedImage[] frames) {
        if (frames != null && frames.length > 0) {
            defaultFrames = frames;
            animations.put(DEFAULT_KEY, frames);
        } else {
            throw new IllegalArgumentException("Default animation frames cannot be null or empty.");
        }
    }

    private BufferedImage[] getFramesForKey(String animationKey) {
        if (animations.containsKey(animationKey)) {
            return animations.get(animationKey);
        } else {
            // Fallback to default animation
            if (defaultFrames != null) {
                return defaultFrames;
            }
            throw new IllegalStateException("No animation found for key: " + animationKey + ", and no default animation is set.");
        }
    }
}
