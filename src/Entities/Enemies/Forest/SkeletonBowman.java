package Entities.Enemies.Forest;

import Animations.Animation;
import Animations.Frames;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Enemies.Enemy;
import Utils.ImageUtils;

public class SkeletonBowman extends Enemy {
    // Moderate health, high defense, low attack, immune to poison.
    public SkeletonBowman(String key) {
        super(new Health(600), new AttackPower(100, 200), new Defense(40));
        this.key = key;
        name = "Bowman";
        description = "A generic selectedEnemy";

        setDialogLines(
                "You have encountered a Skeleton Boss!",
                "Prepare for battle!",
                "Defeat the Skeleton Boss to continue!",
                "Good luck!",
                "Skeleton Boss: I will defeat you!",
                "Skeleton Boss: You will never defeat me!"
        );

        Frames sheet = new Frames(ImageUtils.loadImage("/Maps/Forest/Skeleton_Bowman.png"), 32, 32);

        animation = new Animation(sheet)
                .setDefaultAnimation(sheet.extractFrames(0, 0, 6, 1))
                .addAnimation(TYPE.IDLE, DIRECTION.DOWN, sheet.extractFrames(0, 0, 6, 1))
                .addAnimation(TYPE.IDLE, DIRECTION.LEFT, sheet.extractFrames(0, 1, 6, 2, true))
                .addAnimation(TYPE.IDLE, DIRECTION.RIGHT, sheet.extractFrames(0, 1, 6, 2))
                .addAnimation(TYPE.IDLE, DIRECTION.UP, sheet.extractFrames(0, 2, 6, 3))
                .addAnimation(TYPE.DEAD, sheet.extractFrame(3, 9));
    }
}
