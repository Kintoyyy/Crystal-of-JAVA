package Entities.Common.Effects;

/**
 * Enum representing the type of target for an effect or attack.
 * <p>
 * This enum defines two types of targets:
 * <ul>
 *   <li><b>SINGLE</b> - The effect or attack targets a single entity.</li>
 *   <li><b>AOE</b> - The effect or attack targets multiple entities in an area of effect.</li>
 * </ul>
 * </p>
 */
public enum Target {
    SINGLE,  // Effect or attack targets a single entity
    AOE      // Effect or attack targets multiple entities in an area
}
