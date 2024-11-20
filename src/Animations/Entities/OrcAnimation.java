package Animations.Entities;

import Animations.Animation;
import Animations.Frames;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;

import java.awt.image.BufferedImage;

public class OrcAnimation extends Animation {

    public OrcAnimation(BufferedImage image) {
        super();

        Frames sheet = new Frames(image, 32, 32);

        this.setDefaultAnimation(sheet.extractFrames(0, 0, 4, 1));
        this.addAnimation(TYPE.IDLE, DIRECTION.DOWN, sheet.extractFrames(0, 0, 4, 1));
        this.addAnimation(TYPE.IDLE, DIRECTION.LEFT, sheet.extractFrames(0, 1, 4, 2, true));
        this.addAnimation(TYPE.IDLE, DIRECTION.RIGHT, sheet.extractFrames(0, 1, 4, 2));
        this.addAnimation(TYPE.IDLE, DIRECTION.UP, sheet.extractFrames(0, 2, 4, 3));

        this.addAnimation(TYPE.WALK, DIRECTION.DOWN, sheet.extractFrames(0, 3, 6, 4));
        this.addAnimation(TYPE.WALK, DIRECTION.LEFT, sheet.extractFrames(0, 4, 6, 5, true));
        this.addAnimation(TYPE.WALK, DIRECTION.RIGHT, sheet.extractFrames(0, 4, 6, 5));
        this.addAnimation(TYPE.WALK, DIRECTION.UP, sheet.extractFrames(0, 5, 6, 6));

        this.addAnimation(TYPE.ATTACK, DIRECTION.DOWN, sheet.extractFrames(0, 6, 6, 7));
        this.addAnimation(TYPE.ATTACK, DIRECTION.LEFT, sheet.extractFrames(0, 7, 6, 8, true));
        this.addAnimation(TYPE.ATTACK, DIRECTION.RIGHT, sheet.extractFrames(0, 7, 6, 8));
        this.addAnimation(TYPE.ATTACK, DIRECTION.UP, sheet.extractFrames(0, 8, 6, 9));

        this.addAnimation(TYPE.SLEEP, DIRECTION.LEFT, sheet.extractFrames(0, 9, 5, 10, true));
        this.addAnimation(TYPE.SLEEP, DIRECTION.RIGHT, sheet.extractFrames(0, 9, 5, 10));

        this.addAnimation(TYPE.GHOST, DIRECTION.DOWN, sheet.extractFrames(0, 10, 4, 11));
        this.addAnimation(TYPE.GHOST, DIRECTION.LEFT, sheet.extractFrames(0, 11, 4, 12, true));
        this.addAnimation(TYPE.GHOST, DIRECTION.RIGHT, sheet.extractFrames(0, 11, 4, 12));
        this.addAnimation(TYPE.GHOST, DIRECTION.UP, sheet.extractFrames(0, 12, 4, 13));
    }
}
