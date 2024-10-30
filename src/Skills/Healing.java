package Skills;

public class Healing extends Skill {
    private static final String name = "Healing";
    private static final int cost = 10;
    private static final int damage = 0;
    private static final String description = "Healing";
    private static final String type = "Healing";

    public Healing() {
        super(name, description, cost, damage, type);
    }

    @Override
    public void useSkill() {

    }
}
