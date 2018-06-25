package uk.Jeka.CowboyPyroFPS.Map;

public class Chunk {

    private final byte Wall;
    private final byte Entity;
    private final byte Item;
    private final byte Object;
    private final byte Top;
    private final byte Bottom;

    public Chunk(final byte Wall, final byte Entity, final byte Item, final byte Object, final byte Top, final byte Bottom) {
        this.Entity = Entity;
        this.Item = Item;
        this.Object = Object;
        this.Wall = Wall;
        this.Top = Top;
        this.Bottom = Bottom;
    }

    public byte getWall() {
        return Wall;
    }

    public byte getEntity() {
        return Entity;
    }

    public byte getItem() {
        return Item;
    }

    public byte getObject() {
        return Object;
    }

    public byte getTop() {
        return Top;
    }

    public byte getBottom() {
        return Bottom;
    }
}
