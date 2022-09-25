import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Template extends JFrame {

 JPanel samplePanel = new JPanel();
 JButton sampleButton = new JButton("Click Me");

 public Template(){
  initGUI();

  setTitle("The Title");
  pack(); // tell the layout manager to organize the components optimally
  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
  setVisible(true);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  // setResizable(false);
 }
 private void initGUI(){
  //getContentPane().setLayout(new GridBagLayout());  // border layout the default
  add(samplePanel); // flow layout the default for panels
  sampleButton.setPreferredSize(new Dimension(100,50));
  sampleButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    sampleButtonActionPerformed(e);
    // you could put your code here to respond to the button
   }
  });
  samplePanel.add(sampleButton);

  // add more components

 }
 private void sampleButtonActionPerformed(ActionEvent e){

  // some code if the button was pressed
  // JButton temp = (JButton)e.getSource();
 }
 public static void main(String[] args) {
  new Template();
 }
}
// just an example of a class which isn't being used in this template
class ImagePanel extends JPanel
{
  private Image img;
  public ImagePanel(Image img)
  {
    this.img = img;
  }
  public void paintComponent(Graphics g)
  {
    g.drawImage(img, 0, 0, null);
  }
}