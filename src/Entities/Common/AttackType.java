package Entities.Common;

/**
 * Enum representing different types of attacks in the game.
 * <p>
 * - PHYSICAL: Represents a physical attack, which is typically based on the enemy's physical strength and can be mitigated by the target's physical defense.
 * - MAGIC: Represents a magical attack, typically based on the caster's magical power and can be mitigated by the target's magical defense or resistances.
 * </p>
 */
public enum AttackType {
    PHYSICAL,
    MAGIC,
}
