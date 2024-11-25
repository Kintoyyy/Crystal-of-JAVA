package Battle.Skills.Warrior;

import Battle.Skills.Skill;

public class WarCry extends Skill {

    private static final String name = "War Cry";
    private static final int cost = 15;
    private static final String description = "Increases team’s attack power temporarily";

    public WarCry() {
        super(name, description, cost, 5, 3);
        skillImage = sheet.crop(54, 0, 18, 18);
    }

    @Override
    public void useSkill() {

    }
}
