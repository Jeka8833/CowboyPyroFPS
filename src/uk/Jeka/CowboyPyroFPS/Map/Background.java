package uk.Jeka.CowboyPyroFPS.Map;

public class Background {

    private final byte Front;
    private final byte Back;
    private final byte Right;
    private final byte Left;
    private final byte Top;

    public Background(final byte Back, final byte Front, final byte Left, final byte Right, final byte Top) {
        this.Back = Back;
        this.Front = Front;
        this.Left = Left;
        this.Right = Right;
        this.Top = Top;
    }

    public byte getBack() {
        return Back;
    }

    public byte getFront() {
        return Front;
    }

    public byte getLeft() {
        return Left;
    }

    public byte getRight() {
        return Right;
    }

    public byte getTop() {
        return Top;
    }
}
