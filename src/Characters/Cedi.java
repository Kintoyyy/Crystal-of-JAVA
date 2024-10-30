package Characters;

import Utils.SpriteSheet;
import Skills.Basic;
import Skills.Healing;
import Skills.Skill;
import Utils.ImageUtils;

import java.util.ArrayList;

public class Cedi extends Character {

    private String name = "Cedi";
    private String description = "";
    private int level = 1;
    private int health = 100;
    private int mana = 100;
    private int baseHealth = 100;
    private ArrayList<Skill> skills = new ArrayList<>();

    public Cedi() {
        // Pass all parameters, including playerSheet, to the superclass constructor
        super("Cedi", 1, 100, 100, 100, new ArrayList<>());

        this.spriteSheet = new SpriteSheet(ImageUtils.loadImage("/textures/Player_Idle_Run_Death_Anim.png"));

        this.skills.add(new Basic());
        this.skills.add(new Healing());
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
