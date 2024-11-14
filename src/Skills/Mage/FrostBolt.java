package Skills.Mage;

import Skills.Skill;
import Skills.SkillType;

public class FrostBolt extends Skill {

    private static final String name = "Frost Bolt";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Slows enemy and deals damage";
    private static final SkillType SKILL_TYPE = SkillType.PHYSICAL;

    public FrostBolt() {
        super(name, description, cost, damage, SKILL_TYPE);
        skillImage = sheet.crop(54, 18, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
