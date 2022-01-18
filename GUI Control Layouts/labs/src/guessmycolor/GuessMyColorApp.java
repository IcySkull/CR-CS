package guessmycolor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/*
        BASE FRAME OF THE APP
 */
public class GuessMyColorApp extends JFrame {
    public static void main(String args[]) {
        new GuessMyColorApp();
    }

    Color win;
    Color guess;
    final int step = 15;

    TitleContainer top = new TitleContainer();
    DrawingContainer middle = new DrawingContainer();
    ColorChangeContainer bottom = new ColorChangeContainer();

    GuessMyColorApp() {
        setTitle("Guess My Color Game");
        setLayout(new GridBagLayout());
        setVisible(true);
        setPreferredSize(new Dimension(700, 500));
        pack();

        startGame();

        // Grid configuration
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Tittle of the Game
        c.gridy = 0;
        add(top, c);

        // Draw panel of the Game
        c.gridy = 1;
        add(middle, c);

        // RGB changer buttons of the Game
        c.gridy = 2;
        add(bottom, c);

        // Event Listeners
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (Component c : getContentPane().getComponents()) {
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
    }
    // End of constructor

    /**
     * A helper method generate new colors for the game
     */
    public void startGame() {
        guess = new Color(createRGB(), createRGB(), createRGB());
        win = new Color(createRGB(), createRGB(), createRGB());
        while (guess.equals(win)) {
            guess = new Color(createRGB(), createRGB(), createRGB());
        }
    }

    /**
     * A method to create a random int within the range [0, 255] and reachable by the steps.
     * Reachable by the steps is a value that is divisible by the steps
     * @return the new int in a range within [0, 255] and reachable
     */
    public int createRGB() {
        int rgb;
        do {
            rgb = (int) (Math.random()*256);
        }
        while (!(rgb % step == 0));
        return rgb;
    }

    public class TitleContainer extends Container implements Resizable {
        final public double HEIGHT_FACTOR = 0.2;
        TitleButton title = new TitleButton("Guess My Color");

        TitleContainer() {
            setLayout(new FlowLayout());
            setVisible(true);
            setBackground(Color.BLACK);
            add(title);

            resize();
        }

        public void resize() {
            Component c = getContentPane();
            setPreferredSize(new Dimension(c.getWidth(), (int)(Math.round(c.getHeight()*HEIGHT_FACTOR))));
            for (Component part : getComponents()) {
                if (part instanceof Resizable) {
                    ((Resizable) part).resize();
                }
            }
        }

        public class TitleButton extends JLabel implements Resizable {
            TitleButton(String str) {
                super(str, SwingConstants.CENTER);
                setFont(new Font("TimesRoman", Font.BOLD+Font.ITALIC,
                        (int)(getContentPane().getWidth()*0.07)));
                setForeground(Color.white);
                setBackground(Color.BLACK);
                setOpaque(true);
            }

            public void resize() {
                Component c = getContentPane();
                float fontSize = (float) (Math.min(c.getWidth() * 0.07, c.getHeight() * 0.2));
                setPreferredSize(new Dimension(c.getWidth(), (int)(Math.round(c.getHeight()*HEIGHT_FACTOR))));
                setFont(getFont().deriveFont(fontSize));
            }

        }
        // END OF THE TitleButton CLASS
    }
    // END OF THE TitleContainer CLASS

    /*
        The container that will be used to draw the rectangles of the game.
     */
    public class DrawingContainer extends Container implements Resizable {
        public final double HEIGHT_FACTOR = 0.4;

        DrawingContainer() {
            setLayout(new FlowLayout());
            setVisible(true);

            DrawingPanel panel = new DrawingPanel();

            add(panel);
            resize();
        }

        // Resize method for the container of the DrawingPanel
        @Override
        public void resize() {
            Component c = getContentPane();
            setPreferredSize(new Dimension(c.getWidth(), (int)(Math.round(c.getHeight()*HEIGHT_FACTOR))));
            for (Component part : getComponents()) {
                if (part instanceof Resizable) {
                    ((Resizable) part).resize();
                }
            }
        }

        /*
            The Panel where the Rects for the game will be created.
         */
        public class DrawingPanel extends JPanel implements Resizable {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                Component c = getContentPane();
                int xStep = (int)(Math.round(c.getWidth()/5.0));
                int yStep = (int)(Math.round(c.getHeight()/22.0));
                g.setColor(guess);
                g.fillRect(xStep, yStep, xStep, (int)(c.getHeight()*0.3));
                g.setColor(win);
                g.fillRect(xStep*3, yStep, xStep, (int) (c.getHeight()*0.3));
            }

            // Resize method for the DrawingPanel
            @Override
            public void resize() {
                Component c = getContentPane();
                setPreferredSize(new Dimension(c.getWidth(), (int)(Math.round(c.getHeight()*HEIGHT_FACTOR))));
            }
        }
        // END OF THE DrawingPanel CLASS
    }
    // END OF THE DrawingContainer CLASS

