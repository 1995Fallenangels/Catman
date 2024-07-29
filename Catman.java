
/**
 * Write a description of class catman here.
 *
 * @author gabriella
 * @version 3/5
 */
import javax.swing.JFrame;


public class Catman extends JFrame {


    static int columns = 28;//this is setting the number of cell columns in the game panel to 21.
    static int rows = 50; //sets the cell rows in the game panel to 50.
    static int cellSize = 23;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cat Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((cellSize * columns), (cellSize * rows));
        TheGame panel = new TheGame();
        frame.add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}