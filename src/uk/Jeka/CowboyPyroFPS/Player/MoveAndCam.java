package uk.Jeka.CowboyPyroFPS.Player;

import com.jogamp.opengl.glu.GLU;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import javax.swing.JFrame;

public class MoveAndCam {

    private static Robot robot;
    private static JFrame frame;

    public static float x = 0f;
    public static float y = 0f;
    public static float z = 0f;

    private static float rotationX = 0f;
    private static float rotationY = 0f;
    public static float pith = 0f;
    public static float yaw = 0f;

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

    public MoveAndCam(JFrame frame) throws AWTException {
        robot = new Robot();
        this.frame = frame;
        robot.mouseMove(frame.getLocation().x + frame.getWidth() / 2, frame.getLocation().y + frame.getHeight() / 2);
    }

    public static void render(GLU glu) {
        long time = System.nanoTime();
        deltaTime = ((float) (time - last_time) / 1000000);
        last_time = time;

        float deltaTimeMove = deltaTime / MoveSpeed;
        float rightABC = pith - 3.14f / 2;
        float rightX = (float) Math.sin(rightABC);
        float rightZ = (float) Math.cos(rightABC);
        float cosVer = (float) Math.cos(yaw);
        if (keyD) {
            x += rightX * deltaTimeMove;
            z += rightZ * deltaTimeMove;
        }
        if (keyA) {
            x -= rightX * deltaTimeMove;
            z -= rightZ * deltaTimeMove;
        }
        if (keyW) {
            x += rightZ * deltaTimeMove;
            z += -rightX * deltaTimeMove;
        }
        if (keyS) {
            x -= rightZ * deltaTimeMove;
            z -= -rightX * deltaTimeMove;
        }
        if (keySpase) {
            y += deltaTimeMove;
        }
        if (keyShift) {
            y -= deltaTimeMove;
        }
        //System.out.println(x + " " + y + " " + z + " " + pith + " " + yaw);
        glu.gluLookAt(
                x, y, z,
                x + cosVer * Math.sin(pith),
                y + Math.sin(yaw),
                z + cosVer * Math.cos(pith),
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
            pith = (float) Math.toRadians(rotationX);
            yaw = (float) Math.toRadians(rotationY);
            robot.mouseMove(frame.getLocation().x + frame.getWidth() / 2, frame.getLocation().y + frame.getHeight() / 2);
        }
    }
}
