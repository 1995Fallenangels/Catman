
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
 * @version 30/05
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionEvent;//actionlistener
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
public class TheGame extends JFrame /* implements ActionListener*/
{
    int columns = 28;//this is setting the number of cell colomns in the maze to 21.
    int rows = 50; //sets the cell rows in the maze to 18.
    int topBorder = 3; //how far the maze is away from the edge of the panel.
    int mazeRows=31;
    int mazeColumns=28;
    int cellSize = 23;// this sets the size of one cell to 25 pixels. 
    private int[][] maze;// this makes an array which the values of maze[i][j] can hold a value of being a wall or a road that the cat and dogs can walk through.
    //the road has little white dots/pellets the cat can eat. When the cat eats the pellet the road become empty.
    private static int river = 0;//a river acts as a wall the cat can't walk through.
    private static int roadDot = 1;//a road with a white dot
    private static int fish = 2;//a road with a fish, which acts as a power up pellet for the kitty.
    private static int emptyRoad = 3;//this is a road without a dot
    private static int emptySpace = 4;//this is a road that the cat doesn't go in/ it's a road out of bounds.
    final static int yOffset = 60;//added this because if i don't, there's no distance between the window bar and the frame and the top part of the fram isn't seen.
    boolean gameRunning= false;//this is if the game starts playing, it will turn to true.
    final String fileName = "catLeft.png";
    int catStartX= 9*cellSize;//the x coordinate of where the cat starts
    int catStartY = 20*cellSize;//the y coordinate of where the cat starts
    /**
     * Constructor for objects of class extension
     */
    public TheGame(){
        //JFrame window = new JFrame("Catman");
        this.setTitle("Catman");
        this.setPreferredSize(new Dimension((cellSize*columns),(cellSize*rows)));//sets the size of the window
        this.getContentPane().setBackground(new Color(255,255,255));// I made the background colour baby pink. This correlates to my relevant implications of aesthetics
        this.setLayout(null);//this is meant to set the window to the middle of the computer screen.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//makes the window close when it needs to
        this.pack();//this sizes the window to the preffered size
        this.toFront();//makes the game the focused window.
        this.setVisible(true);//makes it visible
        repaint();//this prints out the maze.
    }
    @Override
    public void paint(Graphics g){
        //these are the values of the array that makes up the maze. 0's are walls, 1's are roads with dots,
        //2's are pellets, 3's are empty roads 4's are empty spaces that the cat can't go in. (The centre, where all the 4's are, is where the dogs will appear. The cat can't go there.)
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int maze[][] = 
            {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
             {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
             {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
             {0,2,0,4,4,0,1,0,4,4,4,0,1,0,0,1,0,4,4,4,0,1,0,4,4,0,2,0},
             {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
             {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
             {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},
             {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0},
             {0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0},
             {0,0,0,0,0,0,1,0,0,0,0,0,3,0,0,3,0,0,0,0,0,1,0,0,0,0,0,0},
             {4,4,4,4,4,0,1,0,0,0,0,0,3,0,0,3,0,0,0,0,0,1,0,4,4,4,4,4},
             {4,4,4,4,4,0,1,0,0,3,3,3,3,3,3,3,3,3,3,0,0,1,0,4,4,4,4,4},
             {4,4,4,4,4,0,1,0,0,3,0,0,0,4,4,0,0,0,3,0,0,1,0,4,4,4,4,4},
             {0,0,0,0,0,0,1,0,0,3,0,4,4,4,4,4,4,0,3,0,0,1,0,0,0,0,0,0},
             {3,3,3,3,3,3,1,3,3,3,0,4,4,4,4,4,4,0,3,3,3,1,3,3,3,3,3,3},
             {0,0,0,0,0,0,1,0,0,3,0,4,4,4,4,4,4,0,3,0,0,1,0,0,0,0,0,0},
             {4,4,4,4,4,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,4,4,4,4,4},
             {4,4,4,4,4,0,1,0,0,3,3,3,3,3,3,3,3,3,3,0,0,1,0,4,4,4,4,4},
             {4,4,4,4,4,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,4,4,4,4,4},
             {0,0,0,0,0,0,1,0,0,3,0,0,0,0,0,0,0,0,3,0,0,1,0,0,0,0,0,0},
             {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
             {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
             {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
             {0,2,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,2,0},
             {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
             {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
             {0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0},
             {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
             {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
             {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
             {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};
        //now i need to print the array
        //later i need to add a distance between the maze and the top and bottom of the window so I can add a scorekeep in the top right corner and the healthbar at the bottom left. 
        //also need to fix the fact that the first row isn't seen because of the window bar. (I need to fix the .pack())
        int diameter= 5;
        int fishDiameter= 18;
        for(int i = 0; i < mazeColumns; i++){
            for(int j = 0; j< mazeRows; j++){
                switch (maze[j][i]){
                    case 0:
                        g2.setColor(new Color(62,164,240));//setting the colour to blue for water
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);//this print out the 
                        break;
                    case 1:
                        //i need to draw a white dot in the middle of the road
                        //g2.fillRect(i*cellSize,j*cellSize, cellSize, cellSize);//doing fillRect for now just for testing. I need to change it to fillOval later and figure out how to print the circles in the middle.
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        g2.setColor(new Color (0,0,0));//made it black because if it's white, you can barely see it.
                        g2.fillOval(i*cellSize+(cellSize-diameter)/2, j*cellSize+(cellSize-diameter)/2+yOffset, diameter, diameter); //this creates the dot in the middle.
                        //setting the colour to white, but need to change it because it looks grey.
                        break;
                    case 2:
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        g2.setColor(new Color (255,160,122));
                        g2.fillOval(i*cellSize+(cellSize-fishDiameter)/2, j*cellSize+(cellSize-fishDiameter)/2+yOffset, fishDiameter, fishDiameter);
                        break;
                    case 3:
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        break;
                    case 4:
                        g2.setColor(new Color(255,255,255));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        break;
                }    
                }
            }
        ImageIcon image = new ImageIcon(fileName);
        image.paintIcon(this,g, catStartX, catStartY);//prints the cat at it's starting point. Now I need to resize the image.
        //Image scaleImage = image.getImage().getScaledInstance(5, 5,Image.SCALE_DEFAULT);
        //Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);//I'm trying to resize the image but it's not working.
    }
    public void moveCat(){
    
        
    }
    public void moveDogs(){
    
    }
    public void healthBar(){
    
    }
    public void scoreKeep(){
    
    }
            }