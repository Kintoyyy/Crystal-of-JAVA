package Skills.Warrior;

import Skills.Skill;

public class WarCry extends Skill {

    private static final String name = "War Cry";
    private static final int cost = 10;
    private static final int damage = 20;
    private static final String description = "Increases team’s attack power temporarily";
    private static final String type = "warrior";

    public WarCry() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
