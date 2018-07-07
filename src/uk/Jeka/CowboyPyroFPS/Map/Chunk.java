package uk.Jeka.CowboyPyroFPS.Map;

import uk.Jeka.CowboyPyroFPS.render.Wall;

public class Chunk {

    private final Wall Wall;
    private final byte Entity;
    private final byte Item;
    private final byte Object;
    private final byte Top;
    private final byte Bottom;

    public Chunk(final Wall Wall, final byte Entity, final byte Item, final byte Object, final byte Top, final byte Bottom) {
        this.Entity = Entity;
        this.Item = Item;
        this.Object = Object;
        this.Wall = Wall;
        this.Top = Top;
        this.Bottom = Bottom;
    }

    public Chunk(final byte Entity, final byte Item, final byte Object, final byte Top, final byte Bottom) {
        this.Entity = Entity;
        this.Item = Item;
        this.Object = Object;
        this.Top = Top;
        this.Bottom = Bottom;
        this.Wall = new Wall((byte) 0, (byte) 0, (byte) 0, (byte) 0);
    }

    public Chunk(final Wall Wall) {
        this.Wall = Wall;
        this.Entity = (byte) 0;
        this.Item = (byte) 0;
        this.Object = (byte) 0;
        this.Top = (byte) 0;
        this.Bottom = (byte) 0;
    }

    public Wall getWall() {
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

    @Override
    public String toString() {
        return Wall + " " + Entity + " " + Item + " " + Object + " " + Top + " " + Bottom;
    }
}
