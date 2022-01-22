package practices;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {
    DrawingPanel board = new DrawingPanel();

    ContentPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.gray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 50, 50, 50);
        add(board);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
