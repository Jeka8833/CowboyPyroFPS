package uk.Jeka.CowboyPyroFPS.render;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import uk.Jeka.CowboyPyroFPS.CowboyPyroFPS;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;

public class Info {

    private static boolean isAdmin;
    private static final TextRenderer textr = new TextRenderer(new Font("SansSerif", Font.BOLD, 15));
    private static long last = 0;
    private static int tick = 0;
    private static int fps;

    public static void render() {
        if (isAdmin) {
            long time = System.currentTimeMillis();
            tick++;
            if (time - last > 1000) {
                fps = (int) (tick / ((time - last) / 1000));
                last = time;
                tick = 0;
            }

            final int Width = CowboyPyroFPS.frame.getWidth();
            final int Height = CowboyPyroFPS.frame.getHeight();

            textr.beginRendering(Width, Height);
            textr.setColor(1, 1, 1, 1);

            textr.draw("FPS: " + fps, 2, 33);
            textr.draw("Pith: " + MoveAndCam.pith + " Yaw: " + MoveAndCam.yaw, 2, 17);
            textr.draw("X: " + MoveAndCam.x + " Y: " + MoveAndCam.y + " Z " + MoveAndCam.z, 2, 2);

            textr.endRendering();
        }
    }

    public static void begin() {
        isAdmin = new Setting().isAdmin();
    }
}
