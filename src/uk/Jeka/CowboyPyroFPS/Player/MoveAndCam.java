package uk.Jeka.CowboyPyroFPS.Player;

import com.jogamp.opengl.glu.GLU;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import uk.Jeka.CowboyPyroFPS.Utils.Setting;

public class MoveAndCam {

    private static Robot robot;
    private static JFrame frame;

    private static float x = 0f;
    private static float y = 0f;
    private static float z = 0f;

    private static float rotationX = 0f;
    private static float rotationY = 0f;
    private static float pith = 0f;
    private static float yaw = 0f;

    private static boolean keyW = false;
    private static boolean keyA = false;
    private static boolean keyS = false;
    private static boolean keyD = false;
    private static boolean keyShift = false;
    private static boolean keySpase = false;
    private static boolean keyCtrl = false;

    private static float deltaTime = 0.1f;
    private static long last_time = System.nanoTime();

    private static int MoveSpeed = 60;

    private static boolean correction = true;
    private static int correctionX = 0;
    private static int correctionY = 0;
    
    public static void begin(final JFrame frame){
        MoveAndCam.frame = frame;
        try {
            robot = new Robot();
            robot.mouseMove(frame.getLocation().x + frame.getWidth() / 2, frame.getLocation().y + frame.getHeight() / 2);
        } catch (AWTException ex) {
            Logger.getLogger(MoveAndCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void render(GLU glu) {
        long time = System.nanoTime();
        deltaTime = ((float) (time - last_time) / 1000000);
        last_time = time;

        float deltaTimeMove = deltaTime / MoveSpeed;
        float rightABC = getPith() - 3.14f / 2;
        float rightX = (float) Math.sin(rightABC);
        float rightZ = (float) Math.cos(rightABC);
        float cosVer = (float) Math.cos(getYaw());
        if (keyD) {
            setX(getX() + rightX * deltaTimeMove);
            setZ(getZ() + rightZ * deltaTimeMove);
        }
        if (keyA) {
            setX(getX() - rightX * deltaTimeMove);
            setZ(getZ() - rightZ * deltaTimeMove);
        }
        if (keyW) {
            setX(getX() + rightZ * deltaTimeMove);
            setZ(getZ() + -rightX * deltaTimeMove);
        }
        if (keyS) {
            setX(getX() - rightZ * deltaTimeMove);
            setZ(getZ() - -rightX * deltaTimeMove);
        }
        if (Setting.isIsAdmin() && keySpase) {
            setY(getY() + deltaTimeMove);
        }
        if (Setting.isIsAdmin() && keyShift) {
            setY(getY() - deltaTimeMove);
        }
        //System.out.println(x + " " + y + " " + z + " " + pith + " " + yaw);
        glu.gluLookAt(getX(), getY(), getZ(),
                getX() + cosVer * Math.sin(getPith()),
                getY() + Math.sin(getYaw()),
                getZ() + cosVer * Math.cos(getPith()),
                0, 1, 0
        );
    }

    public static void PressW() {
        keyW = true;
    }

    public static void ReleasedW() {
        keyW = false;
    }

    public static void PressA() {
        keyA = true;
    }

    public static void ReleasedA() {
        keyA = false;
    }

    public static void PressS() {
        keyS = true;
    }

    public static void ReleasedS() {
        keyS = false;
    }

    public static void PressD() {
        keyD = true;
    }

    public static void ReleasedD() {
        keyD = false;
    }

    public static void PressShift() {
        keyShift = true;
    }

    public static void ReleasedShift() {
        keyShift = false;
    }

    public static void PressSpase() {
        keySpase = true;
    }

    public static void ReleasedSpase() {
        keySpase = false;
    }

    public static void PressCtrl() {
        keyCtrl = true;
        MoveSpeed = 40;
    }

    public static void ReleasedCtrl() {
        keyCtrl = false;
    }

    public static void UpdateKey() {
        if (!keyCtrl && !keyA && !keyD && !keyS && !keyW) {
            MoveSpeed = 60;
        }
    }

    public static void MouseMove(int x, int y) {
        if (correction) {
            correctionX = ((frame.getWidth() / 2) - x);
            correctionY = ((frame.getHeight() / 2) - y);
            correction = false;
        } else {
            rotationX += (((frame.getWidth() / 2) - x) - correctionX) * 0.2;
            rotationY += (((frame.getHeight() / 2) - y) - correctionY) * 0.2;
            if (rotationX > 180) {
                rotationX = -180;
            }
            if (rotationX < -180) {
                rotationX = 180;
            }
            if (rotationY > 90) {
                rotationY = 89.9f;
            }
            if (rotationY < -90) {
                rotationY = -89.9f;
            }
            setPith((float) Math.toRadians(rotationX));
            setYaw((float) Math.toRadians(rotationY));
            robot.mouseMove(frame.getLocation().x + frame.getWidth() / 2, frame.getLocation().y + frame.getHeight() / 2);
        }
    }

    /**
     * @return the x
     */
    public static float getX() {
        return x;
    }

    /**
     * @param aX the x to set
     */
    public static void setX(float aX) {
        x = aX;
    }

    /**
     * @return the y
     */
    public static float getY() {
        return y;
    }

    /**
     * @param aY the y to set
     */
    public static void setY(float aY) {
        y = aY;
    }

    /**
     * @return the z
     */
    public static float getZ() {
        return z;
    }

    /**
     * @param aZ the z to set
     */
    public static void setZ(float aZ) {
        z = aZ;
    }

    /**
     * @return the pith
     */
    public static float getPith() {
        return pith;
    }

    /**
     * @param aPith the pith to set
     */
    public static void setPith(float aPith) {
        pith = aPith;
    }

    /**
     * @return the yaw
     */
    public static float getYaw() {
        return yaw;
    }

    /**
     * @param aYaw the yaw to set
     */
    public static void setYaw(float aYaw) {
        yaw = aYaw;
    }
}
