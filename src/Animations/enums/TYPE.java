package Animations.enums;

/**
 * The {@code TYPE} enum represents the various animation states or actions
 * that an object can have in an animation system.
 * These states define the specific triggerType of animation that is being played.
 * <p>
 * This enum is used to differentiate between various animation behaviors,
 * such as an idle stance, walking, attacking, etc. It can be used in conjunction
 * with other parameters like direction to create dynamic animations.
 * </p>
 * <ul>
 *     <li>{@link TYPE#IDLE} - Represents the idle animation, when the object is stationary.</li>
 *     <li>{@link TYPE#WALK} - Represents the walking animation, typically when the object is moving.</li>
 *     <li>{@link TYPE#ATTACK} - Represents the attack animation, triggered when the object performs an attack.</li>
 *     <li>{@link TYPE#GHOST} - Represents a ghost-like animation, possibly for a transparent or ethereal object.</li>
 *     <li>{@link TYPE#TIRED} - Represents a tired animation, often showing exhaustion or slower movement.</li>
 *     <li>{@link TYPE#DANCE} - Represents a dancing animation, often used in playful or celebratory contexts.</li>
 *     <li>{@link TYPE#SLEEP} - Represents the sleep animation, typically showing the object resting or inactive.</li>
 * </ul>
 *
 * @author YourName
 * @version 1.0
 */
public enum TYPE {
    IDLE, WALK, ATTACK, GHOST, TIRED, DANCE, SLEEP
}
