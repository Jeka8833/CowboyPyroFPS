package uk.Jeka.CowboyPyroFPS.Map;

public class Door {

    private final int InputX;
    private final int InputY;

    private final int OutputX;
    private final int OutputY;

    private final byte key;

    public Door(final int InputX, final int InputY, final int OutputX, final int OutputY, final byte key) {
        this.InputX = InputX;
        this.InputY = InputY;
        this.OutputX = OutputX;
        this.OutputY = OutputY;
        this.key = key;
    }

    public int getInputX() {
        return InputX;
    }

    public int getInputY() {
        return InputY;
    }

    public int getOutputX() {
        return OutputX;
    }

    public int getOutputY() {
        return OutputY;
    }

    public byte getKey() {
        return key;
    }
}
