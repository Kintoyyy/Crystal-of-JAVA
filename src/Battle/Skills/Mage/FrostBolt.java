package Battle.Skills.Mage;

import Battle.Skills.Skill;

public class FrostBolt extends Skill {

    private static final String name = "Frost Bolt";
    private static final int cost = 10;
    private static final String description = "Slows selectedEnemy and deals damage";

    public FrostBolt() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(54, 18, 18, 18);
    }

    @Override
    public void useSkill() {
        // enemy takes 500 damage
        selectedEnemy.takeDamage(500);
    }
}
