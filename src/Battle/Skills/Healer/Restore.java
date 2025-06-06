package Battle.Skills.Healer;

import Battle.Skills.Skill;
import Entities.Characters.Character;

public class Restore extends Skill {
    private static final String name = "Restore";
    private static final int cost = 10;
    private static final String description = "Heals a teammate";

    public Restore() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(18, 54, 18, 18);
    }

    @Override
    public void useSkill() {
        for(Character character: characters){
            character.getHealth().heal(300);
        }
    }
}
