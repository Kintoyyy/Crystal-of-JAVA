package Components.Menu;

import Characters.Character;
import Components.Component;
import Components.Frame.SkillFrame;
import Game.Handler;
import Skills.Skill;

import java.awt.*;
import java.util.ArrayList;

public class BattleSkillMenu extends Menu {
    private ArrayList<Skill> skills;
    private final Handler handler;

    public BattleSkillMenu(Handler handler) {
        super();
        this.handler = handler;
        this.skills = new ArrayList<>();
        scale(6);
        initCharacterFrames();
    }

    private void initCharacterFrames() {
        // Remove previous skill frames if any
        childComponents.clear();

        // Create a new SkillFrame for each skill in the updated list
        for (Skill skill : skills) {
            SkillFrame frame = (SkillFrame) new SkillFrame(skill)
                    .setAction(() -> {
                        System.out.println("Selected Skill: " + skill.getName());
                    });
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
        // Get the updated list of skills
        ArrayList<Skill> updatedSkills = handler.getGameState().getPlayer().getSkills();

        // Check if skills have changed
        if (!skills.equals(updatedSkills)) {
            // If they have, update the list and recreate the skill frames
            skills = updatedSkills;
            initCharacterFrames();  // Reinitialize skill frames
        }

        // Update child components
        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        Character player = handler.getGameState().getPlayer();
        int xOffset = (int) this.x;

        // Render the skill frames
        for (Component component : childComponents) {
            if (component instanceof SkillFrame frame) {
                frame.setLocation(xOffset, (int) this.y);
                xOffset += frame.getWidth();
            }
            component.render(g);
        }
    }

    @Override
    public void onClick() {
        childComponents.forEach(Component::onClick);
    }
}