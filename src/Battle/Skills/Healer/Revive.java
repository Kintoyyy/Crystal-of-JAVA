package Battle.Skills.Healer;

import Battle.Skills.Skill;
import Entities.Characters.Character;

public class Revive extends Skill {
    private static final String name = "Revive";
    private static final int cost = 10;
    private static final String description = "Brings a fallen teammate back to life with low health";

    public Revive() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(54, 54, 18, 18);
    }

    @Override
    public void useSkill() {
        for (Character character : characters) {
            if (character.getHealth().isDead()) {
                character.getHealth().resetHealth();
                break;
            }
        }
    }
}
