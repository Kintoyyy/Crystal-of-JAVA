package Skills;


public class Basic extends Skill {

    private static final String name = "Basic";
    private static final int cost = 0;
    private static final int damage = 10;
    private static final String description = "Basic attack";
    private static final String type = "all";

    public Basic() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}