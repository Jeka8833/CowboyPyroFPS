package uk.Jeka.CowboyPyroFPS.render;

import com.jogamp.opengl.GL2;
import uk.Jeka.CowboyPyroFPS.CowboyPyroFPS;

public class Command {

    private static boolean active = false;

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

            gl.glBegin(GL2.GL_QUADS);
            gl.glColor3d(0.4f, 0.4f, 0.4f);
            gl.glVertex2f(Width, Height);
            gl.glVertex2f(Width, Height - 25);
            gl.glVertex2f(0, Height - 25);
            gl.glVertex2f(0, Height);
            gl.glEnd();

            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glPopMatrix();
            gl.glMatrixMode(GL2.GL_MODELVIEW);
        }
    }

    public static void setActive() {
        active = active == false;
    }
}
