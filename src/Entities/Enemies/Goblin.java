package Entities.Enemies;

import Animations.Entities.EntityAnimation;
import Animations.Entities.GoblinAnimation;
import Animations.Entities.OrcAnimation;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Utils.ImageUtils;

public class Goblin extends Enemy {
    // Low health, high dodge rate, may steal mana.
    public Goblin() {
        super(new Health(500), new AttackPower(50), new Defense(0));
        name = "Goblin";
        description = "A generic selectedEnemy";
        dodge = 0.2; // 20% chance to dodge attacks

        animation = new OrcAnimation(ImageUtils.loadImage("/Entities/Enemies/Goblins/Goblin_Maceman.png"));
    }
}
