
/**
 *
 * This is the main class for the game "catman".
 * I made the main method (catman) a JFrame and TheGame a JPanel to fix my flickering error.
 * JPanel reprints the maze every 15 milliseconds.
 * Before, it was JFrame reprinting every second which made it look glitchy.
 * If JFrame was like a picture frame, JPanel would be the photo I put inside the frame.
 * Before I updated this, the JFrame was printing it out almost every second.
 * Let's say I wanted to change a photo (JPanel) in the picture frame, I would throw the old frame (JFrame) out and replace it with a new frame.
 * This is not very efficient, is it? This is what my code was essentially doing before I fixed this issue.
 * Now JPanel repaints the screen (the maze, cat, dogs, etc.) almost every second.
 * That is like the photo inside is changing but keeping the same frame (JFrame).
 * Now the screen doesn't flicker and the graphics are a lot smoother than before!!! YAY!
 * JPanel has automatic double buffering and JFrame doesn't.
 *
 * @author Gabriella Bella Rose Bitju
 * @version 02/08
 */
import javax.swing.JFrame;


public class Catman extends JFrame {
    static int columns = 28;//this is setting the number of cell columns in the game panel to 28.
    static int rows = 50; //sets the cell rows in the game panel to 50.
    static int cellSize = 23;//the size of a cell is 23 pixels
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cat Man");//making a new frame/ window
        frame.setSize((cellSize * columns), (cellSize * rows));//setting the frame/window size.
        TheGame panel = new TheGame();//making the class TheGame called panel
        frame.add(panel);//adding panel into the frame (window)
        panel.requestFocusInWindow();//to make the panel be able to focus in the window
        // Set the frame to be visible
        frame.setVisible(true);
    }
}