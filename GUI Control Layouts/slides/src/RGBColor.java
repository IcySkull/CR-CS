
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class RGBColor extends JFrame {

    JLabel redLabel = new JLabel();
    JLabel greenLabel = new JLabel();
    JLabel blueLabel = new JLabel();
    JSlider redSlider = new JSlider();
    JSlider greenSlider = new JSlider();
    JSlider blueSlider = new JSlider();
    JSlider[] sliderAry;
    Font font = new Font(Font.DIALOG,Font.BOLD,24);
    JPanel topPanel = new JPanel();
   
   
    public static void main(String[] args) {
        new RGBColor().show();
    }
    public RGBColor(){
        setTitle("RGB Color Problem");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
           
        // add blue label
        GridBagConstraints gridConstraits = new GridBagConstraints();
        blueLabel.setPreferredSize(new Dimension(100,30));
        blueLabel.setText("255");
        blueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        blueLabel.setVerticalAlignment(SwingConstants.CENTER);
        blueLabel.setBackground(Color.white);
        blueLabel.setForeground(Color.blue);
        blueLabel.setFont(font);
        blueLabel.setOpaque(true);
        gridConstraits.gridx=2;
        gridConstraits.gridy=2;
        gridConstraits.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(blueLabel,gridConstraits);
        
        
        // add red label 
        gridConstraits = new GridBagConstraints();
        redLabel.setPreferredSize(new Dimension(100,30));
        redLabel.setText("255");
        redLabel.setHorizontalAlignment(SwingConstants.CENTER);
        redLabel.setVerticalAlignment(SwingConstants.CENTER);
        redLabel.setBackground(Color.white);
        redLabel.setForeground(Color.red);
        redLabel.setFont(font);
        redLabel.setOpaque(true);
        gridConstraits.gridx=0;
        gridConstraits.gridy=2;
        gridConstraits.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(redLabel,gridConstraits);
        
        // add green label
        gridConstraits = new GridBagConstraints();
        greenLabel.setPreferredSize(new Dimension(100,30));
        greenLabel.setText("255");
        greenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        greenLabel.setVerticalAlignment(SwingConstants.CENTER);
        greenLabel.setBackground(Color.white);
        greenLabel.setForeground(Color.green);
        greenLabel.setFont(font);
        greenLabel.setOpaque(true);
        gridConstraits.gridx=1;
        gridConstraits.gridy=2;
        gridConstraits.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(greenLabel,gridConstraits);
        
        //add panel to top
        gridConstraits = new GridBagConstraints();
        topPanel.setPreferredSize(new Dimension(400,100));
        topPanel.setBackground(Color.black);
        gridConstraits.gridx=0;
        gridConstraits.gridy=0;
        gridConstraits.gridwidth=3;
        gridConstraits.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(topPanel,gridConstraits);
        
       // add the 3 sliders
        sliderAry = new JSlider[]{redSlider, greenSlider, blueSlider};
        for(int i=0; i<sliderAry.length;i++){
            gridConstraits = new GridBagConstraints();
            sliderAry[i].setPreferredSize(new Dimension(150,40));
            sliderAry[i].setMinimum(0);
            sliderAry[i].setMaximum(255);
            gridConstraits.gridx=i;
            gridConstraits.gridy=1;
            getContentPane().add(sliderAry[i],gridConstraits);
            sliderAry[i].setMinorTickSpacing(1);
            sliderAry[i].setValue(255);
            sliderAry[i].addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                  sliderAryStateChanged(e);
                }
            });
        }
        
        
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
    }
    
    private void sliderAryStateChanged(ChangeEvent e){
        Color c = new Color(sliderAry[0].getValue(),sliderAry[1].getValue(),sliderAry[2].getValue());
        topPanel.setBackground(c);
        redLabel.setText(""+ sliderAry[0].getValue());
        greenLabel.setText(""+sliderAry[1].getValue());
        blueLabel.setText(""+sliderAry[2].getValue());
    }
    
    private void exitForm(WindowEvent e){
        System.exit(0);
    } 
    
}
