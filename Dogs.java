/**
 *  *
 *  This is the dogs class. This is where I will sort out the dogs directions
 *  creating a separate dogs class will make things less cluttered.
 *  * @author gabriella bella rose Bitju
 *  * @version 02/08
 *  */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dogs {
    String upDog = "img/doggyUp.png";//png of dog facing up
    String downDog = "img/doggyDown.png";//png of dog facing down
    String rightDog = "img/doggyRight.png";//png of dog facing right
    String leftDog = "img/doggyLeft.png";//png of dog facing left
    private int dogPosX;//
    private int dogPosY;
    int velocity;
    private int direction;
    String dogPic = leftDog;//setting the initial dog position to left
    private Image dogImage;
    private Timer moveTimer;

    public Dogs(int startX, int startY, int velocity) {
        this.dogPosY = startY;//getting the y coordinates the player chooses to be the starting y position of the dog
        this.dogPosX = startX;//getting the x coordinates the player chooses to be the starting x position of the dog
        this.velocity = velocity;//
        dogImage = new ImageIcon(dogPic).getImage();//this creates the dog pic into an image that the game can use
        //this is a timer that makes the dogs move.
        moveTimer = new Timer((int) (1000 / velocity), new ActionListener() {//so every 1000 milliseconds (one second) divided by the dogs' velocity.
            @Override
            public void actionPerformed(ActionEvent e) {
                move();//it will call the move function and move.
            }
        });
        moveTimer.start();//tells the moveTimer to start and the dogs will start to print.
    }
    //this function gets the dogs x position.
    public int getDogPosX() {
        return dogPosX;
    }
    //this function gets the dogs Y position.
    public int getDogPosY() {
        return dogPosY;
    }
//this function makes the dogs move.
    public void move() {
        switch (direction) {
            //the X and Y's are mixed around!!! might fix soon...
            //so many X and Y's that are mixed up idk if I can fix them all by tomorrow
            case 0: // MOVE LEFT
                if (canMove(dogPosX - 1, dogPosY)) {//first it checks if the dog can move to that block first.
                    //eg, this checks if the block/cell to it's left
                    dogPosX--;
                    dogImage = new ImageIcon(leftDog).getImage();
                } /*else if (dogPosX == */
                else changeDirection();
                break;
            case 1: // MOVE RIGHT
                if (canMove(dogPosX + 1, dogPosY)) {
                    dogPosX++;
                    dogImage = new ImageIcon(rightDog).getImage();
                } else changeDirection();
                break;
            case 2: // MOVE UP
                if (canMove(dogPosX, dogPosY - 1)) {
                    dogPosY--;
                    dogImage = new ImageIcon(upDog).getImage();
                } else changeDirection();
                break;
            case 3: // MOVE DOWN
                if (canMove(dogPosX, dogPosY + 1)) {
                    dogPosY++;
                    dogImage = new ImageIcon(downDog).getImage();
                } else changeDirection();
                break;
        }
    }
//this is a function that checks if the dog can go to that column or not.
    //x and y's are switched around a lot during the beginning then I fixed it. I'm pretty sure some of the x and y's are still switched
    //but I don't really want to change it around because it works well haha
    //update: I have changed the X and Y's around, hopefully everything should be fine now
    private boolean canMove(int x, int y) {
        int[][] maze = TheGame.maze;
        int mazeRows = maze.length;
        int mazeColumns = maze[0].length;
//mazeRows and mazeColumns were switched and it caused a big error in making the dogs not walk out of bounds
        if (x >= 0 && x <= mazeColumns  && y >= 0 && y <= mazeRows ) {
            return maze[y][x] == 1 || maze[y][x] == 2 || maze[y][x] == 3 || maze[y][x] == 4|| maze[y][x] == 5;
        } else {
            System.out.println("Invalid move");
            return false;
        }
    }
    //this function makes the dogs go a different direction.
    private void changeDirection() {
        int[] possibleDirections = {0, 1, 2, 3};//the possible directions are left, right, up, and down.
        this.direction = possibleDirections[(int) (Math.random() * possibleDirections.length)];//then the directions get set to a random number (direction).
    }
    public void paint(Graphics g) {
        int cellSize = 23;
        int yOffset = 60;
        g.drawImage(dogImage, dogPosX * cellSize, dogPosY * cellSize + yOffset, cellSize, cellSize, null);
    }
}

