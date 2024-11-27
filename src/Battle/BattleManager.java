package Battle;

import Entities.Characters.Character;
import Entities.Characters.CharacterManager;
import Battle.Effects.EffectsManager;
import Battle.Effects.DamageIndicatorManager;
import Entities.Enemies.Enemy;
import Entities.Enemies.EnemyManager;
import Entities.Entity;
import Game.Handler;
import Utils.Timer;
import Views.Overlay.BattleDialog;
import Views.enums.Views;
import Views.ViewManager;
import Worlds.Battle;

import java.util.ArrayList;

public class BattleManager {
    private final Handler handler;
    private final ViewManager viewManager;

    private final CharacterManager characterManager;
    private final EnemyManager enemyManager;

    private final EffectsManager effectsManager;
    private final DamageIndicatorManager damageIndicatorManager;

    private final Timer timer = new Timer();
    private boolean isDataLoaded = false;
    private boolean isPlayersTurn = true;

    public BattleManager(Handler handler) {
        handler.setBattleManager(this);
        this.handler = handler;

        this.effectsManager = new EffectsManager();
        this.damageIndicatorManager = new DamageIndicatorManager();
        this.enemyManager = new EnemyManager(handler);
        this.characterManager = handler.getCharacterManager();

        this.viewManager = handler.getViewManager();
    }

    public void startBattle(Battle battle) {
        //set battle
        handler.getWorldManager().getCurrentWorld().setCurrentBattle(battle);

        if (battle.isComplete()) {
            System.out.println("Battle already completed: " + battle.getKey());
            abortBattle();
            return;
        }

        if (!battle.getPreBattleDialogs().isEmpty()) {
            viewManager.customView(new BattleDialog(battle.getPreBattleDialogs(), () -> {
                loadBattleView(battle);
            }));
            return;
        }
        loadBattleView(battle);
    }

    private void loadBattleView(Battle battle) {

        handler.getWorldManager().getCurrentWorld().setCurrentBattle(battle);
        enemyManager.loadEnemies(battle.getEnemies());
        this.isDataLoaded = false;
        viewManager.setView(Views.BATTLE);
    }

    public void enemiesTurn() {
        isPlayersTurn = false;
        System.out.println("Enemies turn");
        timer.reset();
        timer.start().setDelay(1).setAction(() -> {

            // Use the new selectTarget method
            Enemy currentEnemy = getCurrentEnemy();
            Character target = selectTarget(characterManager.getCharacters(), !characterHaveHalfHealth() ? "random" : "lowest");

            if (target == null) {
                System.out.println("No valid target for the enemy!");
                return;
            }

            if (currentEnemy.getHealth().isDead()) {
                setCurrentEnemy(enemyManager.getNextEnemyIndex());
            }

            if (!currentEnemy.getHealth().isDead()) {
                double damage = currentEnemy.attack(target);

                if (damage == -1) {
                    damageIndicatorManager.addDodgeIndicator((float) target.getDisplayX(), (float) (target.getDisplayY() - 20));
                } else {
                    damageIndicatorManager.addDamageIndicator(damage, (float) target.getDisplayX(), (float) (target.getDisplayY() - 20));
                }
            }

            isPlayersTurn = true;
            characterManager.updateTurns();
        });
    }

    public boolean characterHaveHalfHealth() {
        for (Character character : characterManager.getCharacters()) {
            if (character.getHealth().getHealth() <= character.getHealth().getBaseHealth() / 2 && !character.getHealth().isDead()) {
                return true;
            }
        }
        return false;
    }

    public <T extends Entity> T selectTarget(ArrayList<T> targets, String strategy) {
        if (targets.isEmpty()) {
            System.out.println("No targets available!");
            return null;
        }

        switch (strategy.toLowerCase()) {
            case "random":

                ArrayList<Integer> aliveTarget = new ArrayList<>();
                for (T target : targets) {
                    if (!target.getHealth().isDead()) {
                        aliveTarget.add(targets.indexOf(target));
                    }
                }

                System.out.println("Alive targets: " + aliveTarget);

                int randomIndex = (int) (Math.random() * aliveTarget.size());
                System.out.println("Randomly selected target at index: " + randomIndex);
                return targets.get(aliveTarget.get(randomIndex));

            case "lowest":
                T lowestHealthTarget = targets.get(0);
                for (T target : targets) {
                    if (target.getHealth().getHealth() < lowestHealthTarget.getHealth().getHealth()) {
                        lowestHealthTarget = target;
                    }
                }
                System.out.println("Selected target with lowest health: " + lowestHealthTarget);
                return lowestHealthTarget;

            default:
                System.out.println("Invalid strategy: " + strategy);
                return null;
        }
    }

    public void tick() {
        timer.update(); // Update the timer regardless
        damageIndicatorManager.update();
        // Check if all enemies are dead

        if (enemyManager.isAllEnemiesDead() && !timer.isActive()) {
            Battle currentBattle = handler.getWorldManager().getCurrentWorld().getCurrentBattle();
            if (!currentBattle.getPostBattleDialogs().isEmpty()) {
                viewManager.customView(new BattleDialog(currentBattle.getPostBattleDialogs(), () -> {
                    viewManager.setView(Views.GAME);
                }));
            } else {
                viewManager.setView(Views.GAME);
            }
        }

        if (enemyManager.isEmpty()) {
            System.out.println("Battle ended");
            viewManager.setView(Views.GAME);
            return;
        }
    }

    // TODO: need to move this
    public Handler getHandler() {
        return handler;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemyManager.getEnemies();
    }

    // for ui
    public boolean isDataLoaded() {
        return isDataLoaded;
    }

    public void setDataLoaded(boolean isDataLoaded) {
        this.isDataLoaded = isDataLoaded;
    }

    public void abortBattle() {
        viewManager.setView(Views.GAME);
        enemyManager.clearEnemies();
    }

    public void setCurrentEnemy(int index) {

        if (index < 0 || index >= enemyManager.getEnemies().size()) {
            System.out.println("Invalid enemy index: " + index);
            return;
        }

        if (enemyManager.getEnemies().get(index).getHealth().isDead()) {
            System.out.println("Enemy is already dead");

            return;
        }

        System.out.println("Enemy Selected: " + index);
        enemyManager.setCurrentEnemy(index);
    }

    // get current selectedEnemy
    public Enemy getCurrentEnemy() {
        return enemyManager.getCurrentEnemy();
    }

    //for debugging only
    public void killAllEnemies() {
        enemyManager.killAllEnemies();
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public Double getTimer() {
        return timer.getTime();
    }

    public DamageIndicatorManager getDamageIndicatorManager() {
        return damageIndicatorManager;
    }
}
