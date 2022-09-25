package ui;

import fractals.tools.PrintableFractal;

import javax.swing.*;
import java.awt.*;

public class FractalPanel extends JPanel {
    private FractalGraphics fractalDisplay;

    public FractalPanel() {
        setLayout(new GridBagLayout());
        this.layFractalDisplay();
    }

    public void layFractalDisplay() {
        this.fractalDisplay = new FractalGraphics();
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.weightx = 1;
        constrains.weighty = 1;
        constrains.fill = GridBagConstraints.BOTH;
        this.add(fractalDisplay, constrains);
    }

    public void setFractal(PrintableFractal fractal) {
        fractalDisplay.setFractal(fractal);
    }

    public PrintableFractal getFractal() {
        return fractalDisplay.getFractal();
    }

    public FractalGraphics getFractalDisplay() {
        return this.fractalDisplay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
