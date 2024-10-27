package Skills;

public class Healing extends Skill {
    private static String name = "Healing";
    private static int cost = 10;
    private static int damage = 0;
    private static String description = "Healing";
    private static String type = "Healing";

    public Healing() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
