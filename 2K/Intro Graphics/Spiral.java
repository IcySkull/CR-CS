import javax.swing.*;
import java.awt.*;

public class Spiral extends JFrame {
    Spiral () {
        setTitle("Spirals");
        setSize(new Dimension(600,600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new DrawingSpirals());

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5*(screensize.width-getWidth())), (int)(.5*(screensize.height-getHeight())), getWidth(), getHeight() );
    }

    public static void main(String[] args) {
        new Spiral();
    }

    private class DrawingSpirals extends JPanel {
        int[] pt, dx;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int division = Math.min(Math.round(getHeight()/11), Math.round(getWidth()/11));
            pt = new int[]{(int)Math.round(getWidth()/2), (int)Math.round(getHeight()/2)};
            dx = new int[]{0, 1};
            g.setColor(Color.red);
            drawSpiral(division, 1, g);
        }

        private void drawSpiral(int div, int steps, Graphics g) {
            if (drawLines(2, div, steps, g)) {
                drawSpiral(div, ++steps, g);
            }
        }

        private boolean drawLines(int turns, int div, int steps, Graphics g) {
            if (turns != 0) {
                int x2 = pt[0] + steps * div * dx[0];
                int y2 = pt[1] + steps * div * dx[1];
                if ( ( (x2 <= getWidth() && x2 >= 0) && (y2 <= getHeight() && y2 >= 0) ) )  {
                    g.drawLine(pt[0], pt[1], x2, y2);
                    int o = dx[0];
                    dx[0] = dx[1];
                    dx[1] = o * -1;
                    pt[0] = x2;
                    pt[1] = y2;
                    drawLines(--turns, div, steps, g);
                }
                else
                    return false;
            }
            return true;
        }
    }
}
