package Characters;

import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Characters.Stats.Mana;
import Skills.*;
import Skills.Archer.Evasion;
import Skills.Archer.MultiShot;
import Skills.Archer.PoisonArrow;
import Skills.Archer.PrecisionShot;
import Skills.Warrior.ShieldBlock;
import Skills.Warrior.WarCry;
import Utils.SpriteSheet;
import Utils.ImageUtils;

import java.util.ArrayList;

public class Nathan extends Character {
    public Nathan() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Nathan", 1, new Health( 90), new Mana(60),new AttackPower(12), new Defense(6), new ArrayList<>());

        this.skills.add(new PrecisionShot());
        this.skills.add(new MultiShot());
        this.skills.add(new PoisonArrow());
        this.skills.add(new Evasion());

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
