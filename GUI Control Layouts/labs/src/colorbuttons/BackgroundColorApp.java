package colorbuttons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/*
        BASE FRAME FOR THE APP
 */
public class BackgroundColorApp extends JFrame {
    public static void main(String args[]) {
        new BackgroundColorApp();
    }

    ColorButton redB = new ColorButton(Color.red, "RED");
    ColorButton greenB = new ColorButton(Color.green, "GREEN");
    ColorButton blueB = new ColorButton(Color.blue, "BlUE");
    ColorButton grayB = new ColorButton(Color.gray, "GRAY");
    ColorButton whiteB = new ColorButton(Color.white, "WHITE");

    BackgroundColorApp() {
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
                        ((ColorButton) c).resizeButton(getContentPane());
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


}

