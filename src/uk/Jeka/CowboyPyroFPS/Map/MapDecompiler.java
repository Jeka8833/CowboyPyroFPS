package uk.Jeka.CowboyPyroFPS.Map;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.json.simple.JSONObject;

public class MapDecompiler {

    public static void Convert() {
        File inv = Inventory((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 100, (byte) 50);
        Chunk[][] chunk = new Chunk[20][20];

        final Random random = new Random();
        for (int i = 0; i < 20; i++) {
            for (int a = 0; a < 20; a++) {
                chunk[i][a] = new Chunk((byte) random.nextInt(91), (byte) random.nextInt(127), (byte) random.nextInt(127), (byte) random.nextInt(127), (byte) random.nextInt(91), (byte) random.nextInt(91));
            }
        }

        File map = Map(chunk, 2, 2, 100);
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D:\\save.dat"))) {
            FileInputStream inve = new FileInputStream(inv);
            FileInputStream maps = new FileInputStream(map);

            zout.putNextEntry(new ZipEntry("inventory.json"));

            byte[] buffer = new byte[inve.available()];
            inve.read(buffer);
            zout.write(buffer);

            zout.closeEntry();

            zout.putNextEntry(new ZipEntry("map.dat"));

            byte[] buffer1 = new byte[maps.available()];
            maps.read(buffer1);
            zout.write(buffer1);

            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static File Inventory(byte slot1, byte slot2, byte slot3, byte slot4, byte heal, byte armor) {
        final JSONObject obj = new JSONObject();
        obj.put("Slot1", slot1);
        obj.put("Slot2", slot2);
        obj.put("Slot3", slot3);
        obj.put("Slot4", slot4);
        obj.put("Heal", heal);
        obj.put("Armor", armor);
        File temp = null;
        try {
            temp = File.createTempFile("Inventory", ".json");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(temp))) {
                writer.write(obj.toJSONString());
            }
        } catch (IOException ex) {
            Logger.getLogger(MapDecompiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public static File Map(Chunk[][] map, int x, int y, int id) {
        StringBuilder br = new StringBuilder();
        br.append((char) Integer.parseInt(Integer.toBinaryString(x), 2));
        br.append((char) Integer.parseInt(Integer.toBinaryString(y), 2));
        for (Chunk[] a : map) {
            for (Chunk b : a) {
                br.append((char) Integer.parseInt(Integer.toBinaryString(b.getWall()) + "", 2));
                br.append((char) Integer.parseInt(Integer.toBinaryString(b.getItem()) + "", 2));
                br.append((char) Integer.parseInt(Integer.toBinaryString(b.getObject()) + "", 2));
                br.append((char) Integer.parseInt(Integer.toBinaryString(b.getEntity()) + "", 2));
            }
        }

        File temp = null;
        try {
            temp = File.createTempFile("Map" + id, ".dat");
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            writer.write(br.toString());
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(MapDecompiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public static void Decompile() {
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

        for (String ter : files) {
            System.out.println(ter);
        }
    }
}
