
import java.io.IOException;
import javax.swing.SwingUtilities;
import ui.Login;

public class Main {

    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            try {
                Login frame = new Login(); // your JFrame subclass
                frame.setVisible(true);         // make it visible
            } catch (IOException ex) {
                System.getLogger(Main.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

    }
}
