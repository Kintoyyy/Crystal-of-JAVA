package Battle.Skills.Archer;

import Battle.Skills.Skill;

public class PrecisionShot extends Skill {
    private static final String name = "Precision Shot";
    private static final int cost = 10;
    private static final String description = "Ignores selectedEnemy defense";

    public PrecisionShot() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(18, 36, 18, 18);
    }

    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(randomizeDamage(300, 500));
    }
}
