/**
 * Write a description of class TheGame here.
 * who, what, why, when
 * This is the code for the game 'Catman' I'm creating for my computer science assignment. Catman is inspired by the game Pacman.
 * In this game, a cat is being chased by dogs. When the cat gets caught by a dog, the cat loses 3 lives. The cat has 9 lives in total.
 * There will be fish the cat can eat.
 * There will need to be 5 main components that make up this game which is:
 * 1) a printing component. (prints everything) This prints the maze, the cat, the dogs, the health bar, the score, the intro screen, the losing screen, the winning screen, etc.
 * 2) a component that moves the characters
 * 3) a scorekeeping component. This will show the score of the user
 * 4) a healthbar. This will show how many lives the kitten has left. The cat will have nine lives represented as hearts (because cats have 9 lives).
 * When the cat runs into a dog, the cat will lose 3 lives.
 * when the player wins or loses and wants to restart the game, they need to press enter then they can restart the game
 * here is a link to the photos I used that I got from KindPNG.com
 * for the cat pic on the winning screen:
 *  https://www.kindpng.com/imgv/iRbiToT_persian-cat-minuet-cat-maine-coon-munchkin-cat/
 * for the puppy pic on the losing screen:
 *  https://www.kindpng.com/imgv/TixTTh_dogs-dogpng-animals-doggo-carefree-golden-retriever-transparent/
 * for the dog enemy pics:
 *  https://www.kindpng.com/imgv/hTRwmhJ_cat-and-dog-sprites-pixel-dog-sprite-sheet/
 * for the cat character and the heart picture for the health bar I made myself on pixelArt
 * The game starts with a title screen which displays the cat character and a text of the title “Catman” then a text will say “press enter to start”.
 * Once the player presses enter, everything will be printed out nicely and the game should start.
 * The cat will need to move around the whole maze to collect the dots/pellets.
 * A small dot is 200 points,a bigger dot is 400 points, and the biggest dot is worth 2400 points. These points add up to about 52000 points.
 * In total the cat will have 9 hearts because “cats have nine lives”. However, when the cat bumps into a dog, it will lose three hearts.
 * This is because if the cat has 9 lives then the game will play on too long.
 * If the player reaches 52000 points before it loses its lives, then the player wins.
 * When the player wins, there will be a winning screen that appears. It will say “you won the game! Press enter to restart the game”.
 * If the player loses, there will be a losing screen that appears. It will say “you lost the game” “Press enter to restart the game”.
 * When the player presses restart, it will show the title screen again and so it repeats itself.
 *
 * My relevant implications are : intellectual property, aesthetics, usability, and functionality.
 *
 * @author gabriella bella rose bitju
 * @version 02/08
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

    int mazeRows = 31;//this sets the maze rows to 31.
    int mazeColumns = 28;//this sets the maze columns to 28
    int cellSize = 23;// this sets the size of one cell to 23 pixels.
    //honestly it's recommended that I add this large array into a jar file (?) but I haven't had the time to figure out how to do that
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
    // this makes an array which is values of the maze. It holds a value of being a wall or a road that the cat and dogs can walk through.
    //the road has little dots/pellets the cat can eat. When the cat eats the pellet the road become empty.
    //0 = a river. It acts as a wall the cat can't walk through.
    //1 = a road with a dot. Those are 200 points each
    //2 = a road with a fish, which acts as a power up pellet for the kitty. It makes the cat go faster and is also worth more points.
    //3 = a road without a dot (an empty road)
    //4 = a road that the cat doesn't go in, and only the dogs can go walk through
    final static int yOffset = 60;//added this because if I don't, there's no distance between the window bar and the top part of the frame. (the maze will be cut off)
    //also added a space so that I have room to print out the hearts, catScore, and little text.
    int gameState = 1; // this makes the game state = 1 which means the game plays
    //originally, I was going to make the gameState = 0 for the title screen, = 1, for when the game starts, =2 when the game ends, but I changed the game logics along the way.
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
    Dogs[] dogs = {new Dogs(6, 5, 2)
            , new Dogs(6, 20, 1)
            , new Dogs(21, 20, 2)
            , new Dogs(21, 5, 2)
            , new Dogs(15, 29, 3)
            , new Dogs( 15, 11, 3)};//I initialized the dogs here. I have made 6 dogs. I've set their spawn points and velocities.
    //this is an array of dogs to keep a reference for the dogs classes we initiated, so every re-print of the game we keep track of their positions and repaint them
    //this is from my dogs constructor in my dogs class
    private Timer timer; //It is responsible for re-rendering the game and a consistent and stable intervals
    private Timer catTimer;//catTimer is a timer variable to control the movement calculations of the cat. It's responsible for changing its position every 1000/velocity millisecond
    //both cat timer and timer are called when the game starts (aka the "Catman" class constructor is initiated)
    private Timer removePowerUpTimer;//its job is to revert the cat's velocity into its original velocity after 4 seconds of it being called
    //It is called in "PowerUp" function after setting the cat velocity into the powerUp velocity

    public TheGame() {
        this.addKeyListener(this);//adds key listener which basically does the code if you press a specific key on the keyboard. it listens to keyboard input
        setFocusable(true);//this needs to be set to true so that it will respond to the keyListener. Without focus, the game won't listen to keyboard input
        requestFocusInWindow(true);//make the window be able to focus
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
        //creating the timer that re-renders
        timer = new Timer(15, new ActionListener() {
            //this timer will print everything every 15 milliseconds, currently it re-renders the game very 15 millisecond
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint(); //Call repaint every 15 millisecond (Will print out everything and update what the screen displays)
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
    //the function which draws the title screen. It's called below.
    private void drawTitleScreen(Graphics2D g2) {
        //setting the bg color
        g2.setColor(new Color (255,255,255));
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
        // draw the title text
        //setting the font
        Font oldFont = g2.getFont();//gets the existing minecraft font which is created before this function gets called below
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
        ImageIcon catIcon = new ImageIcon("img/ragdollLeft.png");
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
        try {
            //we need to print out the cat score
            //Although this part is for printing out the cat score, I put this code here first because the title screen gets its font from the cat score font
            //this is where I create and set the font to the minecraft font so the title screen can get its font from here
            //setting the font to a pixel-like font to suit the game's aesthetics/ pixel vibe
            Font minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("Minecraft copy.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraft);
            g2.setFont(minecraft);
            //drawing a white screen behind the catScore everytime it prints the cat's score because without it, the catScore will overlap and you can't read it
            g2.setColor(Color.WHITE);
            g2.fillRect(25*cellSize, cellSize + pointOffset, 4*cellSize, cellSize);
            //printing the cat score in pink
            g2.setColor(Color.PINK);
            //drawing the catScore
            g2.drawString(Integer.toString(catScore), 25 * cellSize, 2 * cellSize + pointOffset);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        //we need to print out the title screen
        if(!isGameRunning) {//if the game isn't running yet
            if(!isLost && !isWon) {//and if the game hasn't been lost or won yet
                //(these if statements makes the title screen print first)
                drawTitleScreen(g2);//calls this function to draw the title screen.
            }
            if(isWon){//but if the player won then...
                //draw the winning screen
                //set the bg color
                g2.setColor(new Color (255,255,255));//to white
                g2.fillRect(0,0,this.getWidth(),this.getHeight());
                //draw the text
                //getting the old font
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
        //this prints a white bg screen behind the hearts first
        g2.setColor(Color.WHITE);
        g2.fillRect(0, cellSize, 10*cellSize, cellSize+10);
        //when the player loses a life, this will cover 3 hearts
        // EDIT: when I changed to JPanel, now the hearts just don't print out when you lose a life because JPanel repaints it
        //Before I changed to JPanel (when I still had JFrame), the white bg would print over the heart everytime you lost a life (to hide the heart).
        for (int h = 0; h < lives; h++) {//the amount of hearts drawn depends on how many lives the player has
            //setting the png into an icon then to an image so it can be resized then turned back to an icon so it can be painted (printed)
            ImageIcon heartIcon = new ImageIcon(newHeartsPic);
            Image heartImage = heartIcon.getImage();
            Image modifiedHeartImage = heartImage.getScaledInstance((cellSize * 3), (cellSize * 3), Image.SCALE_SMOOTH);//resizing the hearts png
            heartIcon = new ImageIcon(modifiedHeartImage);
            heartIcon.paintIcon(this, g,  h*(3*cellSize), 10 );//printing the hearts png times the number of lives.
            //the x coordinates of the hearts will depend on the h (number of lives the cat has) multiplied by the cellSize
            //as the lives decrease, the h decreases, and the number of hearts printed will decrease.
        }
        //now I need to add a text that you have 9 hearts (because cats have 9 lives), but you lose 3 hearts everytime you lose a life.
        //I need to clarify this to my players. I added this little text because when my friend was trialling my game, he said to add a little text to explain that in case the player is confused.
        //set a pink bg behind the text to make sure it shows
        g2.setColor(new Color(255, 238, 238));
        g2.fillRect(2, 12, 20*cellSize, 13);
        //setting font, font size, and color
        Font explanationText = g2.getFont();
        Font explanationFont = explanationText.deriveFont(11f);
        g2.setFont(explanationFont);
        g2.setColor(new Color(0,0,0));
        //draw the explanation text
        g2.drawString("You have 9 hearts because Cats have 9 lives, but you lose 3 hearts everytime you die.", 2, 23);
        //next i need to draw the cells/maze
        //this visualizes the array
        switch (gameState) {
            case 1: //originally, I was going to make the gameState = 0 for the title screen, = 1, for when the game starts, =2 when the game ends, but I changed the game logics along the way.
                //now I need to print the array/maze
                for (int i = 0; i < mazeColumns; i++) {
                    for (int j = 0; j < mazeRows; j++) {
                        switch (maze[j][i]) {
                            case 0://sets all the 0's to a river that acts as a wall for the cats and dogs not to go through
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
        //where I paint the doggies
        if(paintDogs) {
            for (int k=0; k<dogs.length; k++){
                dogs[k].paint(g2);//paints the dogs
                if(dogs[k].getDogPosY() == catPosY  && dogs[k].getDogPosX() == catPosX) {//If the cat and a dog are in the same position it will do the following
                    //If the locations of the dogs are the same as the cat it means the cat got caught!
                    //System.out.println("CAT CAUGHT   " + dogs[k].getDogPosX() + "  " + dogs[k].getDogPosY()); //for testing purposes
                    //dog X and dog Y accidentally switched while coding. (update: now fixed)
                    maze[catPosY][catPosX] = 1;//the cats position where the cat died will be a 1 (a road with a dot)
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
    private void PowerUpCat() {//this function is called when the cat eats a power pellet that allows it to move faster.
        // If there is a timer already running, stop  it.
        removePowerUpTimer.stop(); //I added the removePowerUpTimer function here because of what I have explained earlier/above.
        catTimer.setDelay(1000/catPowerVelocity); //now make the cat faster
        removePowerUpTimer.start(); //then start the timer again, after 4 seconds it will make the cat go back to its normal speed
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
            catScore += 2400;//the cats score will increase by 2400 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY + 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the big red dot
        }
    }
    public void down() {//moving down function
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
            catScore += 2400;//the cats score will increase by 2400 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY - 1][catPosX] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road). This creates the illusion that the cat ate the dot
        }
    }
    public void left() {//moving left function
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
            catScore += 2400;//the cats score will increase by 2400 each time it eats a big power up pellet
            maze[catPosY][catPosX] = 5;//the cell/block it's moving to will be a five (5 = where the cat is printed). so the cat will be printed in the place it's going to
            maze[catPosY][catPosX + 1] = 3;//the previous block (the one it was on) will turn to a 3 (3 = an empty road).
        }
    }

    public void right() {//moving right function
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
            catScore += 2400;//the cats score will increase by 2400 each time it eats a big power up pellet
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
        //initialising the dogs again so they can restart at the same place
        dogs =new Dogs[] {new Dogs(6, 5, 2) ,
                new Dogs(6, 20, 1) ,
                new Dogs(21, 20, 2) ,
                new Dogs(21, 5, 2) ,
                new Dogs(15, 29, 3) ,
                new Dogs( 15, 11, 3)};
        //set the cats coordinates back to its respawn point
        catPosX = catStartX;
        catPosY = catStartY;
        //set the cat score back to 0
        catScore = 0;
    }


}
 
 