package Battle.Skills.Warrior;

import Battle.Skills.Skill;

public class Warrior extends Skill {

    private static final String name = "Basic Strike";
    private static final int cost = 1;
    private static final String description = "Basic Strike";

    public Warrior() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(0, 0, 18, 18);
    }

    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(selectedPlayer.getAttackPower().getDamage());
    }
}