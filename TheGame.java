/**
 * Write a description of class TheGame here.
 * This is the code for the game 'Catman' I'm creating for my computer science assignment. Catman is inspired by the game Pacman.
 * In this game, a cat is being chased by dogs. When the cat gets caught by a dog, the cat loses 3 lives. The cat has 9 lives in total.
 * There will be fish the cat can eat.
 * There will need to be 5 main components that make up this game which is:
 * 1) a printing component. (prints everything) This prints the maze, the cat, the dogs, the health bar, the score, the intro screen, the losing screen, the winning screen, etc.
 * 2) a component that moves the characters
 * 3) a scorekeeping component. This will show the score of the user
 * 4) a healthbar. This will show how many lives the kitten has left. The cat will have nine lives represented as hearts (because cats have 9 lives).
 * When the cat runs into a dog, the cat will lose 3 lives.
 * when the player wins or loses and wants to restart the game, they need to press enter and they can restart the game
 *
 * @author gabriella bella rose bitju
 * @version 01/08
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class TheGame extends JPanel implements KeyListener  {

    int columns = 28;//this is setting the number of cell columns in the game panel to 21.
    int rows = 50; //sets the cell rows in the game panel to 50.
    int mazeRows = 31;//this sets the maze rows to 31.
    int mazeColumns = 28;//this sets the maze columns to 28
    int cellSize = 23;// this sets the size of one cell to 23 pixels.
    public static int[][] maze =
            {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
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
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 4, 4, 4, 4, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 4, 4, 4, 4, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
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
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
    // this makes an array which the values of the maze and can hold a value of being a wall or a road that the cat and dogs can walk through.
    //the road has little dots/pellets the cat can eat. When the cat eats the pellet the road become empty.
    //0 = a river. It acts as a wall the cat can't walk through.
    //1 = a road with a dot. Those are 200 points each
    //2 = a road with a fish, which acts as a power up pellet for the kitty
    //3 = a road without a dot
    //4 = a road that the cat doesn't go in, and only the dogs can go in
    final static int yOffset = 60;//added this because if I don't, there's no distance between the window bar and the frame and the top part of the frame isn't seen.
    int gameState = 1; // this states the game state to 0 which is the title screen
    String leftCat = "img/ragdollLeft.png"; //this is a png of the cat facing left
    String rightCat = "img/ragdollRight.png"; //this is a png of the cat facing right
    String downCat = "img/ragdollDown.png"; //this is a png of the cat facing down
    String upCat = "img/ragdollUp.png"; //png of cat facing up
    String newHeartsPic = "img/newHearts.png";
    String puppyPic = "img/puppy.png";//used in the losing screen
    String kittyPic = "img/kitty.png";//used in the winning screen

    int diameter = 5;//diameter of the black dot in the middle
    int fishDiameter = 18;//diameter of the 'power up' pellets/fish
    int catPosX = 14;//the x coordinate of the cat
    int catPosY = 23;//the y coordinate of the cat
    int catStartX = catPosX;//the x coordinate of the cat
    int catStartY = catPosY;//the y coordinate of the cat
    String catPic = leftCat;//setting the initial cat position to left
    int pointOffset = 10;// this is used to set the points coordinates to be 10 pixels below because without this y offset, the top of the panel will cover it.
    int catVelocity = 4; //Initialize the velocity variable of the cat private
    int catPowerVelocity = 6;// when the cat gets a power up pellet, the velocity will be increase, making the cat go faster
    int powerUpDuration = 4;//Power up duration in seconds
    private int direction = 0; //The current direction of the cat
    int catScore;//this is the catScore
    int catX;//needed for the X coordinates for the cat to be printed
    int catY;//needed for the Y coordinates for the cat to be printed
    int lives = 3;//how many lives the cat has
    boolean isGameRunning = false;//the game is not running at first
    boolean isLost = false;//this boolean is used for if the player lost
    boolean paintDogs = true;//boolean that says to paint the dogs
    boolean isWon = false;//this boolean is used for if the player won
    Dogs[] dogs = {new Dogs(5, 6, 2)
            , new Dogs(20, 6, 1)
            , new Dogs(20, 21, 2)
            , new Dogs(5, 21, 2)
            , new Dogs(29, 15, 3)
            , new Dogs( 11, 15, 3)};//I initialized the dogs here. I have made 6 dogs. I've set their spawn points and velocities.
    private Timer timer; // Timer field
    private Timer catTimer;//
    private Timer removePowerUpTimer;//

    public TheGame() {
        this.addKeyListener(this);//adds key listener which basically does the code if you press a specific key on the keyboard. it listens to keyboard input
        setFocusable(true);//this needs to be set to true so that it will respond to the keyListener. Without focus, the game won't listen to keyboard input
        requestFocusInWindow(true);//make the window be able to focus
        //cat timer is so that every (second/the cat velocity, or in this case, every 1/4th of a second) the game moves left if the player pressed left
        catTimer = new Timer(1000/catVelocity, new ActionListener() {
            //so every 1000 millisecond (1 second) divided by catVelocity (4) (1/4th of a second) it will do the following:
            //it will check the direction the player wants to go and make the cat position go that direction
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isGameRunning) return;//if the game is not running then it won't do the following
                switch (direction) {//checks the direction
                    case KeyEvent.VK_RIGHT://if the player pressed the right arrow
                        right();//the right function will be called and the cat will move right
                        break;
                    case KeyEvent.VK_LEFT://if the player pressed the left arrow
                        left();//the left function will be called and the cat will move left
                        break;
                    case KeyEvent.VK_DOWN://if the player pressed the down arrow
                        down();//the down function will be called and the cat will move down
                        break;
                    case KeyEvent.VK_UP://if the player pressed the up arrow
                        up();//the up function will be called and the cat will move up
                        break;
                }
            }
        });
        //creating the printing out timer
        timer = new Timer(15, new ActionListener() {
            //this timer will print everything every 15 milliseconds
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint(); //Call repaint every 15 millisecond
            }
        });
        //creating the removePowerUpTimer timer
        //I created this because let's say, a cat eats a pellet right, I want it to go fast for only 4 seconds.
        //But what if the player eats ANOTHER power up pellet within those 4 seconds?
        //this removePowerUpTimer will turn off the existing timer (which was turned on because the cat ate the first power up pellet)
        //then it will start a new timer. (this is coded below in my powerUpCat function.)
        removePowerUpTimer = new Timer(1000 * powerUpDuration, new ActionListener() {
            //basically after 4 seconds (after 1 second x powerUpDuration) it will do the following:
            @Override
            public void actionPerformed(ActionEvent e) {
                catTimer.setDelay(1000/catVelocity);//it will set the cat's velocity back to normal
                removePowerUpTimer.stop();//and it will stop this timer.
            }
        });
        timer.start(); // Start the timer and prints out the maze, title screen, and basically everything else
        catTimer.start();// will start to check and set the cat's next direction.
    }
    //drawing the title screen component
    private void drawTitleScreen(Graphics2D g2) {
        //setting the bg color
        g2.setColor(new Color (255,255,255));
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
        // draw the title text
        //setting the font
        Font oldFont = g2.getFont();
        Font titleFont = oldFont.deriveFont(72f);
        g2.setFont(titleFont);
        String text = "Catman";
        //getting coordinates to centre the text
        int x = this.getWidth() / 2 - g2.getFontMetrics().stringWidth(text) / 2;
        int y = (getHeight() / 2);
        //setting text color
        g2.setColor(new Color	(255,208,237));
        g2.drawString(text,x,y);//prints out cat man
        //now I want to print out a shadow for my text
        // set title text color and font
        g2.setColor(new Color(255,236,242));
        g2.drawString(text, x+5, y+5);
        g2.setFont(oldFont);
        // I want to print out hearts on the title screen to make it more aesthetic
        //making the image and resizing it to print it out
        ImageIcon heartIcon = new ImageIcon(newHeartsPic);
        Image heartImage = heartIcon.getImage();
        Image modifiedHeartImage = heartImage.getScaledInstance((cellSize * 3),(cellSize * 3), Image.SCALE_SMOOTH);//resizing the png
        heartIcon = new ImageIcon(modifiedHeartImage);
        heartIcon.paintIcon(this, g2,  x+100, y+50 );//printing the hearts under the instructions text
        //now I want to print out the cat on the screen
        ImageIcon catIcon = new ImageIcon(catPic);
        Image catImage = catIcon.getImage();
        Image modifiedCatImage = catImage.getScaledInstance(cellSize*9 , cellSize*9 , Image.SCALE_SMOOTH);//resizing the png to make it large
        catIcon = new ImageIcon(modifiedCatImage);
        x= this.getWidth()/2 - catIcon.getIconWidth()/2;
        catIcon.paintIcon(this, g2, x, y - 245);//printing the cat above the Catman title
        // print instructions
        y +=  60;//make it print below catman
        g2.drawString("Press Enter to Start", x, y);
    }
    // this is the paint component which prints out basically everything except the title screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//getting the paint component
        Graphics2D g2 = (Graphics2D) g;
        //we need to print out the cat score
        try {
            //setting the font to a pixel-like font to suit the game's aesthetics
            Font minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("Minecraft copy.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraft);
            g2.setFont(minecraft);
            //drawing a white screen behind the catScore everytime it prints the cat's score because without it, the catScore will overlap and you can't read it
            g2.setColor(Color.WHITE);
            g2.fillRect(25*cellSize, cellSize + pointOffset, 4*cellSize, cellSize);
            //printing the cat score in pink
            g2.setColor(Color.PINK);
            g2.drawString(Integer.toString(catScore), 25 * cellSize, 2 * cellSize + pointOffset);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        if(!isGameRunning) {//if the game isn't running yet
            if(!isLost && !isWon) {//and if the game hasn't been lost or won yet
                drawTitleScreen(g2);//then draw the title screen
            }
            if(isWon){//but if the player won then...
                //draw the winning screen
                //set the bg color
                g2.setColor(new Color (255,255,255));//to white
                g2.fillRect(0,0,this.getWidth(),this.getHeight());
                //draw the text
                Font oldFont = g2.getFont();
                Font textFont = oldFont.deriveFont(68f);
                g2.setFont(textFont);
                String text = "You won the game!";
                int x = this.getWidth() / 2 - g2.getFontMetrics().stringWidth(text) / 2;
                int y = (getHeight()  / 2);
                g2.setColor(new Color(255,208,237));
                g2.drawString(text,x,y);//drawing the winning text
                //print instructions text
                //setting color
                g2.setColor(new Color(0,0,0));
                //setting font and font size
                Font instructionsText = g2.getFont();
                Font instructionsFont = instructionsText.deriveFont(30f);
                g2.setFont(instructionsFont);
                g2.drawString("press enter to restart the game", 95 , y+40);
                //now I want to print out a cat photo
                ImageIcon kittyIcon = new ImageIcon(kittyPic);
                Image kittyImage = kittyIcon.getImage();
                Image modifiedKittyImage = kittyImage.getScaledInstance((kittyIcon.getIconWidth())/4 , (kittyIcon.getIconHeight())/4 , Image.SCALE_SMOOTH);//resizing the png to make it large
                kittyIcon = new ImageIcon(modifiedKittyImage);
                x= this.getWidth()/2 - kittyIcon.getIconWidth()/2;
                kittyIcon.paintIcon(this, g2, x, y -200);//printing the cat above the Catman title
            }
            if(isLost) {//but if the player lost then...
                //draw the dying screen
                //set the bg color
                g2.setColor(new Color (255,255,255));//to black
                g2.fillRect(0,0,this.getWidth(),this.getHeight());
                //draw the text
                Font oldFont = g2.getFont();
                Font textFont = oldFont.deriveFont(72f);
                g2.setFont(textFont);
                String text = "you lost the game";
                int x = this.getWidth() / 2 - g2.getFontMetrics().stringWidth(text) / 2;
                int y = (getHeight()  / 2);
                g2.setColor(new Color	(0,0,0));
                g2.drawString(text,x,y);//it will print out the losing text
                //print instructions text
                //set color
                g2.setColor(new Color(255,208,237));
                //setting font and font size
                Font instructionsText = g2.getFont();
                Font instructionsFont = instructionsText.deriveFont(30f);
                g2.setFont(instructionsFont);
                g2.drawString("press enter to restart the game", 95, y+45);//prints out instructions
                //now I want to print out a dog photo
                ImageIcon puppyIcon = new ImageIcon(puppyPic);
                Image puppyImage = puppyIcon.getImage();
                Image modifiedPuppyImage = puppyImage.getScaledInstance((puppyIcon.getIconWidth())/4 , (puppyIcon.getIconHeight())/4 , Image.SCALE_SMOOTH);//resizing the png to make it large
                puppyIcon = new ImageIcon(modifiedPuppyImage);
                x= this.getWidth()/2 - puppyIcon.getIconWidth()/2;
                puppyIcon.paintIcon(this, g2, x, y + 55);//printing the cat above the Catman title

            }
            return;
        }
        //now i need to print out the hearts
        g2.setColor(Color.WHITE);
        g2.fillRect(0, cellSize, 10*cellSize, cellSize+10);//this prints a white bg screen behind the hearts
        //when the player loses a life, this will cover 3 hearts
        // EDIT: when I changed to JPanel, now the hearts just don't print out when you lose a life because JPanel repaints it
        //Before I changed to JPanel, the white bg would print over the heart everytime you lost a life (to hide the heart).
        //now I need to add a text that you have 9 hearts because cats have 9 lives, but you lose 3 hearts everytime you lose a life.
        //I need to clarify this to my players. I added this little text because when my friend was trialling my game, he said to add a little text to explain that in case the player is confused.
        //set a pink bg behind the text to make sure it shows
        g2.setColor(new Color(255, 238, 238));
        g2.fillRect(2, 12, 20*cellSize, 13);
        //setting font size and color
        Font explanationText = g2.getFont();
        Font explanationFont = explanationText.deriveFont(11f);
        g2.setFont(explanationFont);
        g2.setColor(new Color(0,0,0));
        //draw the explanation text
        g2.drawString("You have 9 hearts because Cats have 9 lives, but you lose 3 hearts everytime you die.", 2, 23);
        for (int h = 0; h < lives; h++) {
            ImageIcon heartIcon = new ImageIcon(newHeartsPic);
            Image heartImage = heartIcon.getImage();
            Image modifiedHeartImage = heartImage.getScaledInstance((cellSize * 3), (cellSize * 3), Image.SCALE_SMOOTH);//resizing the hearts png
            heartIcon = new ImageIcon(modifiedHeartImage);
            heartIcon.paintIcon(this, g,  h*(3*cellSize), 10 );//printing the hearts png times the number of lives.
            //the x coordinates of the hearts will depend on the h (number of lives the cat has) multiplied by the cellSize
            //as the lives decrease, the h decreases, and the number of hearts printed will decrease.
        }
        switch (gameState) {
            case 1: //originally, I was going to make the gameState = 0 for the title screen, = 1, for when the game starts, =2 when the game ends, but I changed it along the way.
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
                                Image modifiedCatImage = catImage.getScaledInstance(cellSize + 10, cellSize + 10, Image.SCALE_SMOOTH);//resizing the png to fit the roads.
                                catIcon = new ImageIcon(modifiedCatImage);
                                catX = catPosX * cellSize + (cellSize - modifiedCatImage.getWidth(this)) / 2;//getting the width of the cat png
                                catY = catPosY * cellSize + (cellSize - modifiedCatImage.getHeight(this)) / 2 + yOffset;//getting the height of the cat png
                                catIcon.paintIcon(this, g, catX, catY - 1);//printing the cat in the center of cell 5.
                                break;
                            case 6://sets the 6 into a power up pellet but if I have time, I'll change it into another food png.
                                g2.setColor(new Color(255, 238, 238));
                                g2.fillRect(i * cellSize, j * cellSize + yOffset, cellSize, cellSize);//creates the background pink
                                g2.setColor(new Color(255, 32, 25));
                                g2.fillOval(i * cellSize + (cellSize - fishDiameter) / 2, j * cellSize + (cellSize - fishDiameter) / 2 + yOffset, fishDiameter, fishDiameter);//creates a big red dot as another power up pellet

                                break;
                        }
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameState);
        }
        if(catScore >= 52000){//if the player reaches 52000 points
            isWon = true;//the player wins the game
            isGameRunning = false;//the game will stop running.
        }
        if(paintDogs) {//where I paint the doggies
            for (int k=0; k<dogs.length; k++){
                dogs[k].paint(g2);//paints the dogs
                if(dogs[k].getDogPosX() == catPosY  && dogs[k].getDogPosY() == catPosX) {//If the cat and a dog are in the same position it will do the following
                    //If the locations of the dogs are the same as the cat it means the cat got caught!
                    System.out.println("CAT CAUGHT   " + dogs[k].getDogPosX() + "  " + dogs[k].getDogPosY());
                    //dog X and dog Y accidentally switched while coding.
                    maze[catPosY][catPosX] = 1;//the cats position will be a 3 (an empty road)
                    catPosX = catStartX;//then the cat will respawn back to its original position, the x coordinate will be where it started
                    catPosY = catStartY;//the cat's y position will be where it started
                    left();//the cat will automatically walk to the left
                    lives--;//the lives of the cat will decrease by 1
                    if(lives <=0) {//but if the cats lives are less or equal to 0
                        isLost = true;//the player lost the game and the losing screen will appear
                        isGameRunning = false; //the game will stop running
                        paintDogs = false;//it will stop printing the dogs
                    }


                }
            }
        }

    }
    public void up() {// this is an up function. It's called when the player presses up
        if (maze[catPosY - 1][catPosX] == 1) {//checks if the road/cell above is equal to one (a road with a dot).
            catPic = upCat;//the cat will face upwards
            catScore += 200;//the cats score will increase by 200 each time it eats a dot
            catPosY--;//the y position of the cat will decrease by one making the cat move upwards
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY + 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        } else if (maze[catPosY - 1][catPosX] == 2) {// and if the block/cell above the cat is equal to 2 (a road with a power up pellet)
            catPic = upCat;//cat will face up
            catScore += 400;//the cats score will increase by 400 each time it eats a power up pellet
            catPosY--;//the y position of the cat will decrease by one making the cat move upwards
            PowerUpCat();//the power up function will be called (to make it faster only for 4 seconds).
            maze[catPosY][catPosX] = 5;//the cat will bne printed in the box it wants to go to
            maze[catPosY + 1][catPosX] = 3;//the previous block will be an empty road
        } else if (maze[catPosY - 1][catPosX] == 3) {//and if the block above is an empty road
            catPic = upCat;//make cat face up
            catPosY--;//the y position of the cat will decrease by one making the cat move upwards
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY + 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        } else if (maze[catPosY - 1][catPosX] == 6) {//if the block below
            catPic = upCat;//make cat face up
            catPosY--;//the y position of the cat will decrease by one making the cat move upwards
            catScore += 2000;//the cats score will increase by 2000 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY + 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the big red dot
        }
    }
    public void down() {
        if(maze[catPosY + 1][catPosX] == 1) {//checks if the road/cell below is equal to one (a road with a dot).
            catPic = downCat;//the cat will face downwards
            catScore += 200;//the cats score will increase by 200 each time it eats a dot
            catPosY++;//the y position of the cat will increase by one making the cat move downwards
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY - 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        } else if (maze[catPosY + 1][catPosX] == 2) {// and if the block/cell below the cat is equal to 2 (a road with a power up pellet)
            catPic = downCat;//the cat will face downwards
            catPosY++;//the y position of the cat will increase by one making the cat move downwards
            PowerUpCat();//the power up function will be called (to make it faster only for 4 seconds).
            catScore += 400;//the cats score will increase by 400 each time it eats a power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY - 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        } else if (maze[catPosY + 1][catPosX] == 3) {//and if the block above is an empty road
            catPic = downCat;//the cat will face downwards
            catPosY++;//the y position of the cat will increase by one making the cat move downwards
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY - 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        } else if (maze[catPosY + 1][catPosX] == 6) {
            catPic = downCat;//the cat will face downwards
            catPosY++;//the y position of the cat will increase by one making the cat move downwards
            catScore += 2000;//the cats score will increase by 2000 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY - 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        }
    }
    public void left() {
        if(catPosX-1 < 0) {//this is for the middle lane of the maze where the cat can teleport. this is if the cat is on the left side of the middle lane of the maze
            maze[catPosY][mazeColumns-1] = 5;//the cat will be printed on the opposite end of the maze but in the same middle lane
            maze[catPosY][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
            catPosX = mazeColumns-1;//make the cats position on the right side of the maze
            catPic = leftCat;//the cat will face left
        }
        else if (maze[catPosY][catPosX - 1] == 1) {
            catPic = leftCat;//the cat will face left
            catPosX--;//the x position of the cat will decrease by one making the cat move left
            catScore += 200;//the cats score will increase by 200 each time it eats a dot
            //System.out.println(catScore);
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX + 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        } else if (maze[catPosY][catPosX - 1] == 2) {
            catPic = leftCat;//the cat will face left
            catScore += 400;//the cats score will increase by 400 each time it eats a power up pellet
            PowerUpCat();//the power up function will be called (to make it faster only for 4 seconds).
            catPosX--;//the x position of the cat will decrease by one making the cat move left
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX + 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        } else if (maze[catPosY][catPosX - 1] == 3) {
            catPic = leftCat;//the cat will face left
            catPosX--;//the x position of the cat will decrease by one making the cat move left
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX + 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        } else if (maze[catPosY][catPosX - 1] == 6) {
            catPic = leftCat;//the cat will face left
            catPosX--;//the x position of the cat will decrease by one making the cat move left
            catScore += 2000;//the cats score will increase by 2000 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX + 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        }
    }

    public void right() {
        if(catPosX+1 > mazeColumns-1) {//this is for the middle lane of the maze where the cat can teleport.
            maze[catPosY][1] = 5;//the cat will be printed on the opposite end of the maze but in the same middle lane
            maze[catPosY][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
            catPosX = 1;//the cats position will be on the left side of the maze (x = 1)
            catPic = rightCat;//the cat faces right
        } else if (maze[catPosY][catPosX + 1] == 1) {
            catPic = rightCat;//the cat will face right
            catScore += 200;//the cats score will increase by 200 each time it eats a dot
            catPosX++;//the x position of the cat will increase by one making the cat move right
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX - 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        } else if (maze[catPosY][catPosX + 1] == 2) {
            catPic = rightCat;//the cat will face right
            catPosX++;//the x position of the cat will increase by one making the cat move right
            PowerUpCat();//the power up function will be called (to make it faster only for 4 seconds).
            catScore += 400;//the cats score will increase by 400 each time it eats a power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX - 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        } else if (maze[catPosY][catPosX + 1] == 3) {
            catPic = rightCat;//the cat will face right
            catPosX++;//the x position of the cat will increase by one making the cat move right
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX - 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        } else if (maze[catPosY][catPosX + 1] == 6) {
            catPic = rightCat;//the cat will face right
            catPosX++;//the x position of the cat will increase by one making the cat move right
            catScore += 2400;//the cats score will increase by 2000 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX - 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER && !isLost) {//if the player pressed enter and the player hasn't lost (this is for the title screen)
            isGameRunning = true;//the game starts to run
        }
        if(key == KeyEvent.VK_ENTER && isWon){//if the player presses enter and the player won, the game will restart
            restartGame();//calls restart game function
        }
        if (key == KeyEvent.VK_ENTER && isLost){//if the player presses enter and the player lost, the game will restart
            restartGame();//calls the restart game function
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {//if the player presses the up arrow or the 'w' key
            if (!(maze[catPosY - 1][catPosX] == 0) && !(maze[catPosY - 1][catPosX] == 4)) {//and if the block/cell above is not a wall and it's not a 4 (out of bounds for cats)
                direction = KeyEvent.VK_UP;//the cats direction will go up
            }
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {//if the player presses the down arrow or the 's' key
            if (!(maze[catPosY + 1][catPosX] == 0) && !(maze[catPosY + 1][catPosX] == 4)) {//and if the block/cell below is not a wall and it's not a 4 (out of bounds for cats)
                direction = KeyEvent.VK_DOWN;//cat will go down
            }
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {//if the player presses the left arrow or the 'a' key
            if (!(maze[catPosY][catPosX - 1] == 0) && !(maze[catPosY][catPosX - 1] == 4)) {//and if the block/cell next to it is not a wall and it's not a 4 (out of bounds for cats)
                direction = KeyEvent.VK_LEFT;//cat will go left
            }
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {//if the player presses the right arrow or the 'd' key
            if (!(maze[catPosY][catPosX + 1] == 0) && !(maze[catPosY][catPosX + 1] == 4)) {//and if the block/cell next to it is not a wall and it's not a 4 (out of bounds for cats)
                direction = KeyEvent.VK_RIGHT;//cat will go right
            }
        }
    }


    private void PowerUpCat() {//this function is called when the cat eats a power pellet that allows it to move faster.
        removePowerUpTimer.stop(); //I added the removePowerUpTimer function here because of what I have explained earlier.
        // If there is a timer already running, stop  it.
        catTimer.setDelay(1000/catPowerVelocity); //now make the cat faster
        removePowerUpTimer.start(); //then start the timer again, after 4 seconds it will make the cat go back to its normal speed
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    //creating the restart game function
    void restartGame() {
        lives = 3;//sets the lives back to 3
        //sets the maze to its original state
        maze = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
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
                {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 4, 4, 4, 4, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 4, 4, 4, 4, 4, 4, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {4, 4, 4, 4, 4, 0, 1, 0, 0, 3, 0, 0, 4, 4, 4, 4, 0, 0, 3, 0, 0, 1, 0, 4, 4, 4, 4, 4},
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
        //setting everything back to default, this will make the title screen show
        isGameRunning = false;
        isLost = false;
        isWon=false;
        paintDogs = true;
        //initialising the dogs again
        dogs = new Dogs[] {new Dogs(5, 6, 2)
                , new Dogs(20, 6, 1)
                , new Dogs(20, 21, 2)
                , new Dogs(5, 21, 1)
                , new Dogs(29, 15, 3)};
        //set the cats coordinates back to its respawn point
        catPosX = catStartX;
        catPosY = catStartY;
        //set the cat score back to 0
        catScore = 0;
    }


}
 
 