package Battle.Skills.Mage;

import Battle.Skills.Skill;

public class ArcaneShield extends Skill {

    private static final String name = "Arcane Shield";
    private static final int cost = 20;
    private static final String description = "Reduces all incoming magic damage for a few turns";

    public ArcaneShield() {
        super(name, description, cost, 4, 3);
        skillImage = sheet.crop(72, 18, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
