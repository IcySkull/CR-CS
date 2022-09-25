import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Border extends JFrame {

 
 JButton northButton = new JButton("North");
 JButton southButton = new JButton("South");
 JButton eastButton = new JButton("East");
 JButton westButton = new JButton("West");
 JButton centerButton = new JButton("Center");
 

 public Border(){
  initGUI();

  setTitle("Border Layout");
  pack(); // tell the layout manager to organize the components optimally
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
  setVisible(true);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  // setResizable(false);
 }
 private void initGUI(){
   getContentPane().setLayout(new BorderLayout());  // border layout the default
   
   getContentPane().add(northButton,BorderLayout.NORTH);
   getContentPane().add(southButton,BorderLayout.SOUTH);
   getContentPane().add(eastButton,BorderLayout.EAST);
   getContentPane().add(centerButton,BorderLayout.CENTER);
   getContentPane().add(westButton,BorderLayout.WEST);

 }
 public static void main(String[] args) {
  new Border();
 }
}