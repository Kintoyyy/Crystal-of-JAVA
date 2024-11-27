package Battle.Skills;

import Entities.Characters.Character;
import Battle.Effects.Effect;
import Entities.Common.AttackPower;
import Entities.Enemies.Enemy;
import Battle.BattleManager;
import Utils.ImageUtils;
import Utils.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Skill {
    private final String name;
    private final String description;
    private final int cost;

    private int cooldown = 0;
    private int currentCooldown = 0;

    private int duration = 0;

    protected SpriteSheet sheet = new SpriteSheet(ImageUtils.loadImage("/ui/Skills.png"));
    protected BufferedImage skillImage;

    protected Enemy selectedEnemy;
    protected Character selectedPlayer;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Character> characters;

    public Skill(String name, String description, int cost, int cooldown, int duration) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.cooldown = cooldown;
        this.duration = duration;
        this.currentCooldown = 0;
    }

    protected void applyEffectToCharacters(Effect effect) {
        for (Character character : characters) {
            character.addEffect(effect);
        }
    }

    protected void applyEffectToEnemies(Effect effect) {
        for (Enemy enemy : enemies) {
            enemy.addEffect(effect);
        }
    }

    protected void applyEffectToEnemy(Effect effect) {
        selectedEnemy.addEffect(effect);
    }

    protected void applyEffectToCharacter(Effect effect) {
        selectedPlayer.addEffect(effect);
    }

    public boolean isAvailable() {
        return currentCooldown == 0;
    }

    public void applyCooldown() {
        currentCooldown = cooldown;
    }

    public void reduceCooldown() {
        if (currentCooldown > 0) currentCooldown--;
    }

    public void execute(BattleManager battleManager) {
        this.selectedEnemy = battleManager.getCurrentEnemy();
        this.enemies = battleManager.getEnemies();
        this.selectedPlayer = battleManager.getCharacterManager().getPlayer();
        this.characters = battleManager.getCharacterManager().getCharacters();

        if(selectedPlayer.getHealth().isDead()) return;

        if(selectedPlayer.getMana().getMana() < cost) return;

        if(!battleManager.isPlayersTurn()) return;

        if (isAvailable()) {
            selectedPlayer.getMana().useMana(cost);
            if(selectedPlayer.getHealth().isDead()) return;
            applyCooldown();

            //get ActiveEffects first adjust Skill damage or attributes
            useSkill();

            // call enemy turn
            battleManager.enemiesTurn();
        }
    }

    public int getCooldown() {
        return currentCooldown;
    }


    public BufferedImage getSkillImage() {
        return skillImage;
    }

    public String getName() {
        return name;
    }

    public void setCharacter(Character player) {
        this.selectedPlayer = player;
    }

    public int getCost() {
        return cost;
    }

    public abstract void useSkill();

    protected int randomizeDamage(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}