import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Flow extends JFrame implements ActionListener {

 JButton aButton = new JButton("a");
 JButton bButton = new JButton("b");
 JButton cButton = new JButton("c");
 JButton dButton = new JButton("d");
 JButton eButton = new JButton("e");
 

 public Flow(){
  initGUI();

  setTitle("Flow Layout");
  setSize(300,300);
  pack(); // tell the layout manager to organize the components optimally
            // and ignores the setSize above
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
  setVisible(true);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  // setResizable(false);
 }
 
 // helper method to add the buttons and listeners
 private void initGUI(){
   getContentPane().setLayout(new FlowLayout());  // border layout the default
   
   // add listeners
   aButton.addActionListener(this);
   bButton.addActionListener(this);
   cButton.addActionListener(this);
   dButton.addActionListener(this);
   eButton.addActionListener(this);
   
   // add buttons to content pane via flow layout
   getContentPane().add(aButton);
   getContentPane().add(bButton);
   getContentPane().add(cButton);
   getContentPane().add(dButton);
   getContentPane().add(eButton);
 }
 
 public void actionPerformed(ActionEvent ae){
   String s = ae.getActionCommand();
   JOptionPane.showMessageDialog(null,"The " + s + " button was clicked");
 }
 public static void main(String[] args) {
  Flow f = new Flow();
 }
}