package uk.Jeka.CowboyPyroFPS;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.awt.DisplayMode;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.AWTException;
import java.awt.Font;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.Jeka.CowboyPyroFPS.Listeners.Keyboard;
import uk.Jeka.CowboyPyroFPS.Listeners.MouseMove;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;
import uk.Jeka.CowboyPyroFPS.render.Command;
import uk.Jeka.CowboyPyroFPS.render.Wall;

public class CowboyPyroFPS implements GLEventListener {

    public static DisplayMode dm, dm_old;
    public static JFrame frame;
    GLU glu = new GLU();
    private int texture;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glColor4d(1f, 1f, 1f, 1f);
        gl.glLoadIdentity(); // Reset The View
        MoveAndCam.render(glu);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        for (int i = 0; i < 5; i++) {
            gl.glTranslatef(3f, 0f, 0f);
            Wall.render(gl);
        }
TextRenderer textr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 18));
textr.setColor(Color.GREEN);
textr.begin3DRendering();
textr.draw3D("ERRORS", xLocation, yLocation, zLocation, scale);
textr.end3DRendering();
        Command.render(gl);
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
        //gl.glDisable(GL2.GL_DEPTH_TEST);
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
        glu.gluPerspective(45.0f, (float) width / (float) height, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void main(String[] args) throws AWTException {
        final GLCanvas glcanvas = new GLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL2)));
        frame = new JFrame("Cowboy Pyro FPS");
        glcanvas.addGLEventListener(new CowboyPyroFPS());
        glcanvas.setSize(400, 400);
        glcanvas.addKeyListener(new Keyboard());
        glcanvas.addMouseMotionListener(new MouseMove());
        frame.getContentPane().add(glcanvas);
        frame.setSize(glcanvas.getSize());
        frame.setVisible(true);
        new FPSAnimator(glcanvas, 300, true).start();
        new MoveAndCam(frame);
    }
}
