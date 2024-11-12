package Characters;

import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Characters.Stats.Mana;
import Skills.*;
import Skills.Mage.ArcaneShield;
import Skills.Mage.Fireball;
import Skills.Mage.FrostBolt;
import Skills.Warrior.PowerStrike;
import Skills.Warrior.ShieldBlock;
import Utils.SpriteSheet;
import Utils.ImageUtils;

import java.util.ArrayList;

public class Cedi extends Character {
    public Cedi() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Cedi", 1, new Health( 80), new Mana( 120), new AttackPower(10),new Defense(4), new ArrayList<>());

        this.addSkill(new Fireball());
        this.addSkill(new ShieldBlock());
        this.addSkill(new FrostBolt());
        this.addSkill(new ArcaneShield());

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
