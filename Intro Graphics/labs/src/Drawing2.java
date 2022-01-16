import javax.swing.*;
import java.awt.*;

public class
Drawing2 extends JFrame {

    public Drawing2() {
        setTitle("Drawing2");
        setSize(new Dimension(300, 300));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new DrawingPanel2());

        //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public static void main(String[] args) {
        new Drawing2();
    }

    private class DrawingPanel2 extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int lines = 15;
            int stepX = getWidth() / lines;
            int stepY = getHeight() / lines;

            for (int i = 1; i<=lines; i++) {
                g.drawLine(0, stepY*i, stepX*i, getHeight());
                g.drawLine(stepX*i, getHeight(), getWidth(), getHeight()-stepY*i);
                g.drawLine(getWidth(), getHeight()-stepY*i, getWidth()-stepX*i, 0);
                g.drawLine(getWidth()-stepX*i, 0, 0, stepY*i);
            }
        }
    }
}
