package Entities.Characters;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Common.Mana;
import Skills.*;
import Skills.Archer.*;
import Utils.SpriteSheet;
import Utils.ImageUtils;

import java.util.ArrayList;

public class Nathan extends Character {
    public Nathan() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Nathan", 1, new Health(900), new Mana(120), new AttackPower(140), new Defense(15), new ArrayList<>());

        this.addSkill(new Archer(this));
        this.addSkill(new PrecisionShot());
        this.addSkill(new MultiShot());
        this.addSkill(new PoisonArrow());
        this.addSkill(new Evasion());

        SpriteSheet profile = new SpriteSheet(ImageUtils.loadImage("/Player/Nathan/Profile.png"));
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
