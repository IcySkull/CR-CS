package paintproject;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;


/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    BASE FRAME OF THE APP
 */
public class PaintApp extends JFrame {
    public static void main(String[] args) {
        initApp();
    }

    // Instance variables
    ToolsPanel toolsPanel = new ToolsPanel();
    TabbedTables tables = new TabbedTables();

    PaintApp() {
        setGridBagLayout(new GridBagLayout());
        setVisible(true);
        setDimension(1200, 600);
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

    // set the Table component to occupy all the space disposable and the Tools component to a fixed width space
    public void setGridBagLayout(GridBagLayout gbl) {
        setLayout(gbl);
        gbl.columnWeights = new double[]{0,1};
        gbl.rowWeights = new double[]{1};
        gbl.columnWidths = new int[]{200}; // fixed width space for the first column
    }


    public void addComponents() {
        initMenu();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // add tools panels
        gbc.gridx = 0;
        add(toolsPanel, gbc);

        // add tab tables
        gbc.gridx = 1;
        add(tables, gbc);
    }

    /**
     * Creates an interface menu to interact with the user
     */
    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("FILE");
        JMenu menuView = new JMenu("VIEW");

        // menuFile items
        JMenuItem addFile = new JMenuItem("New Draw");
        addFile.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(
                    null,
                    "Enter the name of the draw",
                    "New Draw",
                    JOptionPane.INFORMATION_MESSAGE);
            String width = JOptionPane.showInputDialog(
                    null,
                    "Enter the width of the draw",
                    "New Draw",
                    JOptionPane.INFORMATION_MESSAGE);
            String height = JOptionPane.showInputDialog(
                    null,
                    "Enter the height of the draw",
                    "New Draw",
                    JOptionPane.INFORMATION_MESSAGE);

            int widthNum = Integer.parseInt(width);
            int heightNum = Integer.parseInt(height);

            tables.initTable(title, widthNum, heightNum);
        });

        // menuView items
        JRadioButtonMenuItem tools = new JRadioButtonMenuItem("Tools");
        tools.addChangeListener(e -> {

        });

        // Add menuFile items
        menuFile.add(addFile);

        // Add menuView items
        menuView.add(tools);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        setJMenuBar(menuBar);
    }

    public void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (Math.round((screenSize.getWidth() - getWidth()) / 2));
        int y = (int) (Math.round((screenSize.getHeight() - getHeight()) / 2));
        setLocation(x, y);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        A tabbed interface where the user changes between Board objects
     */
    class TabbedTables extends JTabbedPane {
        // Instance variables
        int index;

        // Default constructor of the class
        TabbedTables() {
        }

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
            The component that will be used as the tab
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
                    }
                }

                //we don't want to update UI for this button
                public void updateUI() {
                }

                //paint the cross
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    //shift the image for pressed buttons
                    if (getModel().isPressed()) {
                        g2.translate(1, 1);
                    }
                    g2.setStroke(new BasicStroke(2));
                    g2.setColor(Color.BLACK);
                    if (getModel().isRollover()) {
                        g2.setColor(Color.MAGENTA);
                    }
                    int delta = 6;
                    g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
                    g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
                    g2.dispose();
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
        int width, height; // note that these are the dimensions of the Board

        // Default constructor of Table class
        Table() {
            width = 800;
            height = 400;
            setGridBagLayout(new GridBagLayout());
            addBoard();
            setVisible(true);
            setBackground(new Color(150, 150, 150));
        }

        Table(int width, int height) {
            this.width = width;
            this.height = height;
            setGridBagLayout(new GridBagLayout());
            addBoard();
            setVisible(true);
            setBackground(new Color(150, 150, 150));
        }

        /**
         * Helper method to add the board where the user will draw
         */
        public void addBoard() {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            add(new Board(this), gbc);
        }

        public void setGridBagLayout(GridBagLayout gbl) {
            setLayout(gbl);
            gbl.columnWidths = new int[]{width};
            gbl.rowHeights = new int[]{height};
        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            Inner class of Table. The Board class will provide the base layout for the drawings.
         */
        public class Board extends JPanel {
            // Instance variables
            int width, height;

            Board(Table table) {
                setGridBagLayout(new GridBagLayout());
                setVisible(true);
                setBackground(Color.WHITE);
                width = table.width;
                height = table.height;
            }

            public void setGridBagLayout(GridBagLayout gbl) {
                setLayout(gbl);
                gbl.rowWeights = new double[]{1};
                gbl.columnWeights = new double[]{1};
            }

            /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Inner class of Board. All the layers in conjunction will provide to the user the tool of swap
                layers
             */
            public class Layers extends JLayeredPane {
                int index;

                Layers() {
                    setGridBagLayout(new GridBagLayout());
                    setVisible(true);
                }

                public void setGridBagLayout(GridBagLayout gbl) {
                    setLayout(gbl);
                    gbl.rowWeights = new double[]{1};
                    gbl.columnWeights = new double[]{1};
                }

                public void addNewLayer() {

                }

                /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    The JPanel where the user will create every draw
                 */
                public class Draw extends JPanel {

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
            Tools that will be used for the user to interact with the Table class
    */
    public class ToolsPanel extends JPanel {
        ToolsPanel() {
            setGridBagLayout(new GridBagLayout());
            setVisible(true);
            setBackground(new Color(200, 200, 200));
        }

        public void setGridBagLayout(GridBagLayout gbl) {
            setLayout(gbl);
        }
    }
    /*
        END OF INNER CLASS ToolsPanel
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /**
     * Basic calls to initialize the paint program
     */
    public static void initApp() {
        new PaintApp();
    }
}
/*
    END OF THE PaintApp CLASS
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/