package Entities.Characters;

import Animations.Entities.EntityAnimation;
import Entities.Common.AttackPower;
import Entities.Common.Defense;
import Entities.Common.Health;
import Entities.Common.Mana;
import Battle.Skills.Healer.*;
import Battle.Skills.Skill;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.util.ArrayList;

public class Zeith extends Character {
    public Zeith() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Zeith", 1, new Health(1000), new Mana(180), new AttackPower(90), new Defense(20), new ArrayList<>());


        this.addSkill(new Healer());
        this.addSkill(new Restore());
        this.addSkill(new DivineShield());
        this.addSkill(new Revive());
//        this.addSkill(new Blessing());

        SpriteSheet profile = new SpriteSheet(ImageUtils.loadImage("/Player/Zeith/Profile.png"));
        this.profileImage = profile.crop(0, 0, 300, 300);

        animation = new EntityAnimation(ImageUtils.loadImage("/Player/Zeith/Character.png"));
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
