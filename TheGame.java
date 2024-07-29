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


    final static int yOffset = 60;//added this because if I don't, there's no distance between the window bar and the frame and the top part of the frame isn't seen.
    int gameState = 1; // this states the game state to 0 which is the title screen
    String leftCat = "catLeft.png"; //this is a png of the cat facing left
    String rightCat = "catRight.png"; //this is a png of the cat facing right
    String downCat = "catDown.png"; //this is a png of the cat facing down
    String upCat = "catUp.png"; //png of cat facing up
    String newHeartsPic = "newHearts.png";
    int diameter = 5;//diameter of the black dot in the middle
    int fishDiameter = 18;//diameter of the 'power up' pellets/fish
    int catPosX = 14;//the x coordinate of the cat
    int catPosY = 23;//the y coordinate of the cat
    int catStartX = catPosX;
    int catStartY = catPosY;
    String catPic = leftCat;//setting the initial cat position to left
    int pointOffset = 10;
    int catVelocity = 4; //Initialize the velocity variable of the cat private
    int catPowerVelocity = 8;
    int powerUpDuration = 4;//Power up duration in seconds
    private int direction = 0; //The current direction of the cat
    int catScore;
    int catX;
    int catY;
    int lives = 3;
    boolean isGameRunning = false;
    boolean isLost = false;
    boolean paintDogs = true;
    Dogs[] dogs = {new Dogs(5, 6, 2, this)
            , new Dogs(20, 6, 1, this)
            , new Dogs(20, 21, 1, this)
            , new Dogs(5, 21, 1, this)
            , new Dogs(29, 15, 3, this)};
    private Timer timer; // Timer field
    private Timer catTimer;
    private Timer powerUpTimer;

    public TheGame() {
        this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);

        catTimer = new Timer(1000/catVelocity, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isGameRunning) return;
                switch (direction) {
                    case KeyEvent.VK_RIGHT:
                        right();
                        break;
                    case KeyEvent.VK_LEFT:
                        left();
                        break;
                    case KeyEvent.VK_DOWN:
//                    if (maze[catPosY + 1][catPosX] == 0 || maze[catPosY + 1][catPosX] == 4) break;
                        down();
                        break;
                    case KeyEvent.VK_UP:
//                    if (maze[catPosY - 1][catPosX] == 0 || maze[catPosY - 1][catPosX] == 4) break;
                        up();
                        break;
                }
            }
        });
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                repaint(); // Call repaint every 1 second
            }
        });
        powerUpTimer = new Timer(1000 * powerUpDuration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catTimer.setDelay(1000/catVelocity);
                powerUpTimer.stop();
            }
        });
        timer.start(); // Start the timer
        catTimer.start();
    }


    private void drawTitleScreen(Graphics2D g2) {
        //setting the bg color
        g2.setColor(new Color (255,255,255));
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
        // draw the title text
        Font oldFont = g2.getFont();
        Font titleFont = oldFont.deriveFont(72f);
        g2.setFont(titleFont);
        String text = "Catman";
        int x = this.getWidth() / 2 - g2.getFontMetrics().stringWidth(text) / 2;
        int y = (getHeight()  / 2);
        g2.setColor(new Color   (255,208,237));
        g2.drawString(text,x,y);
        // set title text color and font
        g2.setColor(new Color(255,236,242));
        g2.drawString(text, x, y);
        g2.setFont(oldFont);
        ImageIcon heartIcon = new ImageIcon(newHeartsPic);
        Image heartImage = heartIcon.getImage();
        Image modifiedHeartImage = heartImage.getScaledInstance((cellSize * 3),(cellSize * 3), Image.SCALE_SMOOTH);//resizing the png to fit the roads.
        heartIcon = new ImageIcon(modifiedHeartImage);
        heartIcon.paintIcon(this, g2,  x+100, y+50 );
        ImageIcon catIcon = new ImageIcon(catPic);
        Image catImage = catIcon.getImage();
        Image modifiedCatImage = catImage.getScaledInstance(cellSize * 10, cellSize * 10, Image.SCALE_SMOOTH);//resizing the png to fit the roads.
        catIcon = new ImageIcon(modifiedCatImage);
        x= this.getWidth()/2 - catIcon.getIconWidth()/2;
        catIcon.paintIcon(this, g2, x, y - 245);//printing the cat in the center of cell 5.
        y +=  60;
        // Set instruction text color and font
        //x += 10;
        g2.drawString("Press Enter to Start", x, y);
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        try {
            Font minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("Minecraft copy.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraft);
            g2.setFont(minecraft);
            g2.setColor(Color.WHITE);
            g2.fillRect(25*cellSize, cellSize + pointOffset, 4*cellSize, cellSize);
            g2.setColor(Color.PINK);
            g2.drawString(Integer.toString(catScore), 25 * cellSize, 2 * cellSize + pointOffset);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        if(!isGameRunning) {
            if(!isLost) {
                drawTitleScreen(g2);
            }
            if(isLost) {

            }
            return;
        }

        g2.setColor(Color.WHITE);
        g2.fillRect(0, cellSize, 10*cellSize, cellSize+10);

        for (int h = 0; h < lives; h++) {

            ImageIcon heartIcon = new ImageIcon(newHeartsPic);
            Image heartImage = heartIcon.getImage();
            Image modifiedHeartImage = heartImage.getScaledInstance((cellSize * 3),
                    (cellSize * 3), Image.SCALE_SMOOTH);//resizing the png to fit the roads.
            heartIcon = new ImageIcon(modifiedHeartImage);

            heartIcon.paintIcon(this, g,  h*(3*cellSize), 10 );

        }




        switch (gameState) {
            case 1://if the game state is 1 then the game will play
                //printing the cat to move directions

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
                                System.out.println("PAINTT " + catPosX + " " +catPosY);

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
                    maze[catPosY][catPosX] = 1;
                    catPosX = catStartX;
                    catPosY = catStartY;
                    left();
                    lives--;
                    if(lives <=0) {
                        isLost = true;
                        isGameRunning = false; //Stop running the game, probably show a lose screen here.
                        paintDogs = false;
                    }


                }
            }
        }

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
            PowerUpCat();
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
            PowerUpCat();
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
            PowerUpCat();
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
            PowerUpCat();
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
        if(key == KeyEvent.VK_ENTER && !isLost) {
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
            }
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if (!(maze[catPosY][catPosX + 1] == 0) && !(maze[catPosY][catPosX + 1] == 4)) {
                direction = KeyEvent.VK_RIGHT;
            }
        }
    }


    private void PowerUpCat() {
        powerUpTimer.stop();

        catTimer.setDelay(1000/20);
        powerUpTimer.start();
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
 
 