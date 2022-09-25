package fractals.tools;

import math.MathOperator;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class FractalHandler extends FractalLogic {
    private MathOperator coordinateOperator;
    private PixelFractal fractal;

    public FractalHandler(PixelFractal fractal) {
        this.fractal = fractal;
    }

    private void paintFractal() {
        Rectangle2D scaledWindow = getScaledWindow();
        BufferedImage pixels = new BufferedImage(
                round(scaledWindow.getWidth()),
                round(scaledWindow.getHeight()),
                BufferedImage.TYPE_INT_ARGB
        );

        Rectangle2D drawBox = getViewOperator().getDrawBox();

        Rectangle2D itrBox = drawBox;

        int maxX = round(itrBox.getWidth());
        int maxY = round(itrBox.getHeight());
        for (int i = round(itrBox.getX()); i < maxX; i++) {
            for (int j = round(itrBox.getY()); j < maxY; j++) {
                Point2D orderedPair = coordinateOperator.getOrderedPair(new Point2D.Double(i, j));
                Color color = fractal.getPixelColor(orderedPair, getIterations());
                pixels.setRGB(i, j, color.getRGB());
            }
        }

        getGraphics().drawImage(pixels, 0, 0, null);
    }

    @Override
    public void paint(Graphics g, Dimension size) {
        super.paint(g, size);
        this.coordinateOperator = new MathOperator(fractal.getCoordinates(), getScaledWindow());
        paintFractal();
    }
}
