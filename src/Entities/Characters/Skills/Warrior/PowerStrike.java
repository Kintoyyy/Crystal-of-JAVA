package Entities.Characters.Skills.Warrior;

import Entities.Characters.Skills.SkillType;
import Entities.Characters.Skills.Skill;

public class PowerStrike extends Skill {

    private static final String name = "Power Strike";
    private static final String description = "High single-target damage";
    private static final int cost = 20;
    private static final double damage = 1.5; // 150% of attack power
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public PowerStrike() {
        super(name, description, cost, damage, SKILL_TYPE, 2, 0);
        skillImage = sheet.crop(18, 0, 18, 18);
    }

    @Override
    public void useSkill() {
        enemy.takeDamage(player.getAttackPower() * damage);
    }
}
