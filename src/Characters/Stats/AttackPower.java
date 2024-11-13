package Characters.Stats;

public class AttackPower {
    private double attackPower;
    private final double minAttackPower;

    public AttackPower(int attackPower) {
        this(attackPower, attackPower);
    }

    public AttackPower(int minAttackPower, int attackPower) {
        this.attackPower = attackPower;
        this.minAttackPower = minAttackPower;
    }

    public double getAttackPower() {
        return minAttackPower + Math.random() * (attackPower - minAttackPower);
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}
