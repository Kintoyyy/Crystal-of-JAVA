package Entities.Common.Effects;

/**
 * Abstract class representing an effect that can be applied to a character.
 * <p>
 * This class is designed to model effects that modify a character's attributes
 * over a specified duration. Subclasses of this class must implement the
 * {@link #applyEffect()} method to define the behavior of the effect.
 * </p>
 * <p>
 * An effect typically has a name, description, a duration in turns, and a value
 * representing the magnitude of the effect.
 * </p>
 */
public abstract class Effect {
    private final String name;
    private final String description;
    private final int turnsDuration;
    private final double effectValue;
    private final Character character;

    /**
     * Constructs an Effect instance.
     *
     * @param name          The name of the effect.
     * @param description   A brief description of the effect.
     * @param turnsDuration The number of turns the effect lasts.
     * @param effectValue   The magnitude or value of the effect.
     * @param character     The character to which the effect is applied.
     */
    public Effect(String name, String description, int turnsDuration, double effectValue, Character character) {
        this.name = name;
        this.description = description;
        this.turnsDuration = turnsDuration;
        this.effectValue = effectValue;
        this.character = character;
    }

    /**
     * Applies the effect to the character.
     * <p>
     * This method must be implemented by subclasses to define the specific behavior
     * of how the effect is applied to the character.
     * </p>
     */
    public abstract void applyEffect();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
