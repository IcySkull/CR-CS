package guessmycolor.buttons;

import guessmycolor.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButton extends JButton {
    private Color color;

    ColorButton(Color color, String str) {
        super(str);
        this.color = color;
        setBackground(this.color);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * A method normally called by componentResized to resize the Buttons of its container.
     * It uses the width and the height of the container to multiply it by a scale factor to
     * resize the button bases on its container.
     */
    public void resizeButton(Container c) {
        float fontSize = (float) (Math.min(c.getWidth() * 0.07, c.getHeight() * 0.07));
        setPreferredSize(new Dimension((int) (Math.round(c.getWidth() * 0.35)),
                (int) (Math.round(c.getHeight() * 0.25))));
        setFont(getFont().deriveFont(fontSize));
    }

    /*
        The inner class of ColorButton to provide the sources for the functionalities of the
        ActionListener
     */
    public class RGBChangerButton extends ColorButton implements ActionListener {
        static final char INCREMENT = '+';
        static final char DECREMENT = '-';
        private boolean increment;

        /*
            The base constructor to provide a functional RGBChangerButton
         */
        RGBChangerButton(Color color, boolean increment) {
            super(color, Character.toString(increment ? INCREMENT : DECREMENT));
            if (color.toString().equalsIgnoreCase("red") ||
                    color.toString().equalsIgnoreCase("green") ||
                    color.toString().equalsIgnoreCase("blue")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid RGB color :)",
                        "Error Message", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (increment) {
//                        color = new Color(color., , );
            }
        }
    }
    // END OF THE RGBChangerButton CLASS
}
// END OF THE ColorButton CLASS

