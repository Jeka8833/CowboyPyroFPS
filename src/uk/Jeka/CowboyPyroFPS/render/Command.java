package uk.Jeka.CowboyPyroFPS.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import uk.Jeka.CowboyPyroFPS.CowboyPyroFPS;

public class Command {

    private static boolean active = false;
    private static String command = "1555";

    public static void render(GL2 gl) {
        if (active) {
            final int Width = CowboyPyroFPS.frame.getWidth();
            final int Height = CowboyPyroFPS.frame.getHeight();

            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glPushMatrix();
            gl.glLoadIdentity();
            gl.glOrtho(0.0, Width, Height, 0.0, -1.0, 10.0);

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glDisable(GL2.GL_CULL_FACE);

            gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);

            gl.glBegin(GL2.GL_LINE);
            gl.glColor4f(1f, 1f, 1f, 1f);
            gl.glVertex2f(Width + 5, Height + 5);
            gl.glVertex2f(0, Height + 5);
            gl.glEnd();

            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glPopMatrix();
            gl.glMatrixMode(GL2.GL_MODELVIEW);

            TextRenderer textr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 14));
            textr.beginRendering(Width, Height);
            textr.setColor(1, 1, 1, 1);
            textr.draw(command, 2, 2);
            textr.endRendering();
        }
    }

    public static void setActive() {
        active = active == false;
    }

    public static boolean getActive() {
        return active;
    }

    public static void pressKey(char key) {
        command += key;
    }
}
