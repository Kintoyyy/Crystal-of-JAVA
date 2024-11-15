package Entities.Common.Effects;

public abstract class Effect {
    private final String name;
    private final String description;
    private final int turnsDuration;
    private final double effectValue;
    private final Character character;

    public Effect(String name, String description, int turnsDuration, double effectValue, Character character) {

        this.name = name;
        this.description = description;
        this.turnsDuration = turnsDuration;
        this.effectValue = effectValue;
        this.character = character;
    }

    public abstract void applyEffect();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
