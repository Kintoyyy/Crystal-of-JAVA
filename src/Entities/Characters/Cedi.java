package Entities.Characters;

import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Common.Mana;
import Entities.Characters.Skills.*;
import Entities.Characters.Skills.Mage.*;
import Utils.SpriteSheet;
import Utils.ImageUtils;

import java.util.ArrayList;

public class Cedi extends Character {
    public Cedi() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Cedi", 1, new Health( 800), new Mana( 200), new AttackPower(120),new Defense(10), new ArrayList<>());

        this.addSkill(new Mage(this));
        this.addSkill(new Fireball());
        this.addSkill(new ManaSurge());
        this.addSkill(new FrostBolt());
        this.addSkill(new ArcaneShield());

        SpriteSheet profile = new SpriteSheet(ImageUtils.loadImage("/Player/Cedi/Profile.png"));
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
