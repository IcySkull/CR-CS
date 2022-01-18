package guessmycolor;

import javax.swing.*;
import java.awt.*;

/*
        BASE FRAME OF THE APP
 */
public class GuessMyColorApp extends JFrame {
    Color win;
    Color current;

    GuessMyColorApp() {
        setTitle("Guess My Color Game");
        setLayout(new GridBagLayout());
        setVisible(true);
        setPreferredSize(new Dimension(500, 500));
        pack();

        // Grid configuration
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Tittle of the Game
        c.gridy = 0;
        JButton title = new JButton("Guess My Color");
//        title.s
//        title.getGraphics() -> paint();
//        add()
    }



    // END OF THE Game CLASS
}
