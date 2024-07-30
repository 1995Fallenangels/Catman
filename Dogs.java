/**
 *  *
 *  This is the dogs class. This is where I will sort out the dogs directions
 *  creating a separate dogs class will make things less cluttered.
 *  * @author gabriella bella rose Bitju
 *  * @version 30/07
 *  */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dogs {
    String upDog = "doggyUp.png";//png of dog facing up
    String downDog = "doggyDown.png";//png of dog facing down
    String rightDog = "doggyRight.png";//png of dog facing right
    String leftDog = "doggyLeft.png";//png of dog facing left
    private int dogX;//initialising the dogs x coordinates
    private int dogY;//initialising the dogs x coordinates
    private int dogPosX;//
    private int dogPosY;
    private int direction;
    String dogPic = leftDog;//setting the initial dog position to left
    private TheGame game;
    private Image dogImage;
    private Timer moveTimer;

    public Dogs(int startX, int startY, int velocity, TheGame theGame) {
        //this.dogY = startY;
        //this.dogX = startX;
        this.dogPosY = startY;
        this.dogPosX = startX;
        this.direction = velocity;
        this.game = theGame;
        dogImage = new ImageIcon(dogPic).getImage();
        moveTimer = new Timer((int) (1000 / velocity), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
        moveTimer.start();
    }


    public int getDogPosX() {
        return dogPosX;
    }

    public int getDogPosY() {
        return dogPosY;
    }

    public void move() {
        switch (direction) {
            case 0: // MOVE LEFT
                if (canMove(dogPosY - 1, dogPosX)) {
                    dogPosY--;
                    dogImage = new ImageIcon(leftDog).getImage();
                } /*else if (dogPosX == */
                else changeDirection();
                break;
            case 1: // MOVE RIGHT
                if (canMove(dogPosY + 1, dogPosX)) {
                    dogPosY++;
                    dogImage = new ImageIcon(rightDog).getImage();
                } else changeDirection();
                break;
            case 2: // MOVE UP
                if (canMove(dogPosY, dogPosX - 1)) {
                    dogPosX--;
                    dogImage = new ImageIcon(upDog).getImage();
                } else changeDirection();
                break;
            case 3: // MOVE DOWN
                if (canMove(dogPosY, dogPosX + 1)) {
                    dogPosX++;
                    dogImage = new ImageIcon(downDog).getImage();
                } else changeDirection();
                break;
        }
    }

    private boolean canMove(int y, int x) {
        int[][] maze = TheGame.maze;
        int mazeRows = maze.length;
        int mazeColumns = maze[0].length;

        if (x >= 0 && x <mazeRows   && y >= 0 && y <mazeColumns   ) {
            return maze[x][y] == 1 || maze[x][y] == 2 || maze[x][y] == 3 || maze[x][y] == 4|| maze[x][y] == 5;
        } else {
            System.out.println("Invalid move");
            return false;
        }
    }
    private void changeDirection() {
        int[] possibleDirections = {0, 1, 2, 3};
        this.direction = possibleDirections[(int) (Math.random() * possibleDirections.length)];
    }
    public void paint(Graphics g) {
        int cellSize = 23;
        int yOffset = 60;
        g.drawImage(dogImage, dogPosY * cellSize, dogPosX * cellSize + yOffset, cellSize, cellSize, null);
    }
}

