package Entities.Characters.Stats;

public class Mana {
    private double mana;
    private double baseMana;
    private double manaRegenRate;

    public Mana(int baseMana) {
        this.mana = baseMana;
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

    public void useMana(double mana) {
        this.mana -= mana;
        if (this.mana < 0) {
            this.mana = 0;
        }
    }

    public void addMana(double mana) {
        this.mana += mana;
        if (this.mana > baseMana) {
            this.mana = baseMana;
        }
    }

    public boolean hasEnoughMana(double mana) {
        return this.mana >= mana;
    }

    public boolean isOutOfMana() {
        return this.mana == 0;
    }

    public boolean isFullMana() {
        return this.mana == this.baseMana;
    }

    public void resetMana() {
        this.mana = 0;
    }

    public void resetMana(int mana) {
        this.mana = mana;
    }
}
