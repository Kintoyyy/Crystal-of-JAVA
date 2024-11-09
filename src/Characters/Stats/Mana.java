package Characters.Stats;

public class Mana {
    private double mana;
    private double baseMana;
    private double manaRegenRate;

    public Mana(int mana, int baseMana) {
        this.mana = mana;
        this.baseMana = baseMana;
        this.manaRegenRate = 0.01;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public double getBaseMana() {
        return baseMana;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    public double getManaRegenRate() {
        return manaRegenRate;
    }

    public void setManaRegenRate(double manaRegenRate) {
        this.manaRegenRate = manaRegenRate;
    }

    public void regenMana() {
        mana += manaRegenRate;
        if (mana > baseMana) {
            mana = baseMana;
        }
    }
}
