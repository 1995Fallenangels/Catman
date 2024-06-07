
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
 * @version 01/06
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;//actionlistener
import javax.swing.ImageIcon;
import java.awt.Image;
public class TheGame extends JFrame implements ActionListener, KeyListener
{
    int columns = 28;//this is setting the number of cell colomns in the maze to 21.
    int rows = 50; //sets the cell rows in the maze to 18.
    int topBorder = 3; //how far the maze is away from the edge of the panel.
    int mazeRows=31;
    int mazeColumns=28;
    int cellSize = 23;// this sets the size of one cell to 25 pixels. 
    private int[][] maze =
            {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
            {0,2,0,4,4,0,1,0,4,4,4,0,1,0,0,1,0,4,4,4,0,1,0,4,4,0,2,0},
            {0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,6,1,1,1,1,1,1,1,1,1,1,1,1,0},
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
            {0,2,1,1,0,0,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,0,0,1,1,2,0},
            {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
            {0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0},
            {0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0},
            {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};
    // this makes an array which the values of the maze and can hold a value of being a wall or a road that the cat and dogs can walk through.
    //the road has little white dots/pellets the cat can eat. When the cat eats the pellet the road become empty.
    private static int river = 0;//a river acts as a wall the cat can't walk through.
    private static int roadDot = 1;//a road with a white dot
    private static int fish = 2;//a road with a fish, which acts as a power up pellet for the kitty.
    private static int emptyRoad = 3;//this is a road without a dot
    private static int emptySpace = 4;//this is a road that the cat doesn't go in/ it's a road out of bounds.
    final static int yOffset = 60;//added this because if i don't, there's no distance between the window bar and the frame and the top part of the frame isn't seen.
    boolean gameRunning= false;//this is if the game starts playing, it will turn to true.
    String leftCat = "catLeft.png";
    String rightCat = "catRight.png";
    String downCat = "catDown.png";
    String upCat = "catUp.png";
    int diameter= 5;//diameter of the black dot in the middle
    int fishDiameter= 18;//diameter of the 'power up' pellets/fish
    int catPosX= 14;//the x coordinate of the cat 
    int catPosY = 23;//the y coordinate of the cat
    String catPic = leftCat;
    ImageIcon catIcon = new ImageIcon(catPic);
    Image catImage = catIcon.getImage();
    Image modifiedCatImage= catImage.getScaledInstance(cellSize*2, cellSize*2, java.awt.Image.SCALE_SMOOTH);//resizing the image
    double catVelX = 0;//velocity of the cat x axis
    double catVelY = 0;//velocity of the cat y axis
    int catScore;
    boolean isGameRunning = false;
    /**
    * Constructor for objects of class extension
    */
    public TheGame(){
        //JFrame window = new JFrame("Catman");
        this.setTitle("Catman");
        this.setPreferredSize(new Dimension((cellSize*columns),(cellSize*rows)));//sets the size of the window
        this.getContentPane().setBackground(new Color(255,255,255));// I made the background colour baby pink. This correlates to my relevant implications of aesthetics. (30th of may I changed it to white)
        this.setLayout(null);//this is meant to set the window to the middle of the computer screen.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//makes the window close when it needs to
        this.pack();//this sizes the window to the preffered size
        this.toFront();//makes the game the focused window.
        this.setVisible(true);//makes it visible
        this.addKeyListener(this);
        repaint();//this prints out the maze.
    }
    @Override
    public void paint(Graphics g){
        //these are the values of the array that makes up the maze. 0's are walls, 1's are roads with dots,
        //2's are pellets, 3's are empty roads 4's are empty spaces that the cat can't go in. (The centre, where all the 4's are, is where the dogs will appear. The cat can't go there.)
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        //now i need to print the array
        for(int i = 0; i < mazeColumns; i++){
            for(int j = 0; j< mazeRows; j++){
                switch (maze[j][i]){
                    case 0:
                        g2.setColor(new Color(62,164,240));//setting the colour to blue for water
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);//this print out the wall
                        break;
                    case 1:
                        //i need to draw a white dot in the middle of the road
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        g2.setColor(new Color (0,0,0));//made it black because if it's white, you can barely see it.
                        g2.fillOval(i*cellSize+(cellSize-diameter)/2, j*cellSize+(cellSize-diameter)/2+yOffset, diameter, diameter); //this creates the dot in the middle.
                        //setting the colour to white, but need to change it bc u can barely see it with the pink background
                        break;
                    case 2:
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);//creates the background pink
                        g2.setColor(new Color (255,160,122));
                        g2.fillOval(i*cellSize+(cellSize-fishDiameter)/2, j*cellSize+(cellSize-fishDiameter)/2+yOffset, fishDiameter, fishDiameter);//creates a salmon coloured circle slightly larger than the black dot.
                        //this is the power up pellet/fish
                        break;
                    case 3:
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        //just an empty road
                        break;
                    case 4:
                        g2.setColor(new Color(255,255,255));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        //empty space cat can't go ind
                        break;
                    case 5:
                        //this is where the cat will be spawned.
                        //set the background to pink
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);
                        //printing the cat png
                        ImageIcon catIcon = new ImageIcon(catPic);
                        Image catImage = catIcon.getImage();
                        Image modifiedCatImage= catImage.getScaledInstance(cellSize*2, cellSize*2, java.awt.Image.SCALE_SMOOTH);
                        catIcon = new ImageIcon(modifiedCatImage);
                        int catWidth = catPosX*cellSize+(cellSize-modifiedCatImage.getWidth(this)) / 2;//getting the width of the cat png
                        int catHeight = catPosY*cellSize+(cellSize-modifiedCatImage.getHeight(this)) / 2+yOffset;//getting the height of the cat png
                        catIcon.paintIcon(this,g,catWidth,catHeight-2);//printing the cat in the center of cell 5.
                        break;
                    case 6:
                        g2.setColor(new Color(255,238,238));
                        g2.fillRect(i*cellSize,j*cellSize+yOffset, cellSize, cellSize);//creates the background pink
                        g2.setColor(new Color (	255,	32,	25));
                        g2.fillOval(i*cellSize+(cellSize-fishDiameter)/2, j*cellSize+(cellSize-fishDiameter)/2+yOffset, fishDiameter, fishDiameter);
                        break;

                }    
                }
            }

    }
