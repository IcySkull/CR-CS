import javax.swing.*;
public class DialogTwo{
	public static void main(String args[]){
		int response = JOptionPane.showConfirmDialog(null, "This is an example of an confirm dialog box.", "Example", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (response == JOptionPane.YES_OPTION){
		}
		else if (response == JOptionPane.NO_OPTION){
		}
		else{
		}
	}
}