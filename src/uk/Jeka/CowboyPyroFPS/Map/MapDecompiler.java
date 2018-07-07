package uk.Jeka.CowboyPyroFPS.Map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static uk.Jeka.CowboyPyroFPS.Map.MapDecompiler.Inventory;
import uk.Jeka.CowboyPyroFPS.Player.Inventory;
import uk.Jeka.CowboyPyroFPS.render.Wall;

public class MapDecompiler {

    public static void Convert() {
        Chunk[][] chunk = new Chunk[998][998];
        Door[] door = new Door[998];
        final Random random = new Random();
        for (int i = 0; i < 998; i++) {
            for (int a = 0; a < 998; a++) {
                chunk[i][a] = new Chunk(new Wall((byte) random.nextInt(79), (byte) random.nextInt(79), (byte) random.nextInt(79), (byte) random.nextInt(79)), (byte) 2, (byte) 3, (byte) 4, (byte) random.nextInt(79), (byte) 6);
            }
            door[i] = new Door(random.nextInt(100), random.nextInt(100), random.nextInt(100), random.nextInt(100), (byte) 5);
        }

        String inv = Inventory(new Item((byte) 1, (byte) 2), new Item((byte) 3, (byte) 4), new Item((byte) 5, (byte) 6), new Item((byte) 7, (byte) 8), (byte) 100, (byte) 50);
        final String map = Map(chunk, door, new Background((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5), 998, 0, 3.5f, 8.698f);
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D:\\save.dat"))) {
            zout.putNextEntry(new ZipEntry("inventory.json"));
            zout.write(inv.getBytes());
            zout.closeEntry();

            zout.putNextEntry(new ZipEntry("map0.dat"));
            zout.write(map.getBytes());
            zout.closeEntry();
        } catch (Exception ex) {
        }
    }

    public static String Inventory(Item slot1, Item slot2, Item slot3, Item slot4, byte heal, byte armor) {
        final JSONArray Inventory = new JSONArray();
        final JSONObject Slot1 = new JSONObject();
        final JSONObject Slot2 = new JSONObject();
        final JSONObject Slot3 = new JSONObject();
        final JSONObject Slot4 = new JSONObject();
        final JSONObject obj = new JSONObject();
        obj.put("Heal", heal);
        obj.put("Armor", armor);
        Slot1.put("Quantity", slot1.getQuantity());
        Slot2.put("Quantity", slot2.getQuantity());
        Slot3.put("Quantity", slot3.getQuantity());
        Slot4.put("Quantity", slot4.getQuantity());
        Slot1.put("Item", slot1.getItem());
        Slot2.put("Item", slot2.getItem());
        Slot3.put("Item", slot3.getItem());
        Slot4.put("Item", slot4.getItem());
        Inventory.add(Slot1);
        Inventory.add(Slot2);
        Inventory.add(Slot3);
        Inventory.add(Slot4);
        obj.put("Inventory", Inventory);

        return obj.toJSONString();
    }

