package Battle.Skills.Warrior;

import Battle.Skills.Skill;
import Battle.Effects.ShieldBuff;

public class ShieldBlock extends Skill {

    private static final String name = "Shield Block";
    private static final int cost = 10;
    private static final String description = "Blocks 50% of incoming damage for 1 turn";

    public ShieldBlock() {
        super(name, description, cost, 3, 1);
        skillImage = sheet.crop(36, 0, 18, 18);
    }

    @Override
    public void useSkill() {
        applyEffectToCharacters(new ShieldBuff(1, 50));
    }
}
