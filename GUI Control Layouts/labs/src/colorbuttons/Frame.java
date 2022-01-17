package colorbuttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.List;
import java.util.ArrayList;

public class Frame extends JFrame {
    public static void main(String args[]) {
        new Frame();
    }

    static ArrayList<ColorButton> colorButtons = new ArrayList<>();
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

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (ColorButton b : colorButtons) {
                    float fontSize = (float)(Math.min(getWidth()*0.07, getHeight()*0.07));
                    b.setPreferredSize(new Dimension((int)(Math.round(getWidth()*0.35)),
                            (int)(Math.round(getHeight()*0.25))));
                    b.setFont(b.getFont().deriveFont(fontSize));
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

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
            colorButtons.add(this);
        }
    }
}

