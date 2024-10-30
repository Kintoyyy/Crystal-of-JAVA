package Skills;

public class Basic extends Skill {

    private static final String name = "Basic";
    private static final int cost = 0;
    private static final int damage = 20;
    private static final String description = "Basic";
    private static final String type = "Basic";

    public Basic() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
