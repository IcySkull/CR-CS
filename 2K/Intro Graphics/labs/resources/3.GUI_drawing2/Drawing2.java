import java.awt.*;
import javax.swing.*;
public class Drawing extends JFrame {

    public Drawing2() {
     setTitle("Drawing2");
     setSize(new Dimension(300,300));
     setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     add(new DrawingPanel2());

  //centers the frame
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5*(screensize.width-getWidth())), (int)(.5*(screensize.height-getHeight())), getWidth(), getHeight() );
    }
    public static void main(String[] args) {
        new Drawing2();
    }
    private class DrawingPanel2 extends JPanel{
  public void paintComponent(Graphics g){
   super.paintComponent(g);
   // your code goes here
  }
    }
}
