package uk.Jeka.CowboyPyroFPS.Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;
import uk.Jeka.CowboyPyroFPS.render.Command;

public class Keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 87:          //Key w
                MoveAndCam.PressW();
                break;
            case 65:          //Key a
                MoveAndCam.PressA();
                break;
            case 83:          //Key s
                MoveAndCam.PressS();
                break;
            case 68:          //Key d
                MoveAndCam.PressD();
                break;
            case 16:          //Key Shift
                MoveAndCam.PressShift();
                break;
            case 32:          //Key Spase
                MoveAndCam.PressSpase();
                break;
            case 27:          //Key Eec
                System.exit(0);
                break;
            case 17:          //Key Ctrl(Sprint)
                MoveAndCam.PressCtrl();
                break;
            case 69:          //Key e(dor...)

                break;
            case 70:          //Key f(reload)

                break;
            case 49:          //Key 1

                break;
            case 50:          //Key 2

                break;
            case 51:          //Key 3

                break;
            case 52:          //Key 4

                break;
            case 53:          //Key 5

                break;
            case 54:          //Key 6

                break;
            case 55:          //Key 7

                break;
            case 84:          //Key t Chat
                Command.setActive();
                break;
            case 77:          //Key m Map

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 87:
                MoveAndCam.ReleasedW();
                break;
            case 65:
                MoveAndCam.ReleasedA();
                break;
            case 83:
                MoveAndCam.ReleasedS();
                break;
            case 68:
                MoveAndCam.ReleasedD();
                break;
            case 16:
                MoveAndCam.ReleasedShift();
                break;
            case 32:
                MoveAndCam.ReleasedSpase();
                break;
            case 17:          //Key Ctrl(Sprint)
                MoveAndCam.ReleasedCtrl();
                break;
            case 69:          //Key e(dor...)

                break;
            case 70:          //Key f(reload)

                break;
            case 49:          //Key 1

                break;
            case 50:          //Key 2

                break;
            case 51:          //Key 3

                break;
            case 52:          //Key 4

                break;
            case 53:          //Key 5

                break;
            case 54:          //Key 6

                break;
            case 55:          //Key 7

                break;
            case 77:          //Key m Map

                break;
        }
        MoveAndCam.UpdateKey();
    }
}
