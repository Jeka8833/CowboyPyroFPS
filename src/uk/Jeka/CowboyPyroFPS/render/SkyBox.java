package uk.Jeka.CowboyPyroFPS.render;

import com.jogamp.opengl.GL2;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;

public class SkyBox {

    public static void render(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -2.0f, 50.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -2.0f, 50.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 100.0f, 50.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 100.0f, 50.0f);   //
        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-50.0f, -2.0f, -50.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-50.0f, 100.0f, -50.0f); //
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(50.0f, 100.0f, -50.0f); // 
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(50.0f, -2.0f, -50.0f);
        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 100.0f, -50.0f);  //
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, 100.0f, 50.0f);  //
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, 100.0f, 50.0f);  // 
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 100.0f, -50.0f); //
        // Right face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(50.0f, -2.0f, -50.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(50.0f, 100.0f, -50.0f); //
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(50.0f, 100.0f, 50.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(50.0f, -2.0f, 50.0f);
        // Left Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-50.0f, -2.0f, -50.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-50.0f, -2.0f, 50.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-50.0f, 100.0f, 50.0f); //
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-50.0f, 100.0f, -50.0f); //
        gl.glEnd();
    }
}
