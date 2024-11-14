package Entities.Characters;

import Entities.Characters.Stats.AttackPower;
import Entities.Characters.Stats.Defense;
import Entities.Characters.Stats.Health;
import Entities.Characters.Stats.Mana;
import Skills.Healer.*;
import Skills.Skill;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.util.ArrayList;

public class Zeith extends Character {
    public Zeith() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Zeith", 1, new Health(1000), new Mana(180), new AttackPower(90), new Defense(20), new ArrayList<>());


        this.addSkill(new Healer(this));
        this.addSkill(new Restore());
        this.addSkill(new DivineShield());
        this.addSkill(new Revive());
        this.addSkill(new Blessing());

        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Player_Idle_Run_Death_Anim.png"));

        SpriteSheet profile = new SpriteSheet(ImageUtils.loadImage("/Player/Zeith/Profile.png"));
        this.profileImage = profile.crop(0, 0, 300, 300);
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
