package Enemies;

import Characters.Character;
import Characters.Stats.AttackPower;
import Characters.Stats.Defense;
import Characters.Stats.Health;
import Enemies.SpecialSkill.SpecialSkill;

abstract class Enemy {
    protected Health health;
    protected AttackPower attackPower;
    protected Defense defence;
    protected SpecialSkill specialSkill;

    Enemy(Health health, AttackPower attackPower, Defense defence) {
        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;
    }

    Enemy(Health health, AttackPower attackPower, Defense defence, SpecialSkill specialSkill) {
        this.health = health;
        this.attackPower = attackPower;
        this.defence = defence;
        this.specialSkill = specialSkill;
    }

    abstract void attack(Character character);

    abstract void useSpecialSkill(Character character);

    public boolean isAlive() {
        return health.isDead();
    }
}
