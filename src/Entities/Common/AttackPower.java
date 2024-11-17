package Entities.Common;

public class AttackPower {
    private double attackPower;
    private final double minAttackPower;
    private final AttackType attackType;

    public AttackPower(int attackPower) {
        this(attackPower, attackPower);
    }

    public AttackPower(int attackPower, AttackType attackType) {
        this.attackType = attackType;
        this.attackPower = attackPower;
        this.minAttackPower = attackPower;
    }

    public AttackPower(int minAttackPower, int attackPower) {
        this(minAttackPower, attackPower, null);
    }

    public AttackPower(int minAttackPower, int attackPower, AttackType attackType) {
        this.attackType = attackType;
        this.attackPower = attackPower;
        this.minAttackPower = minAttackPower;
    }

    public double getAttackPower() {
        return minAttackPower + Math.random() * (attackPower - minAttackPower);
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public AttackType getAttackPowerType() {
        return attackType;
    }
}


