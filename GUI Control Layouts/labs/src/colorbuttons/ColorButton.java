package colorbuttons;

import javax.swing.*;
import java.awt.*;

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
        setBackground(this.color);
    }

    /**
     * A method normally called by componentResized to resize the Buttons of its container.
     * It uses the width and the height of the container to multiply it by a scale factor to
     * resize the button bases on its container.
     */
    public void resizeButton(Container c) {
        float fontSize = (float)(Math.min(c.getWidth()*0.07, c.getHeight()*0.07));
        setPreferredSize(new Dimension((int)(Math.round(c.getWidth()*0.35)),
                (int)(Math.round(c.getHeight()*0.25))));
        setFont(getFont().deriveFont(fontSize));
    }
}