    /**
     *  Container for the RGB buttons that will change the color of the rects.
     */
    public class ColorChangeContainer extends Container implements Resizable {
        public final double HEIGHT_FACTOR = 0.2;
        RGBChangerButton redIncrement = new RGBChangerButton(Color.red, true);
        RGBChangerButton redDecrement = new RGBChangerButton(Color.red, false);
        RGBChangerButton greenIncrement = new RGBChangerButton(Color.green, true);
        RGBChangerButton greenDecrement = new RGBChangerButton(Color.green, false);
        RGBChangerButton blueIncrement = new RGBChangerButton(Color.blue, true);
        RGBChangerButton blueDecrement = new RGBChangerButton(Color.blue, false);

        // default constructor
        ColorChangeContainer() {
            setLayout(new FlowLayout());
            setVisible(true);

            add(redIncrement);
            add(redDecrement);
            add(greenIncrement);
            add(greenDecrement);
            add(blueIncrement);
            add(blueDecrement);

            resize();

            // Event Listeners
            redIncrement.addActionListener(redIncrement);
            redDecrement.addActionListener(redDecrement);
            greenIncrement.addActionListener(greenIncrement);
            greenDecrement.addActionListener(greenDecrement);
            blueIncrement.addActionListener(blueIncrement);
            blueDecrement.addActionListener(blueDecrement);

        }

        /**
         * The resize method for the Container of the RGB changer buttons
         */
        @Override
        public void resize() {
            Component c = getContentPane();
            setPreferredSize(new Dimension(c.getWidth(), (int)(Math.round(c.getWidth()*HEIGHT_FACTOR))));
            for (Component parts : getComponents()) {
                if (parts instanceof Resizable) {
                    ((Resizable) parts).resize();
                }
            }
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
                float fontSize = (float) (Math.min(c.getWidth() * 0.15, c.getHeight() * 0.07));
                setPreferredSize(new Dimension((int)(c.getWidth() * 0.15),
                        (int)(Math.round(c.getHeight() * HEIGHT_FACTOR))));
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
            private final boolean increment;

            /*
                The base constructor to provide a functional RGBChangerButton
             */
            RGBChangerButton(Color color, boolean increment) {
                super(color, Character.toString(increment ? INCREMENT : DECREMENT));
                this.increment = increment;
                if (!(color.equals(Color.red) || color.equals(Color.green) || color.equals(Color.blue))) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid RGB color for the buttons :)",
                            "Error Message", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }

            /**
             * This method is called when the RGB changer buttons are clicked. Therefore, a new color is generated to the
             * create a new rect of the guess of the player.
             * @param e the event of the RGB changer button when clicked
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int sign = increment ? 1 : -1;

                if (getColor().equals(Color.red)) {
                    int col = guess.getRed();
                    int newCol = col + step*sign;
                    if (newCol < 0 || newCol > 255) {
                    } else
                    guess = new Color(newCol,
                            guess.getGreen(), guess.getBlue());
                }

                else if (getColor().equals(Color.green)) {
                    int col = guess.getGreen();
                    int newCol = col + step*sign;
                    if (newCol < 0 || newCol > 255) {
                    } else
                    guess = new Color(guess.getRed(),
                            newCol, guess.getBlue());
                }

                else {
                    int col = guess.getBlue();
                    int newCol = col + step*sign;
                    if (newCol < 0 || newCol > 255) {
                    } else
                    guess = new Color(guess.getRed(),
                            guess.getGreen(), newCol);
                }

                System.out.println(guess + " guess");
                System.out.println(win + " goal");

                if (guess.equals(win)) {
                    int response = JOptionPane.showConfirmDialog(null,
                            "Congratulations! You guessed my Color.\nChoose: Yes, to keep plating. No, to close the program",
                            "Winner", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        dispose();
                        new GuessMyColorApp();
                    }

                    else {
                        System.exit(0);
                    }
                }

                else {
                    middle.paint(middle.getGraphics());
                }
            }
            // END OF THE RGBChangerButton CLASS
        }
        // END OF ColorButton CLASS
    }
    // END OF THE ColorChangeContainer CLASS
}
// END OF THE Game CLASS

