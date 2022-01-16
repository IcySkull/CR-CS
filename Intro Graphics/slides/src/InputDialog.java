import javax.swing.*;
public class InputDialog{
	public static void main(String args[]){
		
		JOptionPane.showMessageDialog(null,"I Love Java","This is the title message", JOptionPane.ERROR_MESSAGE);

		
		String name = JOptionPane.showInputDialog("What's your name?");
		
		String message = String.format("Welcome, %s, to Graphical Programming in Java!",name);
		
		JOptionPane.showMessageDialog(null, message);
		
	}
	// repeat but ask for the persons age
}