import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Border2 extends JFrame {

 JPanel northPanel = new JPanel();
 JPanel southPanel = new JPanel();
 JPanel eastPanel = new JPanel();
 JPanel westPanel = new JPanel();
 JPanel centerPanel = new JPanel();

 public Border2(){
  initGUI();

  setTitle("Border Layout 2");
  //setSize(300,300);
  pack(); // tell the layout manager to organize the components optimally
            // and ignores the setSize above
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
  setVisible(true);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  // setResizable(false);
 }
 private void initGUI(){
   //getContentPane().setLayout(new BorderLayout());  // border layout the default
   
   // set colors
   northPanel.setBackground(Color.red);
   southPanel.setBackground(Color.blue);
   eastPanel.setBackground(Color.white);
   centerPanel.setBackground(Color.black);
   westPanel.setBackground(Color.green);
   
   // set preferred size
   northPanel.setPreferredSize(new Dimension(300,50));
   southPanel.setPreferredSize(new Dimension(300,50));
   eastPanel.setPreferredSize(new Dimension(40,150));
   centerPanel.setPreferredSize(new Dimension(220,150));
   westPanel.setPreferredSize(new Dimension(40,150));
   
   // add panels via border layout
   getContentPane().add(northPanel,BorderLayout.NORTH);
   getContentPane().add(southPanel,BorderLayout.SOUTH);
   getContentPane().add(eastPanel,BorderLayout.EAST);
   getContentPane().add(centerPanel,BorderLayout.CENTER);
   getContentPane().add(westPanel,BorderLayout.WEST);

 }
 public static void main(String[] args) {
  new Border2();
 }
}