package Map;

abstract class TriggerArea {
    protected int x, y, width, height;
    protected String name;
    protected boolean triggered = false;

    public TriggerArea(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void trigger();
}
