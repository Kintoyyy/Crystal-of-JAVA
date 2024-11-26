package Battle.Skills.Mage;

import Battle.Skills.Skill;

public class Mage extends Skill {

    private static final String name = "Basic Magic";
    private static final int cost = 1;
    private static final String description = "Basic Magic";

    public Mage() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(0, 18, 18, 18);
    }

    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(selectedPlayer.getAttackPower().getDamage());
    }
}