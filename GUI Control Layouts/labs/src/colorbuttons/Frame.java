package colorbuttons;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public static void main(String args[]) {
        new Frame();
    }

    ColorButton redB = new ColorButton(Color.red, "RED");
    ColorButton greenB = new ColorButton(Color.green, "GREEN");
    ColorButton blueB = new ColorButton(Color.blue, "BlUE");
    ColorButton grayB = new ColorButton(Color.gray, "GRAY");
    ColorButton whiteB = new ColorButton(Color.white, "WHITE");

    Frame() {
        setTitle("Color Buttons Background");
        setLayout(new FlowLayout());
        setVisible(true);
        setPreferredSize(new Dimension(500, 500));
        pack();

        redB.addActionListener(ae -> getContentPane().setBackground(redB.color));
        greenB.addActionListener(ae -> getContentPane().setBackground(greenB.color));
        blueB.addActionListener(ae -> getContentPane().setBackground(blueB.color));
        grayB.addActionListener(ae -> getContentPane().setBackground(grayB.color));
        whiteB.addActionListener(ae -> getContentPane().setBackground(whiteB.color));

        add(redB);
        add(greenB);
        add(blueB);
        add(grayB);
        add(whiteB);
    }

    public class ColorButton extends JButton {
        Color color;

        ColorButton(Color color, String str) {
            super(str);
            this.color = color;
            setPreferredSize(new Dimension(80, 50));
            setBackground(this.color);
        }
    }
}

