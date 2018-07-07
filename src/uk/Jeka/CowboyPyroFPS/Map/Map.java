package uk.Jeka.CowboyPyroFPS.Map;

import uk.Jeka.CowboyPyroFPS.Player.Inventory;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;

public class Map {

    public static boolean isVisible(int x, int y) {
        final int xl = (int) (x - MoveAndCam.getX() / 2);
        final int yl = (int) (y - MoveAndCam.getZ() / 2);
        return Math.sqrt(xl * xl + yl * yl) < 20;
    }

    private final Chunk[][][] chunk;
    private final Inventory Inventory;
    private final Background[] Background;
    private final Door[][] Door;
    private final int Size;
    private final float[] SpawnX;
    private final float[] SpawnY;

    public Map(final Chunk[][][] chunk, final Door[][] Door, final Inventory Inventory, final Background[] Background, final float[] SpawnX, final float[] SpawnY) {
        this.chunk = chunk;
        this.Door = Door;
        this.Inventory = Inventory;
        this.Background = Background;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        Size = chunk.length;
    }

    public Chunk[][] getChunk(int id) {
        return chunk[id];
    }

    public Inventory getInventory() {
        return Inventory;
    }

    public Door[] getDoor(int id) {
        return Door[id];
    }

    public Background getBackground(int id) {
        return Background[id];
    }

    public int getSize() {
        return Size;
    }

    public float getSpawnX(int id) {
        return SpawnX[id];
    }

    public float getSpawnY(int id) {
        return SpawnY[id];
    }
}
