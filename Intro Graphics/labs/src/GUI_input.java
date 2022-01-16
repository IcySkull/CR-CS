import javax.swing.*;

public class GUI_input {
    public static void main(String args[]) {
        int response = JOptionPane.showConfirmDialog(null, "di algo",
                "bienvenido", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (response == JOptionPane.OK_OPTION) {
            response = JOptionPane.showConfirmDialog(null, "tas seguro piter?", "mmm"
                    , JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION)
                JOptionPane.showConfirmDialog(null, "donde te sentaste!!!", "nooo",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showConfirmDialog(null, "muito obrigado", "bien",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
        }

    }
}
