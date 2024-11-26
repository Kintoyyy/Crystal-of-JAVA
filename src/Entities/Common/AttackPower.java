package Entities.Common;

/**
 * Represents the attack power of an entity, which can be either physical or magical.
 * <p>
 * This class calculates the attack power, which can vary randomly within a specified range.
 * It also includes the triggerType of attack (e.g., PHYSICAL, MAGIC) and ensures that the attack power
 * falls within the defined minimum and maximum values.
 * </p>
 */
public class AttackPower {
    private final double attackPower;
    private final double minAttackPower;
    private final AttackType attackType;

    /**
     * Constructs an AttackPower object with the specified attack power.
     * The attack power is set to the same value for both minimum and maximum.
     *
     * @param attackPower The attack power value.
     */
    public AttackPower(int attackPower) {
        this(attackPower, attackPower);
    }

    /**
     * Constructs an AttackPower object with the specified attack power and attack triggerType.
     *
     * @param attackPower The attack power value.
     * @param attackType  The triggerType of attack (e.g., PHYSICAL or MAGIC).
     */
    public AttackPower(int attackPower, AttackType attackType) {
        this.attackType = attackType;
        this.attackPower = attackPower;
        this.minAttackPower = attackPower;
    }

    /**
     * Constructs an AttackPower object with the specified minimum and maximum attack power.
     *
     * @param minAttackPower The minimum attack power.
     * @param attackPower    The maximum attack power.
     */
    public AttackPower(int minAttackPower, int attackPower) {
        this(minAttackPower, attackPower, null);
    }

    /**
     * Constructs an AttackPower object with the specified minimum and maximum attack power
     * and attack triggerType.
     *
     * @param minAttackPower The minimum attack power.
     * @param attackPower    The maximum attack power.
     * @param attackType     The triggerType of attack (e.g., PHYSICAL or MAGIC).
     */
    public AttackPower(int minAttackPower, int attackPower, AttackType attackType) {
        this.attackType = attackType;
        this.attackPower = attackPower;
        this.minAttackPower = minAttackPower;
    }

    /**
     * Gets a random attack power value within the range defined by the minimum and maximum attack power.
     *
     * @return A random attack power value.
     */
    public double getDamage() {
        return minAttackPower + Math.random() * (attackPower - minAttackPower);
    }
}
