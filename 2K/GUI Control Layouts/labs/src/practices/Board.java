package practices;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board extends JPanel implements MouseInputListener {
    int layer;

    Board() {
        super();
        setVisible(true);
        setMinimumSize(new Dimension(300, 200));
        setPreferredSize(new Dimension(300, 200));
        layer = 0;

        addMouseListener(this);
        addComponents();
    }

    public void addComponents() {
        Layer first = new Layer(this, 0, 0, 50, 50, new Color(0,0,0,1));
        Layer second = new Layer(this, 25, 25, 50, 50, Color.pink);

        add(new Layer(this), layer++);
        add(first, layer++);
        add(second, layer++);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("nia");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
