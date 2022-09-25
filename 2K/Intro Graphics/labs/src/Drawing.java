import java.awt.*;
import javax.swing.*;

public class Drawing extends JFrame {

    public Drawing() {
    	setTitle("Four Corners");
    	setSize(new Dimension(300,300));
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	add(new DrawingPanel());
		System.out.println("asd");

		//centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5*(screensize.width-getWidth())), (int)(.5*(screensize.height-getHeight())), getWidth(), getHeight() );
    }
    public static void main(String[] args) {
        new Drawing();
    }
    private class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
//			setLines(16, width, height, g);

			int lines = 15;
			int spacingX = width / lines;
			int spacingY = height / lines;

			for (int i = 1; i <= lines; i++) {
				g.drawLine(0, 0, spacingX*i, height-spacingY*i);
				g.drawLine(width, 0, spacingX*i, spacingY*i);
				g.drawLine(0, height, spacingX*i, spacingY*i);
				g.drawLine(width, height, spacingX*i, height-spacingY*i);
			}
		}
	}
}
