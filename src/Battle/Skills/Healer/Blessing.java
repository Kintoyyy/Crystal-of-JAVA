package Battle.Skills.Healer;

import Battle.Skills.Skill;
import Entities.Characters.Character;

public class Blessing extends Skill {
    private static final String name = "Blessing";
    private static final int cost = 10;
    private static final String description = "Boosts team’s health regen for a few turns";

    public Blessing() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(72, 54, 18, 18);
    }

    @Override
    public void useSkill() {
        for (Character character : characters) {
            character.getHealth().heal(30);
        }
    }
}