@Override
    public void actionPerformed(ActionEvent e){

    }
    public void up(){
        if (maze[catPosY-1][catPosX]==1){
            catPic = upCat;
            catScore += 200;
            //System.out.println(catScore);
            catPosY--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY+1][catPosX] = 3;
            repaint();
        } else if (maze[catPosY-1][catPosX]== 2) {
            catPic = upCat;
            catScore += 400;
            catPosY--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY+1][catPosX] = 3;
            repaint();
        } else if (maze[catPosY-1][catPosX]== 3) {
            catPic = upCat;
            catPosY--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY+1][catPosX] = 3;
            repaint();
        } else if (maze[catPosY-1][catPosX]== 6) {
            catPic = upCat;
            catPosY--;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY+1][catPosX] = 3;
            repaint();
        }
        System.out.println(catScore);
    }
    public void down(){
        if (maze[catPosY+1][catPosX]== 1){
            catPic = downCat;
            catScore += 200;
            catPosY++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY-1][catPosX] = 3;
            repaint();
        } else if (maze[catPosY+1][catPosX]== 2) {
            catPic = downCat;
            catPosY++;
            catScore+= 400;
            maze[catPosY][catPosX] = 5;
            maze[catPosY-1][catPosX] = 3;
            repaint();
        }
        else if (maze[catPosY+1][catPosX]== 3) {
            catPic = downCat;
            catPosY++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY-1][catPosX] = 3;
            repaint();
        } else if (maze[catPosY+1][catPosX]== 6) {
            catPic = downCat;
            catPosY++;
            catScore+= 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY-1][catPosX] = 3;
            repaint();
        }
        System.out.println(catScore);
    }
    public void left(){
        if (maze[catPosY][catPosX-1]== 1){
            catPic = leftCat;
            catPosX--;
            catScore += 200;
            //System.out.println(catScore);
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX+1] = 3;
            repaint();
        } else if (maze[catPosY][catPosX-1]== 2) {
            catPic = leftCat;
            catScore += 400;
            catPosX--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX+1] = 3;
            repaint();
        }
        else if (maze[catPosY][catPosX-1]== 3) {
            catPic = leftCat;
            catPosX--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX+1] = 3;
            repaint();
        } else if (maze[catPosY][catPosX-1]== 6) {
            catPic = leftCat;
            catPosX--;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX+1] = 3;
            repaint();
        }
        System.out.println(catScore);
    }
    public void right(){
        if (maze[catPosY][catPosX+1]==1){
            catPic = rightCat;
            catScore +=200;
            //System.out.println(catScore);
            catPosX++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX-1] = 3;
            repaint();
        } else if (maze[catPosY][catPosX+1]== 2){
            catPic = rightCat;
            catPosX++;
            catScore += 400;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX-1] = 3;
            repaint();
        } else if (maze[catPosY][catPosX+1]== 3){
            catPic = rightCat;
            catPosX++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX-1] = 3;
            repaint();
        } else if (maze[catPosY][catPosX+1]== 6) {
            catPic = rightCat;
            catPosX++;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX-1] = 3;
            repaint();
        }
        System.out.println(catScore);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();   
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
            up();
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
            down();
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
            left();
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
            right();
        }
    }
    public void healthBar(){
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        /*switch(e.getKeyChar()) {
            case 'a': catIcon.setLocation(catIcon.getX()-1, catIcon.getY());
        }*/
    }
            }