package Animations.Entities;

import Animations.Animation;
import Animations.Frames;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;

import java.awt.image.BufferedImage;

public class EntityAnimation extends Animation {

    public EntityAnimation(BufferedImage image) {
        super();

        Frames sheet = new Frames(image, 32, 32);

        this.setDefaultAnimation(sheet.extractFrames(0, 0, 6, 1));

        this.addAnimation(TYPE.IDLE, DIRECTION.DOWN, sheet.extractFrames(0, 0, 6, 1));
        this.addAnimation(TYPE.IDLE, DIRECTION.LEFT, sheet.extractFrames(0, 1, 6, 2, true));
        this.addAnimation(TYPE.IDLE, DIRECTION.RIGHT, sheet.extractFrames(0, 1, 6, 2));
        this.addAnimation(TYPE.IDLE, DIRECTION.UP, sheet.extractFrames(0, 2, 6, 3));

        this.addAnimation(TYPE.WALK, DIRECTION.DOWN, sheet.extractFrames(0, 3, 6, 4));
        this.addAnimation(TYPE.WALK, DIRECTION.LEFT, sheet.extractFrames(0, 4, 6, 5, true));
        this.addAnimation(TYPE.WALK, DIRECTION.RIGHT, sheet.extractFrames(0, 4, 6, 5));
        this.addAnimation(TYPE.WALK, DIRECTION.UP, sheet.extractFrames(0, 5, 6, 6));

        this.addAnimation(TYPE.SLEEP, DIRECTION.LEFT, sheet.extractFrames(0, 6, 5, 7, true));
        this.addAnimation(TYPE.SLEEP, DIRECTION.RIGHT, sheet.extractFrames(0, 6, 5, 7));

        this.addAnimation(TYPE.GHOST, DIRECTION.DOWN, sheet.extractFrames(0, 7, 4, 8));
        this.addAnimation(TYPE.GHOST, DIRECTION.LEFT, sheet.extractFrames(0, 8, 4, 9, true));
        this.addAnimation(TYPE.GHOST, DIRECTION.RIGHT, sheet.extractFrames(0, 8, 4, 9));
        this.addAnimation(TYPE.GHOST, DIRECTION.UP, sheet.extractFrames(0, 9, 4, 10));

        this.addAnimation(TYPE.TIRED, DIRECTION.DOWN, sheet.extractFrames(0, 11, 6, 12));
        this.addAnimation(TYPE.TIRED, DIRECTION.LEFT, sheet.extractFrames(0, 12, 6, 13, true));
        this.addAnimation(TYPE.TIRED, DIRECTION.RIGHT, sheet.extractFrames(0, 12, 6, 13));

        this.addAnimation(TYPE.DANCE, sheet.extractFrames(0, 12, 8, 13));
    }
}
