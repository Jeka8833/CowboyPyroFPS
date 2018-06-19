package uk.Jeka.CowboyPyroFPS.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Files {
    
    public static void create(String path) {
        try {
            File f = new File(path);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}