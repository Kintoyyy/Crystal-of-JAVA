package Skills;

public class SkillExample3 extends Skill {

    private static final String name = "three";
    private static final int cost = 0;
    private static final int damage = 20;
    private static final String description = "Basic";
    private static final String type = "Basic";

    public SkillExample3() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
