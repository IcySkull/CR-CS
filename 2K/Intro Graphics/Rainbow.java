import javax.swing.*;
import java.awt.*;

public class Rainbow extends JFrame {
        public Rainbow() {
            setTitle("Rianbow with Ovals");
            setSize(new Dimension(900, 400));
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(new DrawingPanel());

            Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
        }

        public static void main(String args[]) {
            new Rainbow();
        }

        private class DrawingPanel extends JPanel {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int step = Math.min(Math.round(getWidth()/11), Math.round(getHeight()/11));
//                int step = getHeight()/11;
                Color c = Color.black;
                for (int i = 2; i < 10; i++) {
                    switch (i) {
                        case 2:
                            c = Color.red; break;
                        case 3:
                            c = Color.orange; break;
                        case 4:
                            c = Color.yellow; break;
                        case 5:
                            c = Color.green; break;
                        case 6:
                            c = Color.blue; break;
                        case 7:
                            c = new Color(150, 0, 255); break;
                        case 8:
                            c = new Color(200, 0, 255); break;
                        case 9:
                            c = Color.white; break;
                    }
                    g.setColor(c);
//                    g.fillOval(i*10, i*10, i*10, i*10);
                    g.fillOval(step*i, step*i, (int)Math.round(getWidth()-step*i*2), (getHeight()-step*i)*2);
                }
            }
        }
}
