import javax.swing.*;
import java.awt.*;

public class RandomShapes extends JFrame {

    public RandomShapes () {
        setTitle("Random Shapes");
        setSize(new Dimension(400, 400));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new DrawRShapes());
    }

    public static void main(String args[]) {
        new RandomShapes();
    }

    private class DrawRShapes extends JPanel {
        Graphics g;
        int x1, y1, sizeX, sizeY, width, height;

        public void paintComponent(Graphics window) {
            g = window;
            width = getWidth();
            height = getHeight();
            drawShape(10);
        }

        private void drawShape(int times) {
            if (times != 0) {
                x1 = (int)(Math.random()*(width+1));
                y1 = (int)(Math.random()*(height+1));
                sizeX = (int)(Math.random()*(width+1));
                sizeY = (int)(Math.random()*(height+1));
                g.setColor(new Color((int)(Math.random()*256),
                        (int)(Math.random()*256), (int)(Math.random()*256)));

                if (Math.random() > 0.5) {
                    g.fillRect(x1, y1, sizeX, sizeY);
                } else {
                    g.fillOval(x1, y1, sizeX, sizeY);
                }
                drawShape(--times);
            }
        }
    }
}
