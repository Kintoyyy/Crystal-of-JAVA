package Characters.Stats;

public class Defense {
    private double defense;
    private double baseDefense;

    public Defense(int defense) {
        this.defense = defense;
        this.baseDefense = defense;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public void addDefense(double defense) {
        this.defense += defense;
    }

    public void removeDefense(double defense) {
        this.defense -= defense;
    }

    public boolean hasEnoughDefense(double defense) {
        return this.defense >= defense;
    }

    public void resetDefense() {
        this.defense = baseDefense;
    }

    public void resetBaseDefense() {
        this.baseDefense = 0;
    }

    public void resetAll() {
        resetDefense();
        resetBaseDefense();
    }
}
