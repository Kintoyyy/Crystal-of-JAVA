package Animations;

import Animations.enums.DIRECTION;
import Animations.enums.TYPE;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Animation {

    protected int speed = 120;
    private final HashMap<String, BufferedImage[]> animations = new HashMap<>();
    private String key;
    private long lastTime, timer;
    private int index;
    private int width, height;

    private static final String DEFAULT_KEY = "DEFAULT";
    private BufferedImage[] defaultFrames;

    public Animation(Frames sheet) {
        this.height = sheet.getHeight();
        this.width = sheet.getWidth();
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    public final void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        // Get the current animation frames
        BufferedImage[] frames = getFramesForKey(key);

        // If only one frame exists, ensure the index remains 0
        if (frames.length == 1) {
            index = 0;
            return; // No need to update further
        }

        // Update the index if the timer exceeds the speed
        if (timer > speed) {
            timer = 0;
            index++;
            if (index >= frames.length) {
                index = 0; // Loop the animation
            }
        }
    }

    public BufferedImage getFrameByKey(String key) {
        try {
            BufferedImage[] frames = getFramesForKey(key);

            // If there is only one frame, return it directly without using the index
            if (frames.length == 1) {
                return frames[0]; // Return the single frame directly
            }

            // Return the frame based on the current index
            return frames[index];

        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the ArrayIndexOutOfBoundsException, which occurs when index is out of bounds
            System.err.println("Error: Index out of bounds for key '" + key + "'. Index: " + index);
            return null; // Return null or some fallback frame

        } catch (NullPointerException e) {
            // Handle NullPointerException when frames are null
            System.err.println("Error: No frames found for key '" + key + "'.");
            return null; // Return null or fallback frame

        } catch (Exception e) {
            // Catch any other exceptions
            System.err.println("Unexpected error while retrieving frame for key '" + key + "'. " + e.getMessage());
            e.printStackTrace(); // Log the stack trace for debugging
            return null; // Return null or fallback frame
        }
    }

    public final BufferedImage getFrame(TYPE type) {
        String key = type.name();
        return getFrameByKey(key);
    }

    public final BufferedImage getFrame(TYPE type, DIRECTION direction) {
        String key = type.name() + "_" + direction.name();
        return getFrameByKey(key);
    }

    public final Animation addAnimation(TYPE type, BufferedImage[] frames) {
        animations.put(type.name(), frames);
        return this;
    }

    public final Animation addAnimation(TYPE type, DIRECTION direction, BufferedImage[] frames) {
        animations.put(type.name() + "_" + direction.name(), frames);
        return this;
    }

    public final Animation setDefaultAnimation(BufferedImage[] frames) {
        if (frames != null && frames.length > 0) {
            defaultFrames = frames;
            animations.put(DEFAULT_KEY, frames);
        } else {
            throw new IllegalArgumentException("Default animation frames cannot be null or empty.");
        }
        return this;
    }

    private BufferedImage[] getFramesForKey(String animationKey) {
        if (animations.containsKey(animationKey)) {
            return animations.get(animationKey);
        } else if (defaultFrames != null) {
            return defaultFrames;
        }
        throw new IllegalStateException("No animation found for key: " + animationKey + ", and no default animation is set.");
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
