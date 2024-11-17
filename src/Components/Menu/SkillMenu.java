package Components.Menu;

import Entities.Characters.Character;
import Components.Component;
import Components.Button.SkillButton;
import Game.Handler;
import Battles.BattleManager;
import Skills.Skill;

import java.awt.*;
import java.util.ArrayList;

public class SkillMenu extends Menu {
    private ArrayList<Skill> skills;
    private final BattleManager battleManager;
    private final Handler handler;

    public SkillMenu(BattleManager battleManager) {
        super();
        this.battleManager = battleManager;
        this.handler = battleManager.getHandler();
        this.skills = new ArrayList<>();
        scale(6);
        initCharacterFrames();
    }

    private void initCharacterFrames() {
        // Remove previous skill frames if any
        childComponents.clear();

        // Create a new SkillButton for each skill in the updated list
        for (Skill skill : skills) {
            SkillButton frame = (SkillButton) new SkillButton(skill)
                    .setAction(() -> {
                        skill.attack(battleManager);
                    });
            childComponents.add(frame);
        }
    }

    @Override
    public void tick() {
        // Get the updated list of skills
        ArrayList<Skill> updatedSkills = battleManager.getPlayer().getSkills();

        char[] skillKeys = {'q', 'w', 'e', 'r', 't'};

        for (int i = 0; i < updatedSkills.size(); i++) {
            if (handler.getKeyManager().isKeyPressed(String.valueOf(skillKeys[i])).ignoreCaps()) {
                SkillButton button = (SkillButton) childComponents.get(i);
                button.setActive(true);
                updatedSkills.get(i).attack(battleManager);
                break; // Only one character can be selected per tick
            }
        }

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
        Character player = battleManager.getPlayer();
        int xOffset = (int) this.x;

        // Render the skill frames
        for (Component component : childComponents) {
            if (component instanceof SkillButton frame) {
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