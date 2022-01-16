
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtonsAlt2 extends JFrame{

    JButton redButton = new JButton("Red");
    JButton grnButton = new JButton("Green");

    TwoButtonsAlt2() 
    {
        setLayout(new FlowLayout());       // choose the layout manager
        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                 getContentPane().setBackground(((JButton)ae.getSource()).getBackground());
            }
        });
        grnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                 getContentPane().setBackground(((JButton)ae.getSource()).getBackground());
            }
        });
        redButton.setBackground(Color.red);
        grnButton.setBackground(Color.green);

        add(redButton);
        add(grnButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args) {
        TwoButtonsAlt2 demo = new TwoButtonsAlt2();
    }
}