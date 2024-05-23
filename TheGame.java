
/**
 * Write a description of class TheGame here.
 * 
 * This is the code for the game 'Catman' I'm creating for my computer science assignment. Catman is inspired by the game Pacman.
 * In this game, a cat is being chased by dogs. When the cat gets caught by a dog, the cat loses 3 lives. The cat has 9 lives in total.
 * There will be fishes the cat can eat. 
 * There will need to be 4 main components that make up this game which is:
 * 1) a maze component. This draws the maze the cat and dogs walk through
 * 2) a component that moves the characters
 * 3) a scorekeeping component. This will show the score of the user
 * 4) a healthbar. This will show how many lives the kitten has left. The cat will have nine lives represented as hearts (because cats have 9 lives).
 * When the cat runs into a dog, the cat will lose 3 lives.
 * @author gabriella
 * @version 22/05
 */
import javax.swing.*;
import java.awt.*;
public class TheGame extends JFrame 
{
    int colomns = 18;//this is setting the number of cell colomns in the maze to 21.
    int rows = 22; //sets the cell rows in the maze to 18.
    int border = 20; //how far the maze is away from the edge of the panel.
    int cellSize = 25;// this sets the size of one cell to 25 pixels. 
    private int [][] maze;// this makes an array which the values of maze[i][j] can hold a value of being a wall or a road that the cat and dogs can walk through.
    //the road has little white dots/pellets the cat can eat. When the cat eats the pellet the road become empty.
    private static int wall = 0;
    private static int road = 1;
    private static int roadDot = 2;//a road with a white dot
    /**
     * Constructor for objects of class extension
     */
    public TheGame(){
        JFrame window = new JFrame("Catman");
        //window.setPreferredSize(new Dimension(((cellSize*colomns)+(cellSize*border)),((cellSize*rows)+(cellSize*border))));//sets the size of the window
        window.getContentPane().setBackground(new Color(255,238,238));// I made the background colour baby pink. This correlates to my relevant implications of aesthetics
        setLocationRelativeTo(null);//this is meant to set the window to the middle of the computer screen.
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);//makes the window close when it needs to
        window.pack();//this sizes the window to the preffered size
        window.toFront();//makes the game the focused window.
        window.setVisible(true);//makes it visible
        //this.maze = makeMaze();
    }
    public void makeMaze(){
        int [][] maze;
    }

}
