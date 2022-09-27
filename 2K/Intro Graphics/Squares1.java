import java.awt.*;

public class Squares1 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 200);
        panel.setBackground(Color.cyan);
        Graphics g = panel.getGraphics();
        for (int i = 1; i<=5; i++) {
            g.setColor(Color.RED);
            g.drawRect(50, 50, i*20, i*20);
        }
        g.setColor(Color.BLACK);
        g.drawLine(50, 50, 150, 150);
    }
}