    public static String Map(Chunk[][] map, Door[] doors, Background background, int size, int id, float SpawnX, float SpawnZ) {
        final StringBuilder br = new StringBuilder();
        final JSONObject obj = new JSONObject();
        obj.put("Size", size);
        final JSONArray back = new JSONArray();
        back.add(background.getBack());
        back.add(background.getFront());
        back.add(background.getLeft());
        back.add(background.getRight());
        back.add(background.getTop());
        obj.put("Background", back);
        final JSONArray spawn = new JSONArray();
        spawn.add(SpawnX);
        spawn.add(SpawnZ);
        obj.put("Spawn", spawn);
        final JSONArray doorss = new JSONArray();
        for (Door door : doors) {
            final JSONObject dd = new JSONObject();
            dd.put("IX", door.getInputX());
            dd.put("IY", door.getInputY());
            dd.put("OX", door.getOutputX());
            dd.put("OY", door.getOutputY());
            dd.put("Key", door.getKey());
            doorss.add(dd);
        }
        obj.put("Door", doorss);
        br.append(obj.toJSONString());
        br.append("mp");
        for (Chunk[] a : map) {
            for (Chunk b : a) {
                if (b.getWall() == null) {
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getItem()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getObject()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getEntity()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getBottom()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getTop()) + "", 2));
                } else {
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getWall().getBack()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getWall().getFront()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getWall().getLeft()) + "", 2));
                    br.append((char) Integer.parseInt(Integer.toBinaryString(b.getWall().getRight()) + "", 2));
                    br.append((char) 0x7F);
                }
            }
        }
        return br.toString();
    }

    public static List<String> Decompile() {
        List<String> files = new ArrayList<>();
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("D:\\save.dat"))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                StringBuilder map = new StringBuilder();
                map.append(entry.getName()).append(":");
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    map.append((char) c);
                }
                files.add(map.toString());
                zin.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return files;
    }

    public static Map getData(List<String> file) {
        final int num = file.size() - 1;
        Inventory inventory = null;
        Chunk[][][] chunk = new Chunk[num][10][10];
        Door[][] doors = new Door[num][10];
        Background[] Background = new Background[num];
        float[] SpawnX = new float[num];
        float[] SpawnY = new float[num];
        for (String data : file) {
            if (data.contains("inventory.json:")) {
                try {
                    final JSONObject object = (JSONObject) new JSONParser().parse(data.substring(15));
                    final JSONArray msg = (JSONArray) object.get("Inventory");
                    final JSONObject item1 = (JSONObject) msg.get(0);
                    final JSONObject item2 = (JSONObject) msg.get(1);
                    final JSONObject item3 = (JSONObject) msg.get(2);
                    final JSONObject item4 = (JSONObject) msg.get(3);
                    inventory = new Inventory(
                            new Item((byte) (long) item1.get("Quantity"), (byte) (long) item1.get("Item")),
                            new Item((byte) (long) item2.get("Quantity"), (byte) (long) item2.get("Item")),
                            new Item((byte) (long) item3.get("Quantity"), (byte) (long) item3.get("Item")),
                            new Item((byte) (long) item4.get("Quantity"), (byte) (long) item4.get("Item")),
                            (byte) (long) object.get("Heal"), (byte) (long) object.get("Armor"));
                } catch (ParseException ex) {
                }
            } else if (data.contains("map")) {
                final int id = Integer.parseInt(data.substring(3, data.indexOf(".dat:")));
                int size = 1;
                try {
                    final JSONObject object = (JSONObject) new JSONParser().parse(data.substring(data.indexOf(".dat:") + 5, data.indexOf("}mp") + 1));
                    size = (int) (long) object.get("Size");
                    doors = new Door[num][size];
                    final JSONArray spawn = (JSONArray) object.get("Spawn");
                    SpawnX[id] = (float) (double) spawn.get(0);
                    SpawnY[id] = (float) (double) spawn.get(1);
                    final JSONArray back = (JSONArray) object.get("Background");
                    Background[id] = new Background((byte) (long) back.get(0), (byte) (long) back.get(1), (byte) (long) back.get(2), (byte) (long) back.get(3), (byte) (long) back.get(4));
                    final JSONArray msg = (JSONArray) object.get("Door");
                    for (int i = 0; i < msg.size(); i++) {
                        final JSONObject door = (JSONObject) msg.get(i);
                        doors[id][i] = new Door((int) (long) door.get("IX"), (int) (long) door.get("IY"), (int) (long) door.get("OX"), (int) (long) door.get("OY"), (byte) (long) door.get("Key"));
                    }
                } catch (ParseException ex) {
                }
                chunk = new Chunk[file.size() - 1][size][size];
                final String dat = data.substring(data.indexOf("}mp") + 3);
                int i = 0;
                for (int a = 0; a < size; a++) {
                    for (int b = 0; b < size; b++) {
                        final int pos = i * 5;
                        String text = dat.substring(pos, pos + 5);
                        if (text.charAt(4) == 0x7F) {
                            chunk[id][a][b] = new Chunk(new Wall((byte) text.charAt(0), (byte) text.charAt(1), (byte) text.charAt(2), (byte) text.charAt(3)));
                        } else {
                            chunk[id][a][b] = new Chunk((byte) text.charAt(2), (byte) text.charAt(0), (byte) text.charAt(1), (byte) text.charAt(4), (byte) text.charAt(3));
                        }
                        i++;
                    }
                }
            }
        }
        return new Map(chunk, doors, inventory, Background, SpawnX, SpawnY);
    }
}
