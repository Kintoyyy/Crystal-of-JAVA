package Skills;

public class SkillExample2 extends Skill {

    private static final String name = "two";
    private static final int cost = 0;
    private static final int damage = 20;
    private static final String description = "Basic";
    private static final String type = "Basic";

    public SkillExample2() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
