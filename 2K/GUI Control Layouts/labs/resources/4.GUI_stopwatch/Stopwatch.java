import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch extends JFrame{

    JButton startButton = new JButton();
    JButton stopButton = new JButton();
    JButton exitButton = new JButton();
    JLabel startLabel = new JLabel();
    JLabel stopLabel = new JLabel();
    JLabel elapsedLabel = new JLabel();
    JTextField startTextField = new JTextField(15);
    JTextField stopTextField = new JTextField(15);
    JTextField elapsedTextField = new JTextField(15);

    long startTime, stopTime;
    double elapsedTime;

    public static void main(String[] args) {
        new Stopwatch();
    }
    public Stopwatch(){
        initGUI();
        setTitle("Stopwatch Application");
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void initGUI(){
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.RED);

        //the first button is done for you
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        startButton.setText("Start Timing");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(startButton, gridBagConstraints);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButtonActionPerformed(e);
            }
        });

        // add other components
		gridBagConstraints = new GridBagConstraints();




    }
    private void startButtonActionPerformed(ActionEvent e){






    }
    private void stopButtonActionPerformed(ActionEvent e){





    }
    private void exitButtonActionPerformed(ActionEvent e){
        System.exit(0);
    }
}


