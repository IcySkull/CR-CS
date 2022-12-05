
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.logging.StreamHandler;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.transform.stream.StreamResult;

public class SkyLine extends JFrame {

    JPanel drawingPanel = new SkyLine.DrawPanel();
    JPanel buttonPanel = new JPanel();
    JButton skyLineButton = new JButton("Draw SkyLine");
    JButton clearButton = new JButton("Clear");
    ArrayList<Building> buildingList = new ArrayList<>(); // holds all buildings
    ArrayList<Color> colorsList = new ArrayList<>(); // holds buildings colors
    final int SIZE = 500;       // size of drawing panel
    Point a, b;     // for mouse clicks
    boolean sky;    // true draws skyline, false buildings

    public SkyLine() {
        initGUI();
        setTitle("Algorithms And Data Structures");
        pack();	// tell the layout manager to organize the components optimally
        setVisible(true);   // must have this line or you're components will only be in memory and can't be seen
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // closes the application
        setResizable(false);        // disables resizing the window
        setLocationRelativeTo(null);    // centers relative to the screen
    }

    private void initGUI() {
        TitleLabel titleLabel = new TitleLabel("SkyLine");   // add the title
        add(titleLabel, BorderLayout.PAGE_START);

        drawingPanel.setPreferredSize(new Dimension(SIZE, SIZE)); // adds the panel to draw on
        drawingPanel.setBackground(Color.white);
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawingPanelMousePressed(e);
            }
        });
        add(drawingPanel, BorderLayout.CENTER);	// flow layout the default for panels

        add(buttonPanel, BorderLayout.PAGE_END);    // adds a button panel at the bottom for all the buttons
        int w = 110, h = 50;
        skyLineButton.setPreferredSize(new Dimension(w * 2, h));
        skyLineButton.addActionListener(this::skyLineButtonActionPerformed);  // adds listener for the button and calls the method
        clearButton.setPreferredSize(new Dimension(w, h));
        clearButton.addActionListener(this::skyLineButtonActionPerformed);
        buttonPanel.add(skyLineButton);
        buttonPanel.add(clearButton);
    }

    private void skyLineButtonActionPerformed(ActionEvent e) {
        JButton temp = (JButton) e.getSource();
        switch (temp.getActionCommand()) {
            case "Draw SkyLine":
                sky = true;
                drawingPanel.setBackground(Color.black);
                temp.setText("Draw Buildings");
                break;
            case "Draw Buildings":
                sky = false;
                drawingPanel.setBackground(Color.white);
                temp.setText("Draw SkyLine");
                break;
            case "Clear":
                sky = false;
                drawingPanel.setBackground(Color.white);
                buildingList.clear();
                colorsList.clear();
                skyLineButton.setText("Draw SkyLine");
                break;
        }
        repaint();
    }

    private void drawingPanelMousePressed(MouseEvent me) {
        if (a == null) {
            a = new Point(me.getX(), me.getY());
        } else {
            b = new Point(me.getX(), me.getY());
            Building building = new Building(Math.min(a.x, b.x), Math.max(a.x, b.x), SIZE - a.y);
            buildingList.add(building);
            a = null;
            b = null;
        }
        Color c = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256), 80);
        colorsList.add(c);
        repaint();
    }

    public List<Point> recurSkyline(List<Building> buildings) {
        int[][] buildingsArray = buildings.stream()
                .map(building -> new int[]{building.left, building.right, building.height})
                .toArray(int[][]::new);
        return getSkyline(buildingsArray).stream()
                .map(point -> new Point(point.get(0), point.get(1)))
                .collect(Collectors.toList());
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Point>> points = Arrays.stream(buildings)
                .map(this::getPoitns)
                .collect(Collectors.toList());

        List<List<Point>> skyline = divideAndConquer(this::merge, points);
        
        return skyline.get(0).stream()
                .map(point -> Arrays.asList(point.x, point.y))
                .collect(Collectors.toList());
    }

    private List<Point> getPoitns(int[] building) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(building[0], building[2]));
        points.add(new Point(building[1], 0));
        return points;
    }

    private static <T> List<T> divideAndConquer(BinaryOperator<List<T>> conquer, List<T> list) {
        if (list.size() == 1) {
            return list;
        }
        int mid = list.size() / 2;
        List<T> left = list.subList(0, mid);
        List<T> right = list.subList(mid, list.size());
        return conquer.apply(divideAndConquer(conquer, left), divideAndConquer(conquer, right));
    }

    private List<List<Point>> merge(List<List<Point>> leftBuilding, List<List<Point>> rightBuilding) {

        List<Point> result = new ArrayList<>();
        List<Point> leftPoints = leftBuilding.stream().flatMap(List::stream).collect(Collectors.toList());
        List<Point> rightPoints = rightBuilding.stream().flatMap(List::stream).collect(Collectors.toList());
        int leftBuildingIndex = 0, rightBuildingIndex = 0;
        int maxLeftHeight = 0, maxRightHeight = 0;
        while (leftBuildingIndex < leftPoints.size() && rightBuildingIndex < rightPoints.size()) {
            Point leftBuildingPoint = leftPoints.get(leftBuildingIndex);
            Point rightBuildingPoint = rightPoints.get(rightBuildingIndex);
            if (leftBuildingPoint.x < rightBuildingPoint.x) {
                maxLeftHeight = leftBuildingPoint.y;
                result.add(new Point(
                    leftBuildingPoint.x, 
                    Math.max(maxLeftHeight, maxRightHeight)
                ));
                leftBuildingIndex++;
            } else if (leftBuildingPoint.x > rightBuildingPoint.x) {
                maxRightHeight = rightBuildingPoint.y;
                result.add(new Point(
                    rightBuildingPoint.x,
                    Math.max(maxLeftHeight, maxRightHeight)
                ));
                rightBuildingIndex++;
            } else {
                maxLeftHeight = leftBuildingPoint.y;
                maxRightHeight = rightBuildingPoint.y;
                result.add(new Point(
                    leftBuildingPoint.x, 
                    Math.max(maxLeftHeight, maxRightHeight)
                ));
                leftBuildingIndex++;
                rightBuildingIndex++;
            }
            if (result.size() > 1 && result.get(result.size()-1).y == result.get(result.size()-2).y) {
                result.remove(result.size()-1);
            }
        }

        while (leftBuildingIndex < leftPoints.size()) {
            result.add(leftPoints.get(leftBuildingIndex++));
        }

        while (rightBuildingIndex < rightPoints.size()) {
            result.add(rightPoints.get(rightBuildingIndex++));
        }

        return new ArrayList<>(Arrays.asList(result));
    }

    public static void main(String[] args) {
        SkyLine test = new SkyLine();
        int[] b1 = {2, 9, 10};
        int[] b2 = {9, 12, 15};
        System.out.println(test.getSkyline(new int[][]{b1, b2}));

        Building one = new Building(2, 9, 10);
        Building two = new Building(3, 6, 15);
        ArrayList<Building> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        System.out.println(test.recurSkyline(list));
        // ans [(2, 10) , (3, 15) , (6, 10) , (9, 0) ]

        // test case 2 { {2,9,10},  {3,6,15},  {5,12,12},  {13,16,10}, {15,17,5} };
        Building three = new Building(5, 12, 12);
        Building four = new Building(13, 16, 10);
        Building five = new Building(15, 17, 5);
        list.add(three);
        list.add(four);
        list.add(five);
        System.out.println(test.recurSkyline(list));
        // ans [(2, 10) , (3, 15) , (6, 12) , (12, 0) , (13, 10) , (16, 5) , (17, 0) ]

        // test case 3 [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8]
        two = new Building(3, 7, 15);
        three = new Building(5, 12, 12);
        four = new Building(15, 20, 10);
        five = new Building(19, 24, 8);
        list.clear();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        System.out.println(test.recurSkyline(list));
        // ans [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]
    }

    class DrawPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            super.paintComponent(g2D);
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setStroke(new BasicStroke(2));
            if (sky) {
                // draws 1000 stars at random locations within the panel 
                g2D.setPaint(Color.white);
                for (int i = 0; i < 1000; i++) {
                    int x = (int) (Math.random() * SIZE);
                    int y = (int) (Math.random() * SIZE);
                    Ellipse2D.Double oval = new Ellipse2D.Double(x, y, 1, 1);
                    g2D.fill(oval);
                }

                //draws the Moon at random locations.
                int x = (int) (Math.random() * SIZE - 30);
                int y = (int) (Math.random() * 50);
                Ellipse2D.Double oval = new Ellipse2D.Double(x, y, 30, 30);
                g2D.fill(oval);
                g2D.setPaint(Color.black);
                oval = new Ellipse2D.Double(x + 7, y + 3, 30, 30);
                g2D.fill(oval);
                if (buildingList.isEmpty()) {
                    return;
                }
                List<Point> list = recurSkyline(buildingList);
                GeneralPath poly = new GeneralPath();
                Point prev = list.get(0);
                poly.moveTo(prev.x, SIZE);
                poly.lineTo(prev.x, SIZE - prev.y);
                for (int i = 1; i < list.size(); i++) {
                    Point p = list.get(i);
                    poly.lineTo(p.x, SIZE - prev.y);
                    poly.lineTo(p.x, SIZE - p.y);
                    prev = p;
                }
                poly.closePath();
                g2D.setPaint(Color.blue);
                g2D.fill(poly);
            } else {
                for (int i = 0; i < buildingList.size(); i++) {
                    g2D.setPaint(colorsList.get(i));
                    Building b = buildingList.get(i);
                    Rectangle2D.Double rec = new Rectangle2D.Double(b.left, SIZE - b.height, b.right - b.left, b.height);
                    g2D.fill(rec);
                }
            }
        }
    }

    class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ") ";
        }
    }

}

class Building {

    int left, right, height;

    public Building(int left, int right, int height) {
        this.left = left;
        this.right = right;
        this.height = height;
    }
}
