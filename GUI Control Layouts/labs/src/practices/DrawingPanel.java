package practices;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    DrawingPanel() {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));
        setBackground(Color.white);
        add(new JButton());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(50, 50, 50, 50);
    }
}
