package Battle.Skills.Archer;

import Battle.Skills.Skill;

public class Evasion extends Skill {
    private static final String name = "Evasion";
    private static final int cost = 10;
    private static final String description = "Increases dodge chance for a few turns";

    public Evasion() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(72, 36, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
