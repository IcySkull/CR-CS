import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MessageBoxes extends JFrame {
    
    public static void main(String args[]){
        new MessageBoxes();
    }
    public MessageBoxes(){
        initGUI();
        setTitle("Message Box Problem");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
        pack();
    }
    private void initGUI(){
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx=0;
        gridConstraints.gridy=0;
        gridConstraints.insets = new Insets(10,10,10,10);
        
      
                        
        
    }
   
    private void exitButtonActionPerformed(ActionEvent e){
        System.exit(0);
    }
}
