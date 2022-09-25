
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtonsAlt extends JFrame {

    Color c = Color.black;
    ColorButton redButton = new ColorButton(Color.red, "RED");
    ColorButton grnButton = new ColorButton(Color.green, "GREEN");

    TwoButtonsAlt() {
        setLayout(new FlowLayout());       // choose the layout manager
        redButton.addActionListener(redButton); // register the redButton object as its listener
        grnButton.addActionListener(grnButton); // register the grnButton object as its listener
        redButton.setBackground(Color.red);
        grnButton.setBackground(Color.green);
        // for both Buttons.
        add(redButton);
        add(grnButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args) {
        TwoButtonsAlt demo = new TwoButtonsAlt();
    }

    class ColorButton extends JButton implements ActionListener {

        Color col;

        public ColorButton(Color col, String s) {
            super(s);
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            getContentPane().setBackground(col);
            repaint();
        }
    }
}
