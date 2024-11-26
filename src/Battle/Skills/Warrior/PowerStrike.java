package Battle.Skills.Warrior;

import Battle.Skills.Skill;

public class PowerStrike extends Skill {

    private static final String name = "Power Strike";
    private static final String description = "High single-target damage";
    private static final int cost = 20;

    public PowerStrike() {
        super(name, description, cost, 2, 0);
        skillImage = sheet.crop(18, 0, 18, 18);
    }


    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(randomizeDamage(300, 450));
    }
}
