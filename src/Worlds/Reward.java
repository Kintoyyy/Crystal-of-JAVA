package Worlds;

public record Reward(String name, int gold, int exp) {
    public Reward {
        if (gold < 0) {
            throw new IllegalArgumentException("Gold cannot be negative");
        }
        if (exp < 0) {
            throw new IllegalArgumentException("Experience cannot be negative");
        }
    }

    public String getName() {
        return name;
    }
}
