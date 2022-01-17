package colorbuttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
        BASE FRAME FOR THE APP
 */
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

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component[] comps = getContentPane().getComponents();
                // Resize all the components of the container
                for (Component c : comps) {
                    if (c instanceof ColorButton) {
                        ((ColorButton) c).resizeButton();
                    }
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

    /*
            Resizable buttons that create a JButton used to modify the background of its container
            by the color established.
     */
    public class ColorButton extends JButton {
        Color color;

        /**
         * The base constructor to create a successful button with all of its properties.
         * @param color the color that will be used to set the color of the button and its background
         * @param str the text of the Button that will be displayed
         */
        ColorButton(Color color, String str) {
            super(str);
            this.color = color;
            setPreferredSize(new Dimension(80, 50));
            setBackground(this.color);
        }

        /**
         * A method normally called by componentResized to resize the Buttons of its container.
         * It uses the width and the height of the container to multiply it by a scale factor to
         * resize the button bases on its container.
         */
        public void resizeButton() {
            Container c = getContentPane();
            float fontSize = (float)(Math.min(c.getWidth()*0.07, c.getHeight()*0.07));
            setPreferredSize(new Dimension((int)(Math.round(c.getWidth()*0.35)),
                    (int)(Math.round(c.getHeight()*0.25))));
            setFont(getFont().deriveFont(fontSize));
        }
    }
}

