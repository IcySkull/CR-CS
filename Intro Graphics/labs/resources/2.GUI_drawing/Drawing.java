import java.awt.*;
import javax.swing.*;
public class Drawing extends JFrame {

    public Drawing() {
     setTitle("Drawing");
     setSize(new Dimension(300,300));
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     add(new DrawingPanel());

  //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5*(screensize.width-getWidth())), (int)(.5*(screensize.height-getHeight())), getWidth(), getHeight() );
    }
    public static void main(String[] args) {
        new Drawing();
    }
    private class DrawingPanel extends JPanel{
  public void paintComponent(Graphics g){
   super.paintComponent(g);
   int w = getWidth();
   int h = getHeight();
   int hSteps = 0;// update
   int wSteps = 0;// update
   
   // your code goes here
  }
    }
}
