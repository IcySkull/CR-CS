
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
public class BeepProblem extends JFrame{
    JButton beepButton = new JButton("Click to beep");
    JPanel centerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JTextField caldendarField = new JTextField(20);
    
    public static void main(String args[]){
        new BeepProblem();
    }
    public BeepProblem(){
        setTitle("Beep Button");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                exitForm(e);
            }
        });
        setResizable(false);
        setPreferredSize(new Dimension(400,200));
        pack();
        setVisible(true);
        initGUI();
    }
    private void initGUI(){
        beepButton.setPreferredSize(new Dimension(200,100));
        beepButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        add(centerPanel,BorderLayout.CENTER);
        centerPanel.add(beepButton);
        beepButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                beepButtonActionPerformed(e);
            }
        });
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(caldendarField);
    }
    private void beepButtonActionPerformed(ActionEvent e){
        Toolkit.getDefaultToolkit().beep();
        centerPanel.setBackground(new Color((int)(Math.random()*256), (int)(Math.random()*256),(int)(Math.random()*256)));
        caldendarField.setText(new Date().toString());
    }
    private void exitForm(WindowEvent e){
        System.exit(0);
    }
}
