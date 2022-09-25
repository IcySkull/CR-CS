
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Savings extends JFrame{
    JLabel depositLabel = new JLabel();
    JLabel interestLabel = new JLabel();
    JLabel monthsLabel = new JLabel();
    JLabel balanceLabel = new JLabel();
    JButton calculateButton = new JButton();
    JButton exitButton = new JButton();
    JButton clearButton = new JButton();
    JTextField depositText = new JTextField(15);
    JTextField interestText = new JTextField(15);
    JTextField monthsText = new JTextField(15);
    JTextField balanceText = new JTextField(15);
    
    public static void main(String args[]){
        new Savings().show();
    }
    public Savings(){
        setTitle("Savings Account");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        depositLabel.setText("Monthly Deposit");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(depositLabel, gridBagConstraints);
        
        interestLabel.setText("Yearly Interest");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(interestLabel, gridBagConstraints);
        
        monthsLabel.setText("Number of Months");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(monthsLabel, gridBagConstraints);
        
        balanceLabel.setText("Final Balance");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        getContentPane().add(balanceLabel, gridBagConstraints);

        depositText.setText("");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        getContentPane().add(depositText, gridBagConstraints);
        depositText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 depositTextActionPerformed(e);
            }
        });
        
        interestText.setText("");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        getContentPane().add(interestText, gridBagConstraints);
        interestText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 interestTextActionPerformed(e);
            }
        });
        
        monthsText.setText("");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(monthsText, gridBagConstraints);
        monthsText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 monthsTextActionPerformed(e);
            }
        });
        
        balanceText.setText("");
        balanceText.setFocusable(false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        getContentPane().add(balanceText, gridBagConstraints);
        balanceText.setEditable(false);
        
        calculateButton.setText("Calculate");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        getContentPane().add(calculateButton, gridBagConstraints);
        calculateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               calculateButtonActionPerformed(e);
            }
        });
        
        exitButton.setText("Exit");
        exitButton.setFocusable(false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        getContentPane().add(exitButton, gridBagConstraints);
        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               exitButtonActionPerformed(e);
            }
        });
        clearButton.setText("Clear");
        clearButton.setFocusable(false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        getContentPane().add(clearButton, gridBagConstraints);
        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               clearButtonActionPerformed(e);
            }
        });
        
        pack();
    }
    private void exitButtonActionPerformed(ActionEvent e){
        System.exit(0);
    }
    private void calculateButtonActionPerformed(ActionEvent evt){
        double d=0, i=0, m=0, b;
        try{
            d = Double.parseDouble(depositText.getText());
            i = Double.parseDouble(interestText.getText())/1200;
            m = Double.parseDouble(monthsText.getText());
            if(i==0){
                throw new Exception();
            }
            b = d * ( Math.pow( 1+i, m) -1)/ i;
        balanceText.setText( String.format("$%,.2f",b));
        }catch(Exception e){
            System.out.println("Error in data entry");
            balanceText.setText( "Error in data");
        }
    }
    private void exitForm(WindowEvent e){
        System.exit(0);
    }
    private void depositTextActionPerformed(ActionEvent e){
        depositText.transferFocus();
    }
    private void interestTextActionPerformed(ActionEvent e){
        interestText.transferFocus();
    }
    private void monthsTextActionPerformed(ActionEvent e){
         monthsText.transferFocus();
    }
    private void clearButtonActionPerformed(ActionEvent e){
        depositText.setText("");
        monthsText.setText("");
        interestText.setText("");
        depositText.requestFocus();
        balanceText.setText("");
    }
}
