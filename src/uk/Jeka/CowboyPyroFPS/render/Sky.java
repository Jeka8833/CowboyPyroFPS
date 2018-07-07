package uk.Jeka.CowboyPyroFPS.render;

import com.jogamp.opengl.GL2;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;

public class Sky {
     public static void render(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, 1.0f, 1.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, 1.0f, 1.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glEnd();
    }
}
