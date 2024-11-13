package Item;

public abstract class Item {
    protected String name;
    protected int id;
    protected int price;
    protected String description;
    protected String spritePath;

    Item(String name, int id, int price, String description, String spritePath) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.description = description;
        this.spritePath = spritePath;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }
}
