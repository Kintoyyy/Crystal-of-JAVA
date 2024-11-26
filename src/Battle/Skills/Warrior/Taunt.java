package Battle.Skills.Warrior;

import Battle.Skills.Skill;

public class Taunt extends Skill {

    private static final String name = "Taunt";
    private static final int cost = 10;
    private static final String description = "Forces enemies to target Warrior for 1 turn";

    public Taunt() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(72, 0, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
