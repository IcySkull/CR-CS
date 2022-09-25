import javax.swing.*;
public class DialogTwo{
	public static void main(String args[]){
		int response = JOptionPane.showConfirmDialog(null, "This is an example of an confirm dialog box.", "Example", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (response == JOptionPane.YES_OPTION){
			// Pressed Yes
		}
		else if (response == JOptionPane.NO_OPTION){
			// Pressed No
		}
		else{
			// Closed window without pressing button
		}
	}
	// add your own dialog box trying different parameters
}