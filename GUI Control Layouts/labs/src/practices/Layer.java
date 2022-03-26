package practices;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

class Layer extends JPanel implements MouseInputListener {
    int x1, y1, width, height;
    Color color;

    Layer(Board board) {
        super();
        setOpaque(true);
        setBounds(board.getBounds());
        x1 = 0;
        y1 = 0;
        width = 0;
        height = 0;
        color = Color.white;
        setBackground(color);
    }

    Layer(Board board, boolean isOpaque) {
        super();
        setOpaque(isOpaque);
        setBounds(board.getBounds());
        x1 = 0;
        y1 = 0;
        width = 0;
        height = 0;
        Color color = Color.white;
        setBackground(color);
    }

    Layer(Board board, int x, int y, int w, int h, Color c) {
        super();
        setOpaque(false);
        setBounds(board.getBounds());
        x1 = x;
        y1 = y;
        width = w;
        height = h;
        color = c;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillRect(x1, y1, width, height);
    }

    @Override
    public String toString() {
        return color.toString();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("first layer");
    }

    @Override
    public void mousePressed(MouseEvent e) {

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