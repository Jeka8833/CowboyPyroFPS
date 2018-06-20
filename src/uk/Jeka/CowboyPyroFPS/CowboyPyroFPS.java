package uk.Jeka.CowboyPyroFPS;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import uk.Jeka.CowboyPyroFPS.Listeners.Keyboard;
import uk.Jeka.CowboyPyroFPS.Listeners.MouseMove;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;
import uk.Jeka.CowboyPyroFPS.render.Info;
import uk.Jeka.CowboyPyroFPS.render.Wall;

public class CowboyPyroFPS implements GLEventListener {

    private static JFrame frame;

    private static final GLU glu = new GLU();

    public static void main(String[] args) {
        Setting.read();
        final GLCanvas glcanvas = new GLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL2)));
        frame = new JFrame("Cowboy Pyro FPS");
        glcanvas.addGLEventListener(new CowboyPyroFPS());
        glcanvas.setSize(400, 400);
        glcanvas.addKeyListener(new Keyboard());
        glcanvas.addMouseMotionListener(new MouseMove());
        getFrame().getContentPane().add(glcanvas);
        getFrame().setSize(glcanvas.getSize());
        getFrame().setVisible(true);
        new FPSAnimator(glcanvas, 300, true).start();
        MoveAndCam.begin(getFrame());
    }

    /**
     * @return the frame
     */
    public static JFrame getFrame() {
        return frame;
    }

    private int texture;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(1f, 1f, 1f, 1f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        MoveAndCam.render(glu);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        for (int i = 0; i < 10; i++) {
            for (int r = 0; r < 10; r++) {
                gl.glTranslatef(3f, 0f, 0f);
                Wall.render(gl);
            }
            gl.glTranslatef(-30f, 0f, 3f);
        }
        Info.render();
        gl.glDisable(GL2.GL_BLEND);
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        InputStream is = CowboyPyroFPS.class.getResourceAsStream("Image/box.png");
        try {
            texture = TextureIO.newTexture(is, false, "png").getTextureObject(gl);
        } catch (IOException | GLException ex) {
            Logger.getLogger(CowboyPyroFPS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, width / (float) height, 1.0, Setting.getDistance());
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}