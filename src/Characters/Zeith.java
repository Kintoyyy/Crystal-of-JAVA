package Characters;

import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Characters.Stats.Mana;
import Skills.Healer.Blessing;
import Skills.Healer.DivineShield;
import Skills.Healer.Restore;
import Skills.Healer.Revive;
import Skills.Skill;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.util.ArrayList;

public class Zeith extends Character {
    public Zeith() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Zeith", 1, new Health(75), new Mana(100), new AttackPower(12), new Defense(5), new ArrayList<>());

        this.skills.add(new Restore());
        this.skills.add(new DivineShield());
        this.skills.add(new Revive());
        this.skills.add(new Blessing());

        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Player_Idle_Run_Death_Anim.png"));
    }

    @Override
    public void useSkill(int index) {
        if (index >= 0 && index < skills.size()) {
            Skill skill = skills.get(index);
            // Logic for using the skill can go here
        }
    }

    @Override
    public void useSkill(Skill skill) {
        // Logic for using a specific skill can go here
    }
}
