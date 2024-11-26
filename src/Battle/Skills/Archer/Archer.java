package Battle.Skills.Archer;

import Battle.Skills.Skill;

public class Archer extends Skill {

    private static final String name = "Basic Arrow";
    private static final int cost = 1;
    private static final String description = "Basic Arrow";

    public Archer() {
        super(name, description, cost, 0, 0);
        skillImage = sheet.crop(0, 36, 18, 18);
    }

    @Override
    public void useSkill() {
        selectedEnemy.takeDamage(selectedPlayer.getAttackPower().getDamage());
    }
}