package uk.Jeka.CowboyPyroFPS.Map;

public class Item {

    private final byte Quantity;
    private final byte Item;

    public Item(final byte Quantity, final byte Item) {
        this.Quantity = Quantity;
        this.Item = Item;
    }

    public byte getQuantity() {
        return Quantity;
    }

    public byte getItem() {
        return Item;
    }
}
