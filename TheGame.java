
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;//actionlistener
import javax.swing.ImageIcon;
import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TheGame extends JFrame implements ActionListener, KeyListener {
    int columns = 28;//this is setting the number of cell columns in the game panel to 21.
    int rows = 50; //sets the cell rows in the game panel to 50.
    int mazeRows = 31;//this sets the maze rows to 31.
    int mazeColumns = 28;//this sets the maze columns to 28
    int cellSize = 23;// this sets the size of one cell to 23 pixels.
    public static int[][] maze =
            {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 2, 0, 4, 4, 0, 1, 0, 4, 4, 4, 0, 1, 0, 0, 1, 0, 4, 4, 4, 0, 1, 0, 4, 4, 0, 2, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 0, 4, 4, 0, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 0, 4, 7, 7, 7, 7, 4, 0, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                    {0, 2, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 2, 0},
                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
    // this makes an array which the values of the maze and can hold a value of being a wall or a road that the cat and dogs can walk through.
    //the road has little white dots/pellets the cat can eat. When the cat eats the pellet the road become empty.
    private static int river = 0;//a river acts as a wall the cat can't walk through.
    private static int roadDot = 1;//a road with a white dot
    private static int fish = 2;//a road with a fish, which acts as a power up pellet for the kitty.
    private static int emptyRoad = 3;//this is a road without a dot
    private static int emptySpace = 4;//this is a road that the cat doesn't go in/ it's a road out of bounds.
    final static int yOffset = 60;//added this because if I don't, there's no distance between the window bar and the frame and the top part of the frame isn't seen.
    int gameTitleScreen = 1;
    int gameRunning = 1;//this is if the game starts playing, the game state will turn to 1 and the game will start.
    int gameEnding = 2;//the game state will change to 2 if the game ends
    int gameState = gameTitleScreen; // this states the game state to 0 which is the title screen
    String leftCat = "catLeft.png"; //this is a png of the cat facing left
    String rightCat = "catRight.png"; //this is a png of the cat facing right
    String downCat = "catDown.png"; //this is a png of the cat facing down
    String upCat = "catUp.png"; //png of cat facing up
    int diameter = 5;//diameter of the black dot in the middle
    int fishDiameter = 18;//diameter of the 'power up' pellets/fish
    int catPosX = 14;//the x coordinate of the cat
    int catPosY = 23;//the y coordinate of the cat
    int catStartX = catPosX;
    int catStartY = catPosY;
    String catPic = leftCat;//setting the initial cat position to left
    /*ImageIcon catIcon = new ImageIcon(catPic);
    Image catImage = catIcon.getImage();
    Image modifiedCatImage = catImage.getScaledInstance(cellSize * 2, cellSize * 2, Image.SCALE_SMOOTH);//resizing the image */
    double catVelX = 0;//velocity of the cat x axis
    double catVelY = 0;//velocity of the cat y axis
    int catScore;
    int pointOffset = 10;
    private Image doubleBufferImage = null;
    private Graphics doubleBufferGraphics = null;
    int catVelocity = 4; //Initialize the velocity variable of the cat private
    int catPowerVelocity = 6;
    int curretlyDisplaying;
    private final int titleScreen = 0;
    private final int gameIsPlaying = 1;
    private final int endingScreen = 2;
    Timer timer; //A timer variable that handles how often the cat should move (automatically)
    private int direction; //The current direction of the cat
    private int oldDirection;
    int catX;
    int catY;
    int lives = 3;
    boolean isGameRunning = true;
    boolean isLost = false;
    boolean paintDogs = true;
    Dogs[] dogs = {new Dogs(5,5,3, this), new Dogs(22,6,1, this), new Dogs(1,10,1, this), new Dogs(20,20,1, this)};

    /**
     * Constructor for objects of class extension
     */
    public TheGame() {
        this.setTitle("Catman");
        this.setPreferredSize(new Dimension((cellSize * columns), (cellSize * rows)));//sets the size of the window
        this.getContentPane().setBackground(new Color(255, 255, 255));// I made the background colour baby pink. This correlates to my relevant implications of aesthetics. (30th of may I changed it to white)
        this.setLayout(null);//this is meant to set the window to the middle of the computer screen.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//makes the window close when it needs to
        this.pack();//this sizes the window to the preferred size
        this.toFront();//makes the game the focused window.
        this.setVisible(true);//makes it visible
        this.addKeyListener(this);
        repaint();//this prints out the maze.
        //timer = new Timer( (int)(1000/catVelocity), this); //Make a timer that executes every 1000 Millisecond/cat velocity
        //If the cat velocity is 2, that means it moves 2 times per second (500 ms)
        //timer.start(); //Start the timer after creating it.


        while (isGameRunning) {
            try {
                Thread.sleep(1000 / catVelocity);
                repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void drawTitleScreen(Graphics2D g2) {

        try {
            Font minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("Minecraft copy.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraft);
            g2.setColor(Color.PINK);
            g2.setFont(minecraft);
            g2.drawString(Integer.toString(catScore), 25 * cellSize, 2 * cellSize + pointOffset);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        // Set background color
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Set title text color and font
        g2.setColor(Color.WHITE);

        // Draw the title text
        Font oldFont = g2.getFont();
        Font titleFont = oldFont.deriveFont(72f);
        g2.setFont(titleFont);
        int x = (getWidth() - 300) / 2;
        int y = (getHeight())/ 2;
        g2.drawString("Cat man", x, y);
        g2.setFont(oldFont);

        // Set instruction text color and font
        x += 10;
        y +=  60;
        g2.drawString("Press Enter to Start", x, y);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;



        if(!isGameRunning) {//If the boolean is false stop running the game (Probably means you lost)
            if(!isLost) {
                drawTitleScreen(g2);
            }
            return;
        }


        //these are the values of the array that makes up the maze. 0's are walls, 1's are roads with dots,
        //2's are pellets, 3's are empty roads 4's are empty spaces that the cat can't go in. (The centre, where all the 4's are, is where the dogs will appear. The cat can't go there.)

        //creating the font for my game, this relates to my aesthetics relevant implications. If the score-keep was in new times roman, it wouldn't fit the theme of the game.



        switch (gameState) {
            case 0://if the game state is 0, the title screen will display
                //drawing the title screen
                super.paint(g);
                //the bg
                g2.setColor(new Color (255,255,216));
                g2.fillRect(0,0,this.getWidth(),this.getHeight());
                //writing the title name
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
                String text = "Catman";
                int x = this.getWidth() / 2 - g2.getFontMetrics().stringWidth(text) / 2;
                int y = cellSize * 8;
                //text shadow
                g2.setColor(new Color(255, 255, 255));
                g2.drawString(text,x+5,y+5);
                //main text
                g2.setColor(new Color(255,226,241));
                g2.drawString(text, x, y);
                break;
            case 1://if the game state is 1 then the game will play
                //printing the cat to move directions
                switch (direction) {
                    case KeyEvent.VK_RIGHT:
                        right();
                        break;
                    case KeyEvent.VK_LEFT:
                        left();
                        break;
                    case KeyEvent.VK_DOWN:
//                        if (maze[catPosY + 1][catPosX] == 0 || maze[catPosY + 1][catPosX] == 4) break;
                        down();
                        break;
                    case KeyEvent.VK_UP:
//                        if (maze[catPosY - 1][catPosX] == 0 || maze[catPosY - 1][catPosX] == 4) break;
                        up();
                        break;
                }
                //now I need to print the array
                for (int i = 0; i < mazeColumns; i++) {
                    for (int j = 0; j < mazeRows; j++) {
                        switch (maze[j][i]) {
                            case 0://sets all the 0's to a river that acts as a wall
                                g2.setColor(new Color(62, 164, 240));//setting the colour to blue for water
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//this print out the wall
                                break;
                            case 1://sets all the 1's to a road with a dot.
                                //I need to draw a white dot in the middle of the road
                                g2.setColor(new Color(255, 238, 238));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//setting the bg color
                                g2.setColor(new Color(0, 0, 0));//made the dot black because if it's white, you can barely see it with the pink bg.
                                g2.fillOval(i * cellSize + (cellSize - diameter) / 2, j * cellSize + (cellSize - diameter) / 2 + yOffset, diameter, diameter); //this creates the dot in the middle.
                                break;
                            case 2://sets all the 2's to power pellets with bigger dots
                                //i might change this later to a png of a fish, if I have time
                                g2.setColor(new Color(255, 238, 238));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//creates the pink bg
                                g2.setColor(new Color(255, 160, 122));//setting the pellet/dot colour to salmon colour
                                g2.fillOval(i * cellSize + (cellSize - fishDiameter) / 2, j * cellSize + (cellSize - fishDiameter) / 2 + yOffset, fishDiameter, fishDiameter);//creates a salmon coloured circle slightly larger than the black dot.
                                //this is the power up pellet/fish
                                break;
                            case 3://this sets all the 3's into an empty road
                                g2.setColor(new Color(255, 238, 238));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);
                                //just an empty road
                                break;
                            case 4://setting the 4's to out of bounds colors/places the cat can't go to
                                g2.setColor(new Color(255, 255, 255));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);
                                //empty space cat can't go ind
                                break;
                            case 5:
                                //this is where the cat will be spawned.
                                //set the background to pink
                                g2.setColor(new Color(255, 238, 238));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//sets the bg color to pink
                                //printing the cat png
                                ImageIcon catIcon = new ImageIcon(catPic);
                                Image catImage = catIcon.getImage();
                                Image modifiedCatImage = catImage.getScaledInstance(cellSize * 2, cellSize * 2, Image.SCALE_SMOOTH);//resizing the png to fit the roads.
                                catIcon = new ImageIcon(modifiedCatImage);
                                catX = catPosX * cellSize + (cellSize - modifiedCatImage.getWidth(this)) / 2;//getting the width of the cat png
                                catY = catPosY * cellSize + (cellSize - modifiedCatImage.getHeight(this)) / 2 + yOffset;//getting the height of the cat png
                                catIcon.paintIcon(this, g, catX, catY - 2);//printing the cat in the center of cell 5.
                                break;
                            case 6://sets the 6 into a power up pellet but if I have time, I'll change it into another food png.
                                g2.setColor(new Color(255, 238, 238));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//creates the background pink
                                g2.setColor(new Color(255, 32, 25));
                                g2.fillOval(i * cellSize + (cellSize - fishDiameter) / 2, j * cellSize + (cellSize - fishDiameter) / 2 + yOffset, fishDiameter, fishDiameter);//creates a big red dot as another power up pellet
                                break;
                            case 7://this is where the dogs will be spawned.
//                                g2.setColor(new Color(255, 255, 255));//setting color to white
//                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//filling the cell in the colour white
//                                //printing the dogs
//                                for (int k=0; k<dogs.length; k++){
//                                    Dogs dog = dogs[k];
//                                    ImageIcon dogIcon = new ImageIcon(dog.getDogPic());
//                                    Image dogImage = dogIcon.getImage();
//                                    Image modifiedDogImage = dogImage.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);//resizing the png to fit the roads.
//                                    dogIcon = new ImageIcon(modifiedDogImage);
//                                    dog.setDogX(dog.getDogPosX() * cellSize + (cellSize - modifiedDogImage.getWidth(this)) / 2);
//                                    dog.setDogY(dog.getDogPosY() * cellSize + (cellSize - modifiedDogImage.getHeight(this)) / 2 + yOffset);
//                                    dogIcon.paintIcon(this, g, dog.getDogPosX(), dog.getDogPosY() - 2);
//                                }
                                break;
                            /*case 8:
                                g2.setColor(new Color(255, 255, 255));//setting color to white
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//filling the cell in the colour white
                                //printing the dogs
                                ImageIcon dogIcon = new ImageIcon(dogPic);
                                Image dogImage = dogIcon.getImage();
                                Image modifiedDogImage = dogImage.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);//resizing the png to fit the roads.
                                catIcon = new ImageIcon(modifiedDogImage);
                                dogX = dogPosX * cellSize + (cellSize - modifiedDogImage.getWidth(this)) / 2;
                                dogY = dogPosY * cellSize + (cellSize - modifiedDogImage.getHeight(this)) / 2 + yOffset;
                                catX = catPosX * cellSize + (cellSize - modifiedDogImage.getWidth(this)) / 2;//getting the width of the cat png
                                catY = catPosY * cellSize + (cellSize - modifiedDogImage.getHeight(this)) / 2 + yOffset;//getting the height of the cat png
                                catIcon.paintIcon(this, g, dogX, dogY - 2);//printing the cat in the center of cell 5.
                                //System.out.println("dog"+ dogX + " " + dogY);
                                break;*/
                        }
                    }

                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameState);
        }


        if(paintDogs) {
            for (int k=0; k<dogs.length; k++){
                dogs[k].paint(g2);
                if(dogs[k].getDogPosX() == catPosY  && dogs[k].getDogPosY() == catPosX) {
                    //If the locations of the dogs are the same as the cat it means the cat got caught!


                    System.out.println("CAT CAUGHT   " + dogs[k].getDogPosX() + "  " + dogs[k].getDogPosY());
                    //dog X and dog Y accidentally switched while coding.
                    catPosX = catStartX;
                    catPosY = catStartY;
                    left();
                        lives--;
                        if(lives <=0) {
                            isLost = true;

                            isGameRunning = false; //Stop running the game, probably show a lose screen here.
                        }


                }
            }
//	        paintDogs = false;
        }



    }

    /*public void renderOffScreen(Graphics2D g2){

    }*/
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void up() {
        if (catPosY - 1 < 0) {
            // Move cat to the bottom row
            maze[mazeRows - 1][catPosX] = 5;
            maze[catPosY][catPosX] = 3;
            catPosY = mazeRows - 1;
            catPic = upCat;
        }else if (maze[catPosY - 1][catPosX] == 1) {
            catPic = upCat;
            catScore += 200;
            catPosY--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY + 1][catPosX] = 3;
        } else if (maze[catPosY - 1][catPosX] == 2) {
            catPic = upCat;
            catScore += 400;
            catPosY--;
            catVelocity = catPowerVelocity;
            maze[catPosY][catPosX] = 5;
            maze[catPosY + 1][catPosX] = 3;
        } else if (maze[catPosY - 1][catPosX] == 3) {
            catPic = upCat;
            catPosY--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY + 1][catPosX] = 3;
        } else if (maze[catPosY - 1][catPosX] == 6) {
            catPic = upCat;
            catPosY--;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY + 1][catPosX] = 3;
        }
    }

    public void down() {
        if(catPosY+1 >  mazeRows-1) {
            maze[1][catPosX] = 5;
            maze[catPosY][catPosX] = 3;
            catPosY = 1;
            catPic = downCat;

        }
        else if (maze[catPosY + 1][catPosX] == 1) {
            catPic = downCat;
            catScore += 200;
            catPosY++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY - 1][catPosX] = 3;
        } else if (maze[catPosY + 1][catPosX] == 2) {
            catPic = downCat;
            catPosY++;
            catVelocity = catPowerVelocity;
            catScore += 400;
            maze[catPosY][catPosX] = 5;
            maze[catPosY - 1][catPosX] = 3;
        } else if (maze[catPosY + 1][catPosX] == 3) {
            catPic = downCat;
            catPosY++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY - 1][catPosX] = 3;
        } else if (maze[catPosY + 1][catPosX] == 6) {
            catPic = downCat;
            catPosY++;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY - 1][catPosX] = 3;
        }
    }

    public void left() {
        if(catPosX-1 < 0) {
            maze[catPosY][mazeColumns-1] = 5;
            maze[catPosY][catPosX] = 3;
            catPosX = mazeColumns-1;
            catPic = leftCat;

        }
        else if (maze[catPosY][catPosX - 1] == 1) {
            catPic = leftCat;
            catPosX--;
            catScore += 200;
            //System.out.println(catScore);
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX + 1] = 3;
        } else if (maze[catPosY][catPosX - 1] == 2) {
            catPic = leftCat;
            catScore += 400;
            catVelocity = catPowerVelocity;
            catPosX--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX + 1] = 3;
        } else if (maze[catPosY][catPosX - 1] == 3) {
            catPic = leftCat;
            catPosX--;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX + 1] = 3;
        } else if (maze[catPosY][catPosX - 1] == 6) {
            catPic = leftCat;
            catPosX--;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX + 1] = 3;
        }
    }

    public void right() {
        if(catPosX+1 > mazeColumns-1) {
            maze[catPosY][1] = 5;
            maze[catPosY][catPosX] = 3;
            catPosX = 1;
            catPic = rightCat;

        }
        else if (maze[catPosY][catPosX + 1] == 1) {
            catPic = rightCat;
            catScore += 200;
            catPosX++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX - 1] = 3;
        } else if (maze[catPosY][catPosX + 1] == 2) {
            catPic = rightCat;
            catPosX++;
            catVelocity = catPowerVelocity;
            catScore += 400;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX - 1] = 3;
        } else if (maze[catPosY][catPosX + 1] == 3) {
            catPic = rightCat;
            catPosX++;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX - 1] = 3;
        } else if (maze[catPosY][catPosX + 1] == 6) {
            catPic = rightCat;
            catPosX++;
            catScore += 2000;
            maze[catPosY][catPosX] = 5;
            maze[catPosY][catPosX - 1] = 3;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER) {
            isGameRunning = true;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            if (!(maze[catPosY - 1][catPosX] == 0 || maze[catPosY - 1][catPosX] == 4)) {
                direction = KeyEvent.VK_UP;
                System.out.println(maze[catPosY - 1][catPosX]);

            }
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            if (!(maze[catPosY + 1][catPosX] == 0) && !(maze[catPosY + 1][catPosX] == 4)) {
                direction = KeyEvent.VK_DOWN;
            }
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            if (!(maze[catPosY][catPosX - 1] == 0) && !(maze[catPosY][catPosX - 1] == 4)) {
                direction = KeyEvent.VK_LEFT;
                oldDirection = direction;
            }
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if (!(maze[catPosY][catPosX + 1] == 0) && !(maze[catPosY][catPosX + 1] == 4)) {
                direction = KeyEvent.VK_RIGHT;
                oldDirection = direction;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}