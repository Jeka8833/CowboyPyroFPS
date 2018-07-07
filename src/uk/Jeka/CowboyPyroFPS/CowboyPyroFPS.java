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
import uk.Jeka.CowboyPyroFPS.Map.Chunk;
import uk.Jeka.CowboyPyroFPS.Map.Map;
import uk.Jeka.CowboyPyroFPS.Map.MapDecompiler;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;
import uk.Jeka.CowboyPyroFPS.render.Info;
import uk.Jeka.CowboyPyroFPS.render.Sky;
import uk.Jeka.CowboyPyroFPS.render.SkyBox;
import uk.Jeka.CowboyPyroFPS.render.Wall;

public class CowboyPyroFPS implements GLEventListener {

    private static JFrame frame;

    private static final GLU glu = new GLU();

    public static Map map;

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
        //MapDecompiler.Convert();
        map = MapDecompiler.getData(MapDecompiler.Decompile());
    }

    /**
     * @return the frame
     */
    public static JFrame getFrame() {
        return frame;
    }

    private int texture[] = new int[92];

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(1f, 1f, 1f, 1f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glLoadIdentity();

        MoveAndCam.render(glu);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture[0]);
        gl.glTranslatef(MoveAndCam.getX(), 0, MoveAndCam.getZ());
        SkyBox.render(gl);
        gl.glTranslatef(-MoveAndCam.getX(), 0, -MoveAndCam.getZ());

        gl.glEnable(GL2.GL_BLEND);
        for (int i = 0; i < 100; i++) {
            for (int r = 0; r < 100; r++) {

                gl.glPushMatrix();
                if (Map.isVisible(r, i)) {
                    gl.glTranslatef(r * 2, 0f, i * 2);
                    final byte wall = map.getChunk(0)[i][r].getWall().getFront();
                    if (wall != 0) {
                        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture[wall]);
                        Wall.render(gl);
                    } else {
                        final byte top = map.getChunk(0)[i][r].getTop();
                        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture[top]);
                        Sky.render(gl);
                    }
                }

                gl.glPopMatrix();
            }
        }
        gl.glDisable(GL2.GL_BLEND);
        gl.glPopMatrix();
        Info.render();
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClearDepth(1.0f);
        gl.glClearColor(0.4f, 0.69f, 1f, 1f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_FASTEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        InputStream is1 = CowboyPyroFPS.class.getResourceAsStream("Image/skybox.png");
        try {
            texture[0] = TextureIO.newTexture(is1, false, "png").getTextureObject(gl);
            gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
        } catch (IOException | GLException ex) {
            Logger.getLogger(CowboyPyroFPS.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 1; i <= 79; i++) {
            InputStream is = CowboyPyroFPS.class.getResourceAsStream("Image/block/" + i + ".png");
            try {
                texture[i] = TextureIO.newTexture(is, false, "png").getTextureObject(gl);
                gl.glTexParameterf(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
            } catch (IOException | GLException ex) {
                Logger.getLogger(CowboyPyroFPS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, width / (float) height, 1.0, 150);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

}
