
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtonsFinalVersion extends JFrame{

    JButton redButton = new JButton("Red");
    JButton grnButton = new JButton("Green");

    TwoButtonsFinalVersion() 
    {
        setLayout(new FlowLayout());       // choose the layout manager
        redButton.addActionListener(ae -> getContentPane().setBackground(redButton.getBackground()));     
        grnButton.addActionListener(ae -> getContentPane().setBackground(grnButton.getBackground()));
        redButton.setBackground(Color.red);
        grnButton.setBackground(Color.green);

        add(redButton);
        add(grnButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setLocationRelativeTo(null);
        pack();
    }

    public static void main(String[] args) {
        TwoButtonsFinalVersion demo = new TwoButtonsFinalVersion();
    }
}