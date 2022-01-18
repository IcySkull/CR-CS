package guessmycolor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.TileObserver;
import java.util.Arrays;

/*
        BASE FRAME OF THE APP
 */
public class GuessMyColorApp extends JFrame {
    public static void main(String args[]) {
        new GuessMyColorApp();
    }

    Color win;
    Color current;

    GuessMyColorApp() {
        setTitle("Guess My Color Game");
        setLayout(new GridBagLayout());
        setVisible(true);
        setPreferredSize(new Dimension(500, 500));
        pack();

        // Grid configuration
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Tittle of the Game
        c.gridy = 0;
        TitleContainer top = new TitleContainer();

        // Event Listeners
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (Component c: getContentPane().getComponents()) {
                    if (c instanceof Resizable) {
                        ((Resizable) c).resize();
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
        add(top);
    }

    public class TitleContainer extends Container implements Resizable {
        final public   double HEIGHT_FACTOR = 0.1;
        TitleButton title = new TitleButton("Guess My Color");

        TitleContainer() {
            setLayout(new FlowLayout());
            setVisible(true);
            setPreferredSize(new Dimension(getContentPane().getWidth(), getContentPane().getHeight()));
            resize();
            pack();

            add(title);
        }

        public void resize() {
            Component c = getContentPane();
            setPreferredSize(new Dimension(c.getWidth(), (int)(c.getHeight()*HEIGHT_FACTOR)));
            for (Component part : getComponents()) {
                if (part instanceof TitleButton) {
                    ((TitleButton) part).resize();
                }
            }
        }

        public class TitleButton extends JLabel implements Resizable {
            TitleButton(String str) {
                super(str, SwingConstants.CENTER);
                setFont(new Font("TimesRoman", Font.BOLD+Font.ITALIC, (int)(getContentPane().getWidth()*0.07)));
                setForeground(Color.white);
                setBackground(Color.BLACK);
                setOpaque(true);
                resize();
            }

            public void resize() {
                Container c = getContentPane();
                float fontSize = (float) (Math.min(c.getWidth() * 0.07, c.getHeight() * 0.2));
                setPreferredSize(new Dimension(c.getWidth(), (int)(c.getHeight()*HEIGHT_FACTOR)));
                setFont(getFont().deriveFont(fontSize));
            }

        }
        // END OF THE TitleButton CLASS
    }
    // END OF THE TitleContainer CLASS

    /*
        The container that will be used to draw the rects.
     */

    /**
     *  Container for the RGB buttons that will change the color of the rects.
     */
    public class ColorChangeContainer extends Container {
        public final double HEIGHT_FACTOR = 0.4;
        RGBChangerButton redIncrement = new RGBChangerButton(Color.red, true);
        RGBChangerButton redDecrement = new RGBChangerButton(Color.red, false);
        RGBChangerButton greenIncrement = new RGBChangerButton(Color.green, true);
        RGBChangerButton greenDecrement = new RGBChangerButton(Color.green, false);
        RGBChangerButton blueIncrement = new RGBChangerButton(Color.red, true);
        RGBChangerButton blueDecrement = new RGBChangerButton(Color.red, false);

        ColorChangeContainer() {
            setLayout(new FlowLayout());
            setVisible(true);

        }

        /**
         * A button with color
         */
        public class ColorButton extends JButton implements Resizable {
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
            public void resize() {
                Component c = getContentPane();
                float fontSize = (float) (Math.min(c.getWidth() * 0.07, c.getHeight() * 0.07));
                setPreferredSize(new Dimension((int) (Math.round(c.getWidth() * 0.35)),
                        (int) (Math.round(c.getHeight() * 0.25))));
                setFont(getFont().deriveFont(fontSize));
            }
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
            // END OF THE RGBChangerButton CLASS
        }
        // END OF ColorButton CLASS
    }
    // END OF THE ColorChangeContainer CLASS
}
// END OF THE Game CLASS

