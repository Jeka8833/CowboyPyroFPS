package uk.Jeka.CowboyPyroFPS.render;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import uk.Jeka.CowboyPyroFPS.CowboyPyroFPS;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;

public class Info {

    private static final TextRenderer textr = new TextRenderer(new Font("SansSerif", Font.BOLD, 15));
    private static long last = 0;
    private static int tick = 0;
    private static int fps;

    public static void render() {
        if (Setting.isIsAdmin()) {
            long time = System.currentTimeMillis();
            tick++;
            if (time - last > 1000) {
                fps = (int) (tick / ((time - last) / 1000));
                last = time;
                tick = 0;
            }

            final int Width = CowboyPyroFPS.getFrame().getWidth();
            final int Height = CowboyPyroFPS.getFrame().getHeight();

            textr.beginRendering(Width, Height);
            textr.setColor(1, 1, 1, 1);

            textr.draw("FPS: " + fps, 2, 33);
            textr.draw("Pith: " + MoveAndCam.getPith() + " Yaw: " + MoveAndCam.getYaw(), 2, 17);
            textr.draw("X: " + MoveAndCam.getX() + " Y: " + MoveAndCam.getY() + " Z " + MoveAndCam.getZ(), 2, 2);

            textr.endRendering();
        }
    }
}