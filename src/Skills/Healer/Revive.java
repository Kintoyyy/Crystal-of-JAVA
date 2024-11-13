package Skills.Healer;

import Skills.Skill;
import Skills.SkillType;

public class Revive extends Skill {
    private static final String name = "Revive";
    private static final int cost = 10;
    private static final int damage = 10;
    private static final String description = "Brings a fallen teammate back to life with low health";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public Revive() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(54, 54, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
