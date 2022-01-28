package paintproject;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;

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
    JColorChooser colorPicker = new JColorChooser();

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
        JMenu refresh = new JMenu("Refresh");

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
        JMenuItem colorButton = new JMenuItem("Color Picker");
        editorTool.addItemListener(e -> {
            if (editorTool.isSelected())
                toolsPanel.addTool(toolsPanel.editorTool);
            else
                toolsPanel.removeTool(toolsPanel.editorTool);

            toolsPanel.repaint();
        });

        colorButton.addActionListener(e -> {
                user.setColor(JColorChooser.showDialog(colorPicker, "Choose a color for your draw", Color.lightGray));
        });
        colorButton.setSelected(true);
        editorTool.setSelected(true);

        // Add menuFile items
        menuFile.add(addFile);

        // Add menuView items
        menuView.add(editorTool);
        menuView.add(colorButton);
        menuView.add(new JSeparator());

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(refresh);
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
        Stroke stroke;
        int drawSize;
        int tool;
        boolean eraser;

        PaintUser() {
            color = Color.darkGray;
            drawSize = 15;
            stroke = new BasicStroke(drawSize);
            tool = 0;
            eraser = false;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setStroke(Stroke stroke) {
            this.stroke = stroke;
        }

        public void setDrawSize(int drawSize) {
            this.drawSize = drawSize;
            stroke = new BasicStroke(drawSize);
        }

        public void setTool(int tool) {
            this.tool = tool;
        }

        public void setEraser(boolean eraser) {
            this.eraser = eraser;
        }

        public Color getColor() {
            return color;
        }

        public Stroke getStroke() {
            return stroke;
        }

        public int getDrawSize() {
            return drawSize;
        }

        public int getTool() {
            return tool;
        }

        public boolean isEraser() {
            return eraser;
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
            limitBounds(this);
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
            limitBounds(this);
            setBackground(new Color(150, 150, 150));
            setVisible(true);
            addBoard();
        }

        public void limitBounds(JComponent comp) {
            Dimension size = new Dimension(widthT, heightT);
            comp.setPreferredSize(size);
            comp.setMinimumSize(size);
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
        private class Board extends JPanel implements MouseInputListener {
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
                limitBounds(this);
                board = new Layer();
                index = 0;
                setBackground(Color.WHITE);
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
                for (Component draw : components) {
                    draw.repaint();
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
                GridBagLayout gbl;
                GridBagConstraints gbc;

                Layer() {
                    setVisible(true);
                    setGridBagLayout(new GridBagLayout());
                    setConstrains();
                    limitBounds(this);
                    index = 0;
                    addDraw();
                    identifier = "board";
                }

                Layer(int index) {
                    setVisible(true);
                    setGridBagLayout(new GridBagLayout());
                    setConstrains();
                    limitBounds(this);
                    this.index = index;
                    addDraw();
                    identifier = Integer.toString(index);
                }

                Layer(int index, String identifier) {
                    setVisible(true);
                    setGridBagLayout(new GridBagLayout());
                    setConstrains();
                    limitBounds(this);
                    this.index = index;
                    addDraw();
                    this.identifier = identifier;
                }

                public void addDraw() {
                    Draw d = new Draw();
                    add(d, gbc);
                    setLayer(d, index++);
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
                        limitBounds(this);
                        image = new BufferedImage(widthT, heightT, BufferedImage.TYPE_INT_ARGB);
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = image.createGraphics();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        int stroke = user.getDrawSize();
                        g2.setColor(user.getColor());
                        g2.setStroke(user.getStroke());

                        if (user.isEraser())
                            g2.setColor(Color.WHITE);

                        if (user.tool == 1) {
                            for (int i = 0; i < xPts.size() - 1; i++) {
                                double xMin = Math.min(xPts.get(i), xPts.get(i+1));
                                int xMax = Math.max(xPts.get(i), xPts.get(i+1));
                                double yMin = Math.min(yPts.get(i), yPts.get(i+1));
                                int yMax = Math.min(yPts.get(i), yPts.get(i+1));
                                if ((xMax / xMin - yMax / yMin) < 0.6)
                                    g2.fillRoundRect(
                                            (int) (xPts.get(i+1) - stroke*0.725),
                                            (int) (yPts.get(i+1) - stroke*0.725),
                                            (int) (stroke*1.45),
                                            (int) (stroke*1.45), 100, 100
                                    );

                                g2.drawLine(xPts.get(i), yPts.get(i), xPts.get(i + 1), yPts.get(i + 1));
                            }
                        }

                        g.drawImage(image, 0, 0, this);
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

            public void unSelectComponentsExcept(JButton button) {
                Component[] components = this.getComponents();
                for (Component comp : components) {
                    if (comp instanceof JButton) {
                        if (button.equals(comp))
                            return;
                        else
                            ((JButton) comp).setSelected(false);
                    }
                }
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
            PincelButton pincel = new PincelButton();
            EraserButton eraser = new EraserButton();
            JSlider strokeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 4);

            EditorTool() {
                setGridBagLayout(new GridBagLayout());
                setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Editor"));
                setConstrains();
                gbc.gridx = 0;
                add(pincel, gbc);
                gbc.gridx = 1;
                add(eraser, gbc);

                addSlider();
            }

            public void addSlider() {
                strokeSlider.addChangeListener(ce -> {
                    JSlider source = (JSlider) ce.getSource();
                    user.setDrawSize(source.getValue());
                });

                gbc.gridy = 1;
                gbc.gridx = 0;
                gbc.gridwidth = 3;
                gbc.insets = new Insets(10, 2, 2, 2);
                strokeSlider.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createRaisedSoftBevelBorder(),"stroke"));
                strokeSlider.setBackground(new Color(200, 200, 200));
                strokeSlider.setName("Stroke Width");
                add(strokeSlider, gbc);
            }

            @Override
            public void setConstrains() {
                gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(2, 2, 2, 2);
            }

            @Override
            public void setGridBagLayout(GridBagLayout gbl) {
                setLayout(gbl);
                gbl.columnWidths = new int[] {40, 40, 40};
                gbl.rowHeights = new int[] {40, 40};
            }

            public class PincelButton extends JButton implements ActionListener {
                BufferedImage icon;
                boolean activated = false;
                int size;

                PincelButton() {
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
                        eraser.activated = false;
                        eraser.setBorder(BorderFactory.createRaisedBevelBorder());
                        user.setEraser(false);
                        setBorder(BorderFactory.createLoweredBevelBorder());
                        user.setTool(1);
                    }
                    else {
                        setBorder(BorderFactory.createRaisedBevelBorder());
                        user.setTool(0);
                    }
                }
            }

            public class EraserButton extends JButton implements ActionListener {
                BufferedImage icon;
                boolean activated = false;
                int size;

                EraserButton() {
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
                    unSelectComponentsExcept(this);
                    Graphics2D g2 = icon.createGraphics();
                    g2.setColor(Color.MAGENTA);
                    g2.fillRect( 6, 0, size-10, 5);

                    g.drawImage(rotate(icon, 45.0), 0, 0, this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    activated = !activated;
                    if (activated) {
                        pincel.activated = false;
                        pincel.setBorder(BorderFactory.createRaisedBevelBorder());
                        setBorder(BorderFactory.createLoweredBevelBorder());
                        user.setTool(1);
                        user.setEraser(true);
                    }
                    else {
                        setBorder(BorderFactory.createRaisedBevelBorder());
                        user.setTool(0);
                        user.setEraser(false);
                    }
                }
            }

        }
        /*
            END OF THE INNER CLASS EditorTool
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

//        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            A tool panel where the user will interact to change the color of its drawings.
//         */
//        public class ColorTool extends ToolPanel {
//            JColorChooser colorPicker = new JColorChooser();
//
//            ColorTool() {
//                modifyJColorChooser();
//                add(colorPicker, gbc);
//            }
//
//            private void modifyJColorChooser() {
//                final AbstractColorChooserPanel[] panels = colorPicker.getChooserPanels();
//                for (final AbstractColorChooserPanel accp : panels) {
//                    if (!accp.getDisplayName().equals("RGB")) {
//                        colorPicker.removeChooserPanel(accp);
//                    }
//                }
//            }
//        }
        /*
            END OF THE INNER CLASS ColorTool
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

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