package uk.Jeka.CowboyPyroFPS.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Setting {

    private static final File FilePath = new File(System.getenv("APPDATA") + "\\CowboyPyroFPS\\setting.json");

    private static boolean isAdmin = false;
    private static int distance = 50;

    public static void update() {
        final JSONObject obj = new JSONObject();
        //"Pass":"SevaAndJeka",
        obj.put("Distance", getDistance());
        try (final FileWriter file = new FileWriter(FilePath)) {
            file.write(obj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            final JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(FilePath));
            setDistance((int) (long) jsonObject.get("Distance"));
            setIsAdmin("SevaAndJeka".equals(jsonObject.get("Pass")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the isAdmin
     */
    public static boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param aIsAdmin the isAdmin to set
     */
    public static void setIsAdmin(boolean aIsAdmin) {
        isAdmin = aIsAdmin;
    }

    /**
     * @return the distance
     */
    public static int getDistance() {
        return distance;
    }

    /**
     * @param aDistance the distance to set
     */
    public static void setDistance(int aDistance) {
        distance = aDistance;
    }
}
