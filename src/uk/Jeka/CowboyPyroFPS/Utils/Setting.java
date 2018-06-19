package uk.Jeka.CowboyPyroFPS.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Setting {

    private static final File FilePath = new File(System.getenv("APPDATA") + "\\CowboyPyroFPS");
    private static final File SettingFile = new File(FilePath + "\\setting.txt");

    public Setting() {
        if (FilePath.isDirectory()) {
            FilePath.getParentFile().mkdirs();
        }
    }

    public boolean isAdmin() {
        if (!SettingFile.isFile()) {
            Files.create(SettingFile.getAbsolutePath());
        }
        try (BufferedReader br = new BufferedReader(new FileReader(SettingFile))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("Pass: SevaAndJeka")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
