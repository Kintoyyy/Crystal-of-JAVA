package Components.Menu;

import Components.Button.RoundedButton;
import Components.Component;
import Game.Handler;
import Skills.Skill;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkillMenu extends Menu {
    private Skill currentSkill;
    private List<Skill> skills;
    private static final int BUTTON_SPACING = 10; // Spacing between buttons
    private final Handler handler;

    public SkillMenu(Handler handler) {
        super();
        this.handler = handler;
        this.skills = new ArrayList<>(handler.getGameState().getPlayer().getSkills());
        initSkillFrames();
    }

    private void initSkillFrames() {
        childComponents.clear(); // Clear existing components before re-adding

        for (Skill skill : skills) {
            RoundedButton frame = (RoundedButton) new RoundedButton(skill.getName())
                    .hideText()
                    .setAction(() -> {
                        currentSkill = skill;
                        System.out.println("Selected Skill: " + currentSkill.getName());
                    });
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
        // Only update skills if they have changed
        List<Skill> newSkills = handler.getGameState().getPlayer().getSkills();

        if (!newSkills.equals(this.skills)) {
            this.skills = new ArrayList<>(newSkills);
            initSkillFrames();
        }
        // Tick each component
        childComponents.forEach(Component::tick);
    }

    @Override
    public void render(Graphics g) {
        int xOffset = (int) this.x;
        int yOffset = (int) this.y;

        if (skills.size() == 4) {
            // Arrange in a 2x2 grid if there are exactly 4 skills
            int col = 0, row = 0;
            for (Component component : childComponents) {
                if (component instanceof RoundedButton frame) {
                    frame.setLocation(xOffset + col * (200 + BUTTON_SPACING),
                            yOffset + row * (60 + BUTTON_SPACING));
                    col++;
                    if (col >= 2) { // Move to the next row after two columns
                        col = 0;
                        row++;
                    }
                }
                component.render(g);
            }
        } else {
            // Default to a single row layout
            for (Component component : childComponents) {
                if (component instanceof RoundedButton frame) {
                    frame.setLocation(xOffset, yOffset);
                    xOffset += frame.getWidth() + BUTTON_SPACING;
                }
                component.render(g);
            }
        }
    }

    @Override
    public void onClick() {
        childComponents.forEach(Component::onClick);
    }
}
