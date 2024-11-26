package Entities.Enemies.Ice;

import Animations.Animation;
import Animations.Frames;
import Animations.enums.DIRECTION;
import Animations.enums.TYPE;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Enemies.Enemy;
import Utils.ImageUtils;

public class IceGoblinArcher extends Enemy {
    // Low health, high dodge rate, may steal mana.
    public IceGoblinArcher(String key) {
        super(new Health(500), new AttackPower(50), new Defense(0));
        this.key = key;
        name = "Goblin";
        description = "A generic selectedEnemy";
        dodge = 0.2; // 20% chance to dodge attacks

        Frames sheet = new Frames(ImageUtils.loadImage("/Entities/Enemies/Goblins/Goblin_Maceman.png"), 48, 48);

        animation = new Animation(sheet)
                .setDefaultAnimation(sheet.extractFrames(0, 0, 3, 1))
                .addAnimation(TYPE.IDLE, DIRECTION.DOWN, sheet.extractFrames(0, 0, 6, 1))
                .addAnimation(TYPE.IDLE, DIRECTION.LEFT, sheet.extractFrames(0, 1, 6, 2, true))
                .addAnimation(TYPE.IDLE, DIRECTION.RIGHT, sheet.extractFrames(0, 1, 6, 2))
                .addAnimation(TYPE.IDLE, DIRECTION.UP, sheet.extractFrames(0, 2, 6, 3))
                .addAnimation(TYPE.DEAD, sheet.extractFrame(3, 8));
    }
}
