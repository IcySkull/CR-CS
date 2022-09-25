import java.awt.*;

public class Squares2 {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(400, 300);
        panel.setBackground(Color.cyan);
        Graphics g = panel.getGraphics();
        drawSquare(50, 50, 20, g);
        System.out.println("hola consola");
        drawSquare(250, 10, 20, g);
        drawSquare(180, 115, 20, g);
    }

    public static void drawSquare(int x1, int y1, int step, Graphics window) {
        window.setColor(Color.RED);
        for (int i = 1; i<=5; i++) {
            window.drawRect(x1, y1, i*step, i*step);
        }
        window.setColor(Color.BLACK);
        window.drawLine(x1, y1, 5*step+x1, 5*step+y1);
    }
}