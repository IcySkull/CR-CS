package practices;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.beans.PropertyVetoException;

public class BasicApp extends JFrame {
     public static void main(String[] args) throws PropertyVetoException {
         JFrame frame = new BasicApp();
     }
     
     ContentPanel pane = new ContentPanel();
     Container panel = new Container();
     Board board = new Board();

     BasicApp() throws PropertyVetoException {
         super("test");
         setVisible(true);
         GridBagLayout gbl = new GridBagLayout();
         setLayout(gbl);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         pack();

         Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
         setBounds(
                 (int)(Math.round(.25 * (screen.width))),
                 (int)(Math.round(.25 * (screen.height))),
                 (int)(Math.round(.5 * (screen.width))),
                 (int)(Math.round(.5 * (screen.height)))
         );

         setContentPane(pane);

         gbl.rowWeights = new double[] {1};
         gbl.columnWeights = new double[] {0, 1};

         GridBagConstraints gbc = new GridBagConstraints();
         setLayout(gbl);
         gbc.fill = GridBagConstraints.BOTH;
         gbc.gridy = 0;

         gbc.gridx = 0;
         panel.setBackground(new Color(100, 100, 100));
         panel.setVisible(true );
         add(panel, gbc);

         gbc.gridx = 1;
         add(board, gbc);

     }
}

