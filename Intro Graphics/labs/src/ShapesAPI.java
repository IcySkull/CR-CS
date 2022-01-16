import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ShapesAPI extends JFrame {
    static int response = Integer.parseInt(JOptionPane.showInputDialog(null, "1 para dibujar rectangulos\n2 para dibujar ovalos"));

    public ShapesAPI () {
        setTitle("Shapes");
        setSize(new Dimension(500, 500));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ShapesAPI.DrawingPanel3());

        //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public static void main(String[] args) {
        new ShapesAPI();
    }

    private class DrawingPanel3 extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int size = 50;
            int nShapes = Math.min(getWidth()/(25), getHeight()/(25));
            drawShape(response, size, nShapes, g);
        }

        public void drawShape(int type, int size, int nShapes, Graphics g) {
            if (!(nShapes != 0 && size+size != getHeight())) {
            }
            else if (type == 1) {
                g.drawRect(size, size, size, size);
                drawShape(type, size+10, nShapes-1, g);
            }
            else if (type == 2) {
                g.drawOval(size, size, size, size);
                drawShape(type, size+10, nShapes-1, g);
            }
        }
    }
}
