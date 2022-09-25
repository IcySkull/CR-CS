import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
public class RandomWalk extends JFrame
{ 
	public static void main(String args[])
    {
        new RandomWalk();        
    }
    public RandomWalk()
    {
        getContentPane().add(new Draw());
        setTitle("Random Walk");
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);   
    }
    // nested inner class
	class Draw extends JPanel
	{
   		private final int DIAMETER=500;
    	private final Point CENTER = new Point(DIAMETER/2+50,DIAMETER/2+50);
    	private Point p = new Point(CENTER.x, CENTER.y);
        
    	public void paintComponent(Graphics g)
   		{
      		  g.setColor(Color.black);
      		  g.drawOval(50, 50, DIAMETER, DIAMETER);
       // change the color, call turn and then draw the instance variable p provided it's inside the circle
        
        
        
        
        
        
    	}
    	public void turn()
    	{
        	// add a switch case for four random directions
        
        
        
        
        
      
        	repaint();
    	}
    	public Color randColor()
    	{
    		// change to a random color
        	return Color.black;
    	}
	}
}
