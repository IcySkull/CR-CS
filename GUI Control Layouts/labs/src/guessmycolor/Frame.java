package guessmycolor;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {


    public class ColorButton extends JButton {
        Color color;

        ColorButton(Color color, String str) {
            super(str);
            this.color = color;
            setBackground(this.color);
        }


//        public class AddButton extends ColorButton {
//
//        }
    }
}
