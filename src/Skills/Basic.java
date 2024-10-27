package Skills;

public class Basic extends Skill {

    private static String name = "Basic";
    private static int cost = 0;
    private static int damage = 20;
    private static String description = "Basic";
    private static String type = "Basic";

    public Basic() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
