import ui.FractalPanel;
import ui.FractalsBar;

import java.awt.*;
import javax.swing.*;


public class Fractal extends JFrame {
    FractalPanel panel;
    FractalsBar menuBar;

    public Fractal() {
     setTitle("Fractals");
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setWindow();
     initPanel();
     initMenuBar();
     pack();
    }

    private void initPanel() {
        panel = new FractalPanel();
        this.setContentPane(panel);
    }

    public void initMenuBar() {
        menuBar = new FractalsBar(panel);
        this.setJMenuBar(menuBar);
    }

    /**
     * Centers the window and sets width and height to the correct proportions
     */
    public void setWindow() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((int) (.5*(screensize.width)), (int)(.75*(screensize.height))));
        Dimension size = getPreferredSize();
        int dx = (int)((screensize.width-size.width)*.5);
        int dy = (int)((screensize.height-size.getHeight())*.5);
        setBounds(
                dx,
                dy,
                getWidth(),
                getHeight()
        );
    }

   public static void main(String[] args) {
        new Fractal();
   }
}
