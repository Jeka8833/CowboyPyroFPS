package uk.Jeka.CowboyPyroFPS.Player;

import uk.Jeka.CowboyPyroFPS.Map.Item;

public class Inventory {

    private final Item Slot1;
    private final Item Slot2;
    private final Item Slot3;
    private final Item Slot4;
    private final byte Heal;
    private final byte Armor;

    public Inventory(final Item Slot1, final Item Slot2, final Item Slot3, final Item Slot4, final byte Heal, final byte Armor) {
        this.Armor = Armor;
        this.Heal = Heal;
        this.Slot1 = Slot1;
        this.Slot2 = Slot2;
        this.Slot3 = Slot3;
        this.Slot4 = Slot4;
    }

    public Item getSlot1() {
        return Slot1;
    }

    public Item getSlot2() {
        return Slot2;
    }

    public Item getSlot3() {
        return Slot3;
    }

    public Item getSlot4() {
        return Slot4;
    }

    public byte getHeal() {
        return Heal;
    }

    public byte getArmor() {
        return Armor;
    }
}
