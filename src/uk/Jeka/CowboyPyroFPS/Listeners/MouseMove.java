package uk.Jeka.CowboyPyroFPS.Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import uk.Jeka.CowboyPyroFPS.Player.MoveAndCam;

public class MouseMove implements MouseMotionListener{

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MoveAndCam.MouseMove(e.getX(), e.getY());
    }
}