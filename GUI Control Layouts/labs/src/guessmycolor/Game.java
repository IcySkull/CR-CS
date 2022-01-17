package guessmycolor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
        BASE FRAME OF THE APP
 */
public class Game extends JFrame {
    Color win;
    Color current;

    Game() {
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
