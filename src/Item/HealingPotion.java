package Item;

public class HealingPotion extends Item {
    private int healAmount;

    public HealingPotion(String name, int id, int price, String description, String spritePath, int healAmount) {
        super(name, id, price, description, spritePath);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return this.healAmount;
    }
}
