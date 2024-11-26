package Battle.Skills.Healer;

import Battle.Skills.Skill;

public class DivineShield extends Skill {
    private static final String name = "Divine Shield";
    private static final int cost = 10;
    private static final String description = "Blocks all damage to a teammate for 1 turn";

    public DivineShield() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(36, 54, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
