package practices;

import javax.swing.*;
import java.awt.*;

public class IntFrame extends JFrame {
    public static void main(String[] args) {
        new IntFrame();
    }

    JInternalFrame frame = new JInternalFrame("tools", true, true, true, true);

    IntFrame() {
        super("hola");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        pack();

        frame.setPreferredSize(new Dimension(100, 100));
        frame.setVisible(true);
        add(frame);

    }
}
