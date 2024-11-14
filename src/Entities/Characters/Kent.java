package Entities.Characters;

import Entities.Common.*;
import Skills.Warrior.*;
import Utils.SpriteSheet;
import Skills.*;
import Utils.ImageUtils;

import java.util.ArrayList;

public class Kent extends Character {

    public Kent() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Kent", 1, new Health(1200), new Mana(100), new AttackPower(150, AttackType.PHYSICAL), new Defense(30), new ArrayList<>());

        this.addSkill(new Warrior(this));
        this.addSkill(new PowerStrike());
        this.addSkill(new ShieldBlock());
        this.addSkill(new WarCry());
        this.addSkill(new Taunt());

        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Player_Idle_Run_Death_Anim.png"));

        SpriteSheet profile = new SpriteSheet(ImageUtils.loadImage("/Player/Kent/Profile.png"));
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
