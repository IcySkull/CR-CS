package fractals;

import fractals.tools.FractalLogic;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SierpinskiTriangle extends FractalLogic {
    public void sierpinskiTriangle(int x, int y) {
        Rectangle2D scaledWindow = getScaledWindow();
        double w = scaledWindow.getWidth();
        double h = scaledWindow.getHeight();
        double wh = w*0.5;
        double hh = h*0.5;
        getGraphics().drawPolygon(
                new int[]{x, round(w) + x, round(wh) + x},
                new int[]{round(h) + y, round(h) + y, y},
                3
        );
        sierpinskiTriangle(wh*0.5 + x, hh + y, wh, hh, getIterations()-1);
    }

    public void sierpinskiTriangle(double x, double y, double w, double h, int it) {
        if (it > 0) {
            double wh = w*0.5;
            double hh = h*0.5;
            double whh = wh*0.5;
            getGraphics().drawPolygon(
                    new int[]{round(x), round(x+w), round(x+wh)},
                    new int[]{round(y), round(y), round(y+h)},
                    3
            );

            sierpinskiTriangle(x-whh, y+hh, wh, hh,  it-1);
            sierpinskiTriangle(x+whh, y-hh, wh, hh, it-1);
            sierpinskiTriangle(x+wh+whh, y+hh, wh, hh, it-1);
        }
    }

    @Override
    public void paint(Graphics g, Dimension size) {
        super.paint(g, size);
        sierpinskiTriangle(getDx(), getDy());
    }

}
