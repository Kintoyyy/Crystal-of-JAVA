package Battle.Skills.Mage;

import Battle.Skills.Skill;

public class ManaSurge extends Skill {

    private static final String name = "Mana Surge";
    private static final int cost = 0;
    private static final String description = "Restores some mana";

    public ManaSurge() {
        super(name, description, cost, 3, 0);
        skillImage = sheet.crop(36, 18, 18, 18);
    }

    @Override
    public void useSkill() {
        // adds 30 mana to the player
        selectedPlayer.getMana().addMana(30);
    }
}
