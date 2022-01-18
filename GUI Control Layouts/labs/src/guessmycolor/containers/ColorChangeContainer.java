package guessmycolor.containers;

import guessmycolor.buttons.ColorButton;

import java.awt.*;

public class ColorChangeContainer extends Container {
    ColorButton.RGBChangerButton redIncrement = new ColorButton.RGBChangerButton(Color.red, true);
    ColorButton.RGBChangerButton redDecrement = new ColorButton.RGBChangerButton(Color.red, false);
    ColorButton.RGBChangerButton greenIncrement = new ColorButton.RGBChangerButton(Color.green, true);
    ColorButton.RGBChangerButton greenDecrement = new ColorButton.RGBChangerButton(Color.green, false);
    ColorButton.RGBChangerButton blueIncrement = new ColorButton.RGBChangerButton(Color.red, true);
    ColorButton.RGBChangerButton blueDecrement = new ColorButton.RGBChangerButton(Color.red, false);


    ColorChangeContainer() {
        setLayout(new FlowLayout());
        setVisible(true);

    }
}
