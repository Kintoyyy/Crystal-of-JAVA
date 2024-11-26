package Animations;

import Animations.enums.DIRECTION;
import Animations.enums.TYPE;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * The {@code Animation} class is responsible for handling the animation logic for different types and directions of animations.
 * It provides functionality for managing and displaying frames based on animation triggerType and direction, controlling the
 * frame update rate, and allowing the addition of custom animations.
 * <p>
 * The class maintains a set of animations in a map, where each key is a combination of animation triggerType and direction,
 * and the value is an array of frames (images). It supports both default and custom animations and automatically loops
 * through frames when the animation reaches the end.
 * </p>
 */
public class Animation {

    /**
     * The speed (in milliseconds) at which the animation frames change.
     * The default speed is 120 milliseconds.
     */
    protected int speed = 120;

    /**
     * A map that stores animations for each triggerType and direction, where the key is the animation triggerType and/or direction,
     * and the value is an array of {@link BufferedImage} frames.
     */
    private final HashMap<String, BufferedImage[]> animations = new HashMap<>();

    /**
     * The current key used to access the frames for the active animation.
     */
    private String key;

    /**
     * The time of the last tick update in milliseconds.
     */
    private long lastTime, timer;

    /**
     * The current index of the active frame.
     */
    private int index;


    private int width, height;

    /**
     * The default key used when no specific animation is found.
     */
    private static final String DEFAULT_KEY = "DEFAULT";

    /**
     * The frames for the default animation, used when no specific animation is found.
     */
    private BufferedImage[] defaultFrames;

    /**
     * Constructs an {@code Animation} object, initializing the animation state.
     * Sets the initial frame index to 0 and records the current system time for the first tick.
     */
    public Animation(Frames sheet) {
        this.height = sheet.getHeight();
        this.width = sheet.getWidth();
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * Updates the animation by advancing the frame index based on the speed.
     * This method should be called regularly (e.g., in the game loop) to update the animation.
     */
    public final void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > speed) {
            timer = 0;
            index++;
            if (index >= getFramesForKey(key).length) {
                index = 0;  // Loop the animation
            }
        }
    }

    /**
     * Retrieves the current frame for the given animation key.
     *
     * @param key The unique key identifying the animation frames.
     * @return The current {@link BufferedImage} frame for the specified key, or {@code null} if an error occurs.
     */
    private BufferedImage getFrameByKey(String key) {
        try {
            return getFramesForKey(key)[index];
        } catch (ArrayIndexOutOfBoundsException e) {
//            System.err.println("Error: Index out of bounds for key '" + key + "'. Returning null.");
            return null;
        } catch (NullPointerException e) {
            System.err.println("Error: No frames found for key '" + key + "'. Returning null.");
            return null;
        }
    }

    /**
     * Retrieves the current frame for the given animation {@link TYPE}.
     * If the key does not exist or the frames are unavailable, a fallback to the default animation is used.
     *
     * @param type The {@link TYPE} of animation to retrieve the frame for.
     * @return The current {@link BufferedImage} frame for the specified animation triggerType, or {@code null} if an error occurs.
     */
    public final BufferedImage getFrame(TYPE type) {
        String key = type.name();
        return getFrameByKey(key);
    }

    /**
     * Retrieves the current frame for the given animation {@link TYPE} and {@link DIRECTION}.
     * This allows different frames for the same animation triggerType based on direction.
     *
     * @param type The {@link TYPE} of animation to retrieve the frame for.
     * @param direction The {@link DIRECTION} of the animation (e.g., left, right).
     * @return The current {@link BufferedImage} frame for the specified animation triggerType and direction, or {@code null} if an error occurs.
     */
    public final BufferedImage getFrame(TYPE type, DIRECTION direction) {
        String key = type.name() + "_" + direction.name();
        return getFrameByKey(key);
    }


    /**
     * Adds a new animation for the specified {@link TYPE} with a set of frames.
     *
     * @param type The {@link TYPE} of animation to add.
     * @param frames The array of {@link BufferedImage} frames for this animation.
     */
    public final Animation addAnimation(TYPE type, BufferedImage[] frames) {
        animations.put(type.name(), frames);
        return this;
    }

    /**
     * Adds a new animation for the specified {@link TYPE} and {@link DIRECTION} with a set of frames.
     *
     * @param type The {@link TYPE} of animation to add.
     * @param direction The {@link DIRECTION} of the animation (e.g., left, right).
     * @param frames The array of {@link BufferedImage} frames for this animation.
     */
    public final Animation addAnimation(TYPE type, DIRECTION direction, BufferedImage[] frames) {
        animations.put(type.name() + "_" + direction.name(), frames);
        return this;
    }

    /**
     * Sets the default animation frames. This animation will be used when no specific animation is found for a key.
     *
     * @param frames The array of {@link BufferedImage} frames for the default animation.
     * @throws IllegalArgumentException If the frames array is {@code null} or empty.
     */
    public final Animation setDefaultAnimation(BufferedImage[] frames) {
        if (frames != null && frames.length > 0) {
            defaultFrames = frames;
            animations.put(DEFAULT_KEY, frames);
        } else {
            throw new IllegalArgumentException("Default animation frames cannot be null or empty.");
        }
        return this;
    }

    /**
     * Retrieves the frames for the specified animation key. If the key does not exist, the default frames are returned.
     *
     * @param animationKey The key for the animation to retrieve frames for.
     * @return The array of {@link BufferedImage} frames for the animation.
     * @throws IllegalStateException If no frames are found for the specified key and no default animation is set.
     */
    private BufferedImage[] getFramesForKey(String animationKey) {
        if (animations.containsKey(animationKey)) {
            return animations.get(animationKey);
        } else {
            if (defaultFrames != null) {
                return defaultFrames;
            }
            throw new IllegalStateException("No animation found for key: " + animationKey + ", and no default animation is set.");
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
