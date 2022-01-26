package paintproject;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static paintproject.PaintApp.PaintUser.rotate;


/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    BASE FRAME OF THE APP
 */
public class PaintApp extends JFrame {
    public static void main(String[] args) {
        initApp();
    }

    // Instance variables
    PaintUser user = new PaintUser();
    ToolsComponent toolsPanel = new ToolsComponent();
    TabbedTables tables = new TabbedTables();
    GridBagConstraints gbc;

    PaintApp() {
        setTitle("Paint");
        setGridBagLayout(new GridBagLayout());
        setVisible(true);
        setConstrains();
        setDimension(1200, 650);
        setBackground(Color.white);

        addComponents();
        centerFrame();
        pack();
    }

    /**
     * Sets the dimension of the frame
     */
    private void setDimension(int width, int height) {
        Dimension size = new Dimension(width, height);
        setSize(size);
        setPreferredSize(size);
    }

    public void setConstrains() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
    }

    // set the Table component to occupy all the space disposable and the Tools component to a fixed width space
    public void setGridBagLayout(GridBagLayout gbl) {
        setLayout(gbl);
        gbl.columnWeights = new double[]{0,1};
        gbl.rowWeights = new double[]{1};
        gbl.columnWidths = new int[]{200}; // fixed width space for the first column
    }

    public void addComponents() {
        initMenu();

        // add tools panels
        gbc.gridx = 0;
        add(toolsPanel, gbc);

        // add tab tables
        gbc.gridx = 1;
        add(tables, gbc);

        tables.initTable("first", new Table());
    }

    /**
     * Creates an interface menu to interact with the user
     */
    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuView = new JMenu("View");

        // menuFile items
        JMenuItem addFile = new JMenuItem("New draw");
        addFile.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(
                    null,
                    "Enter the name of the draw",
                    "New draw",
                    JOptionPane.INFORMATION_MESSAGE);
            String width = JOptionPane.showInputDialog(
                    null,
                    "Enter the width of the draw",
                    "New draw",
                    JOptionPane.INFORMATION_MESSAGE);
            String height = JOptionPane.showInputDialog(
                    null,
                    "Enter the height of the draw",
                    "New draw",
                    JOptionPane.INFORMATION_MESSAGE);

            int widthNum = Integer.parseInt(width);
            int heightNum = Integer.parseInt(height);

            tables.initTable(title, widthNum, heightNum);
        });

        // menuView items
        JRadioButtonMenuItem editorTool = new JRadioButtonMenuItem("Editor");
        editorTool.addItemListener(e -> {
            if (editorTool.isSelected())
                toolsPanel.addTool(toolsPanel.editorTool);
            else
                toolsPanel.removeTool(toolsPanel.editorTool);
            toolsPanel.repaint();
        });
        editorTool.setSelected(true);

        // Add menuFile items
        menuFile.add(addFile);

        // Add menuView items
        menuView.add(editorTool);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        setJMenuBar(menuBar);
    }

    /**
     * Centers the frame of the PaintApp
     */
    public void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (Math.round((screenSize.getWidth() - getWidth()) / 2));
        int y = (int) (Math.round((screenSize.getHeight() - getHeight()) / 2));
        setLocation(x, y);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        A formalized definition of a user of the app. It will provide accessible
        information of the user so the App will interact with these variables to
        improve the experience of the user.
     */
    public class PaintUser {
        //Instance variables
        Color color;
        int stroke;
        int tool;

        PaintUser() {
            color = Color.darkGray;
            stroke = 12;
            tool = 0;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setStroke(int stroke) {
            this.stroke = stroke;
        }

        public void setTool(int tool) {
            this.tool = tool;
        }

        public Color getColor() {
            return color;
        }

        public int getStroke() {
            return stroke;
        }

        public int getTool() {
            return tool;
        }

        /**
         * The following method rotates the provided BufferedImage with the angles introduced
         * @param bimg the BufferedImage to be rotated
         * @param angle the angle desired to rotate the BufferedImage
         * @return the rotated Buffered Image
         */
        public static BufferedImage rotate(BufferedImage bimg, Double angle) {
            double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                    cos = Math.abs(Math.cos(Math.toRadians(angle)));
            int w = bimg.getWidth();
            int h = bimg.getHeight();
            int neww = (int) Math.floor(w*cos + h*sin),
                    newh = (int) Math.floor(h*cos + w*sin);
            BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
            Graphics2D graphic = rotated.createGraphics();
            graphic.translate((neww-w)/4, (newh-h)/4);
            graphic.rotate(Math.toRadians(angle), 0, 0);
            graphic.drawRenderedImage(bimg, null);
            graphic.dispose();
            return rotated;
        }

        @Override
        public String toString() {
            return "PaintUser{" +
                    "color=" + color +
                    ", stroke=" + stroke +
                    '}';
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        A tabbed interface where the user changes between Board objects
     */
    class TabbedTables extends JTabbedPane {
        // Instance variables
        int index;

        public void setGridBagLayout(GridBagLayout gbl) {
            setLayout(gbl);
        }

        // init the custom tabs with the Table components
        public void initTable(String title, int width, int height) {
            addTab(title, new Table(width, height));
            setTabComponentAt(index++, new ButtonTabComponent(this));
        }

        public void initTable(String title, Table table) {
            addTab(title, table);
            setTabComponentAt(index++, new ButtonTabComponent(this));
        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            The component that will be used as the tab. This class was not created by me.
            It was implemented from an official solution of oracle.
         */
        public class ButtonTabComponent extends JPanel {
            private final JTabbedPane pane;

            public ButtonTabComponent(final JTabbedPane pane) {
                //unset default FlowLayout' gaps
                super(new FlowLayout(FlowLayout.LEFT, 0, 0));
                if (pane == null) {
                    throw new NullPointerException("TabbedPane is null");
                }
                this.pane = pane;
                setOpaque(false);

                //make JLabel read titles from JTabbedPane
                JLabel label = new JLabel() {
                    public String getText() {
                        int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                        if (i != -1) {
                            return pane.getTitleAt(i);
                        }
                        return null;
                    }
                };

                add(label);
                //add more space between the label and the button
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                //tab button
                JButton button = new TabButton();
                add(button);
                //add more space to the top of the component
                setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
            }

            private class TabButton extends JButton implements ActionListener {
                public TabButton() {
                    int size = 17;
                    setPreferredSize(new Dimension(size, size));
                    setToolTipText("close this tab");
                    //Make the button looks the same for all Laf's
                    setUI(new BasicButtonUI());
                    //Make it transparent
                    setContentAreaFilled(false);
                    //No need to be focusable
                    setFocusable(false);
                    setBorder(BorderFactory.createEtchedBorder());
                    setBorderPainted(false);
                    //Making nice rollover effect
                    //we use the same listener for all buttons
                    addMouseListener(buttonMouseListener);
                    setRolloverEnabled(true);
                    //Close the proper tab by clicking the button
                    addActionListener(this);
                }

                public void actionPerformed(ActionEvent e) {
                    int i = pane.indexOfTabComponent(ButtonTabComponent.this);
                    if (i != -1) {
                        pane.remove(i);
                        index--;
                    }
                }

                //we don't want to update UI for this button
                public void updateUI() {
                }

                //paint the cross
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g3 = (Graphics2D) g.create();
                    //shift the image for pressed buttons
                    if (getModel().isPressed()) {
                        g3.translate(1, 1);
                    }
                    g3.setStroke(new BasicStroke(2));
                    g3.setColor(Color.BLACK);
                    if (getModel().isRollover()) {
                        g3.setColor(Color.MAGENTA);
                    }
                    int delta = 6;
                    g3.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
                    g3.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
                    g3.dispose();
                }
            }

            private final static MouseListener buttonMouseListener = new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    Component component = e.getComponent();
                    if (component instanceof AbstractButton) {
                        AbstractButton button = (AbstractButton) component;
                        button.setBorderPainted(true);
                    }
                }

                public void mouseExited(MouseEvent e) {
                    Component component = e.getComponent();
                    if (component instanceof AbstractButton) {
                        AbstractButton button = (AbstractButton) component;
                        button.setBorderPainted(false);
                    }
                }
            };
        }
        /*
            END OF THE INNER CLASS ButtonTabComponent
         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    }
    /*
        END OF THE TabbedBoards CLASS
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        The Table class is where the Board objects will be centered placed. It composes the
        Board, Layers and Draw classes.
     */
    public class Table extends JPanel {
        // Instance Variables
        int widthT, heightT; // note that these are the dimensions of the Board
        GridBagLayout gbl;
        GridBagConstraints gbc;

        // Default constructor of Table class
        Table() {
            widthT = 800;
            heightT = 400;
            setGridBagLayout(new GridBagLayout());
            setConstrains();
            addBoard();
            setVisible(true);
            setBackground(new Color(150, 150, 150));
        }

        // Construct a table with the specific width and height for the Board object
        Table(int width, int height) {
            this.widthT = width;
            this.heightT = height;
            setGridBagLayout(new GridBagLayout());
            setConstrains();
            setBackground(new Color(150, 150, 150));
            setVisible(true);
            addBoard();
        }

        /**
         * Helper method to add the board where the user will draw
         */
        public void addBoard() {
            add(new Board(this), gbc);
        }

        public void setConstrains() {
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
        }

        public void setGridBagLayout(GridBagLayout gbl) {
            setLayout(gbl);
            gbl.rowHeights = new int[]{heightT};
            gbl.columnWidths = new int[]{widthT};
        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Inner class of Table. The Board class will provide the base layout for the drawings.
         */
        public class Board extends JPanel implements MouseInputListener {
            int index; // index to keep track of the layers created
            ArrayList<Integer> xPts = new ArrayList<>();
            ArrayList<Integer> yPts = new ArrayList<>();
            Layer board;
            GridBagLayout gbl;
            GridBagConstraints gbc;

            Board(Table table) { // there must be a Table object to create a board
                setGridBagLayout(new GridBagLayout());
                setConstrains();
                setVisible(true);
                board = new Layer();
                index = 0;
                add(board, gbc); // base layer of the initialization of a board
                addMouseListener(this);
                addMouseMotionListener(this);
            }

            /**
             * Add a new layer at the current index to this Board object.
             */
            public void addLayer() {
                board.add(new Layer(index++), index);
            }

            public void setConstrains() {
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
            }

            public void setGridBagLayout(GridBagLayout gbl) {
                setLayout(gbl);

                gbl.rowWeights = new double[]{1};
                gbl.columnWeights = new double[]{1};
                gbl.rowHeights = new int[]{heightT};
                gbl.columnWidths = new int[]{widthT};

                this.gbl = gbl;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xPts.add(e.getX());
                yPts.add(e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                board.addDraw();
                xPts.clear();
                yPts.clear();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                xPts.add(e.getX());
                yPts.add(e.getY());
                Component[] components = board.getComponentsInLayer(board.highestLayer());
                System.out.println(Arrays.toString(components));
                for (Component draw : components) {
                    System.out.println("asd");
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }


            /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Inner class of Board. All the layers in conjunction will provide to the user the tool of swap
                layers
             */
            public class Layer extends JLayeredPane {
                int index;
                String identifier;

                Layer() {
                    setVisible(true);
                    index = 0;
                    addDraw();
                    identifier = "board";
                }

                Layer(int index) {
                    setVisible(true);
                    this.index = index;
                    addDraw();
                    identifier = Integer.toString(index);
                }

                Layer(int index, String identifier) {
                    setVisible(true);
                    this.index = index;
                    addDraw();
                    this.identifier = identifier;
                }

                public void addDraw() {
                    Draw d = new Draw();
                    add(d, ++index);
                }

                @Override
                public String toString() {
                    return "Layer{" +
                            "index=" + index +
                            ", identifier='" + identifier + '\'' +
                            '}';
                }

                /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                    The JPanel where the user will create every draw
                                 */
                public class Draw extends JPanel {
                    BufferedImage image;

                    Draw() {
                        setVisible(true);
                        setOpaque(false);
                        System.out.println(this);
                        image = new BufferedImage(widthT, heightT, BufferedImage.TYPE_INT_ARGB);
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = image.createGraphics();
                        g2.setColor(user.color);
                        if (user.tool == 1) {
                            for (int i = 0; i < xPts.size() - 1; i++) {
                                g2.drawLine(xPts.get(i), yPts.get(i), xPts.get(i+1), yPts.get(i+1));
                            }
                        }g.drawImage(image, 0, 0, this);
                    }

                    @Override
                    public String toString() {
                        return "Draw{" + index + "}";
                    }
                }
            }
            /*
                END OF INNER CLASS Layer
            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
        }
        /*
            END OF INNER CLASS Board
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }
    /*
        END OF THE INNER CLASS Table
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            The component that will be used to create the Tools whose functionalities
            will provide to the user the ability to interact with the Board objects.
    */
    public class ToolsComponent extends JPanel {
        GridBagConstraints gbc;
        GridBagLayout gbl;
        EditorTool editorTool = new EditorTool();
        int index; // int to keep track of the tools created in the panel
        final int TOOL_HEIGHT = 150;

        // Default constructor of ToolsPanel
        ToolsComponent() {
            setGridBagLayout(new GridBagLayout());
            setVisible(true);
            setBackground(new Color(200, 200, 200));
            setConstrains();
            setBorder(BorderFactory.createLoweredBevelBorder());
            index = 0;

        }

        /**
         * Add the ToolPanel provided to the body of the ToolComponent object.
         * @param tool note that this Object most of the time is a child of the ToolPanel class
         */
        public void addTool(ToolPanel tool) {
            changeIndex(true);
            gbc.gridy = index-1;
            add(tool, gbc);
        }

        /**
         * Remove the ToolPanel provided from the body of the object ToolsComponent
         * @param tool this object can -most probably will be- a child of ToolPanel
         */
        public void removeTool(ToolPanel tool) {
            changeIndex(false);
            remove(tool);
        }

        /**
         *  Increment or decrement the index of the tools created, therefore the
         *  rowHeight is augmented due its dependency when aligning the ToolPanel objects.
         */
        public void changeIndex(boolean increment) {
            int sign = increment ?  1 : -1;
            this.index += sign;
            int[] rwHeight = new int[this.index];
            Arrays.fill(rwHeight, TOOL_HEIGHT);
            gbl.rowHeights = rwHeight;
        }

        public void setConstrains() {
            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 5, 5, 5);
        }

        public void setGridBagLayout(GridBagLayout gbl) {
            setLayout(gbl);
            gbl.columnWeights = new double[]{1};
            this.gbl = gbl;
        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            The JPanel object that will provide the blueprint to create every tool.
        */
        public class ToolPanel extends JPanel {
            GridBagLayout gbl;
            GridBagConstraints gbc;

            ToolPanel() {
                setGridBagLayout(new GridBagLayout());
                setConstrains();
                setBackground(new Color(190, 190, 190));
                setBorder(BorderFactory.createRaisedBevelBorder());
            }

            public void addToComponent(ToolPanel tool) {
                toolsPanel.addTool(tool);
            }

            public void removeFromComponent(ToolPanel tool) {
                toolsPanel.removeTool(tool);
            }

            public void setConstrains() {
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(10, 10, 10, 10);
            }

            public void setGridBagLayout(GridBagLayout gbl) {
                setLayout(gbl);
                gbl.rowWeights = new double[]{1};
                gbl.columnWeights = new double[]{1};
                this.gbl = gbl;
            }
        }
        /*
            END OF THE INNER CLASS ToolPanel
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

        // Tools of the user
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            The editor tool will provide a physical interaction with the board to draw
            shapes.
         */
        public class EditorTool extends ToolPanel {
            int tool;
            JButton pincel = new JButton();

            EditorTool() {
                setVisible(true);
                setLayout(new FlowLayout(FlowLayout.LEFT));
                setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Editor"));
                add(new PincelTool());
            }

            public class PincelTool extends JButton implements ActionListener {
                BufferedImage icon;
                boolean activated;
                int size;

                PincelTool() {
                    setVisible(true);
                    setContentAreaFilled(false);
                    setBorder(BorderFactory.createRaisedBevelBorder());
                    size = 40;
                    icon = new BufferedImage(size, size, BufferedImage.TYPE_4BYTE_ABGR);
                    setPreferredSize(new Dimension(size, size));
                    setMinimumSize(new Dimension(size, size));
                    addActionListener(this);
                }

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = icon.createGraphics();

                    int delta = 5;
                    g2.setColor(Color.MAGENTA);
                    g2.fillRect(delta, 0, delta, delta);
                    g2.setColor(Color.ORANGE);
                    g2.fillRect((int) (delta*3 - delta * 0.5), 0, delta*4, delta);
                    g2.setColor(Color.DARK_GRAY);
                    g2.fillPolygon(new int[]{delta*7, delta*7, delta*9},
                            new int[]{0, delta, delta/2}, 3);

                    g.drawImage(rotate(icon, 45.0), 0, 0, this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    activated = !activated;
                    if (activated) {
                        setBorder(BorderFactory.createLoweredBevelBorder());
                        user.setTool(1);
                    }
                    else {
                        setBorder(BorderFactory.createRaisedBevelBorder());
                        user.setTool(0);
                    }
                }
            }
        }
        /*
            END OF THE INNER CLASS EditorTool
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    }
    /*
        END OF INNER CLASS ToolComponent
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /**
     * Basic call to initialize the paint program
     */
    public static void initApp() {
        new PaintApp();
    }
}
/*
    END OF THE PaintApp CLASS
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/