package practices;

import javax.swing.*;
import java.awt.*;

public class BasicApp extends JFrame {
     public static void main(String[] args) {
         JFrame frame = new BasicApp();
     }
     
     ContentPanel pane = new ContentPanel();
     Board board = new Board();

     BasicApp() {
         super("test");
         setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new GridBagLayout());

//         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//         setBounds(
//                 (int)(Math.round(.25 * (screen.width))),
//                 (int)(Math.round(.25 * (screen.height))),
//                 (int)(Math.round(.5 * (screen.width))),
//                 (int)(Math.round(.5 * (screen.height)))
//         );

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(50, 50, 50, 50);

         setContentPane(pane);
         add(board, gbc);
         pack();
     }
}

