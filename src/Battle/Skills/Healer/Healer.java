package Battle.Skills.Healer;

import Battle.Skills.Skill;

public class Healer extends Skill {

    private static final String name = "Basic Attack";
    private static final int cost = 1;
    private static final String description = "Basic attack";

    public Healer() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(0, 54, 18, 18);
    }

    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(selectedPlayer.getAttackPower().getDamage());
    }
}