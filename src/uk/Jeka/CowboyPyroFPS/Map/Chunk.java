package uk.Jeka.CowboyPyroFPS.Map;

public class Chunk {

    private final byte Wall;
    private final byte Entity;
    private final byte Item;
    private final byte Object;

    public Chunk(final byte Wall, final byte Entity, final byte Item, final byte Object) {
        this.Entity = Entity;
        this.Item = Item;
        this.Object = Object;
        this.Wall = Wall;
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
}
