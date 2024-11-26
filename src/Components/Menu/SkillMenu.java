package Components.Menu;

import Components.ComponentEventListener;
import Components.UIComponent;
import Entities.Characters.Character;
import Components.Button.SkillButton;
import Battle.BattleManager;
import Battle.Skills.Skill;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SkillMenu extends Menu {
    private ArrayList<Skill> skills;
    private final BattleManager battleManager;

    public SkillMenu(BattleManager battleManager) {
        super();
        this.battleManager = battleManager;
        this.skills = handler.getCharacterManager().getPlayer().getSkills();
        initCharacterFrames();
    }

    private void initCharacterFrames() {
        // Remove previous skill frames if any
        children.clear();

        // Create a new SkillButton for each skill in the updated list
        for (Skill skill : skills) {
            SkillButton frame = (SkillButton) new SkillButton(skill)
                    .setEventListener(new ComponentEventListener() {
                        @Override
                        public void onComponentClick(MouseEvent event) {
                            skill.execute(battleManager);
                        }

                        @Override
                        public void onMouseEnter(MouseEvent event) {

                        }

                        @Override
                        public void onMouseExit(MouseEvent event) {

                        }
                    });
            children.add(frame);
        }
    }

    @Override
    public void tick() {
        // Get the updated list of skills
        ArrayList<Skill> updatedSkills = battleManager.getCharacterManager().getPlayer().getSkills();

        char[] skillKeys = {'q', 'w', 'e', 'r', 't'};

        for (int i = 0; i < updatedSkills.size(); i++) {
            if (handler.getKeyManager().isKeyPressed(String.valueOf(skillKeys[i])).ignoreCaps()) {
                SkillButton button = (SkillButton) children.get(i);
                button.setActive(true);
//                updatedSkills.get(i).attack(battleManagerOld);
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
        children.forEach(UIComponent::tick);
    }

    @Override
    public void render(Graphics g) {
        Character player = battleManager.getCharacterManager().getPlayer();
        int xOffset = (int) this.x;

        // Render the skill frames
        for (UIComponent UIComponent : children) {
            if (UIComponent instanceof SkillButton frame) {
                frame.setLocation(xOffset, (int) this.y);
                xOffset += frame.getWidth() + 40;
            }
            UIComponent.render(g);
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        children.forEach(component -> component.onClick(e));
    }
}