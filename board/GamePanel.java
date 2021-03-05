package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    public final int GAME_PANEL_WIDTH = 500;
    public final int GAME_PANEL_HEIGHT = 700;
    public final int TILE_SIZE = 10;
    public final int GAME_UNITS = (GAME_PANEL_HEIGHT*GAME_PANEL_WIDTH)/(TILE_SIZE*TILE_SIZE);
    private final int[] xCoordinate = new int[GAME_UNITS];
    private final int[] yCoordinate = new int[GAME_UNITS];
    private int bodyOfTheSnake = 2, score = 0, appleXCoordinates, appleYCoordinates;
    private boolean gameIsRunning = false;
    char directionOfTheSnake = 'D';
    private final int DELAY = 190;
    int[] obstaclesCoordinates;

    JButton upButton = new JButton("UP");
    JButton downButton = new JButton("DOWN");
    JButton rightButton = new JButton("RIGHT");
    JButton leftButton = new JButton("LEFT");
    JButton pauseButton = new JButton("PAUSE");
    JButton restartButton = new JButton("RESTART");
    JButton startButton = new JButton("START");

    Random rand = new Random();
    Timer timer = new Timer(DELAY, this);

    /**
     * Constructor for the class GamePanel that creates the black background that the snake will move on and
     * puts all the buttons on the panel
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT + 85));
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setFocusable(true);

        settingButtons();
        this.add(upButton);
        this.add(downButton);
        this.add(rightButton);
        this.add(leftButton);
        this.add(restartButton);
        this.add(pauseButton);
        this.add(startButton);

        startOfTheGame();
    }

    /**
     * Implemented interface ActionListener was used to detect when the buttons were clicked. After they were clicked
     * every single one off them served it's purpose.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == upButton){
            if(directionOfTheSnake != 'D'){
                directionOfTheSnake = 'U';
            }
        }
        if(e.getSource() == rightButton){
            if(directionOfTheSnake != 'L'){
                directionOfTheSnake = 'R';
            }
        }
        if(e.getSource() == downButton){
            if(directionOfTheSnake != 'U'){
                directionOfTheSnake = 'D';
            }
        }
        if(e.getSource() == leftButton){
            if(directionOfTheSnake != 'R'){
                directionOfTheSnake = 'L';
            }
        }

        if(e.getSource() == restartButton){
            ((Window)getRootPane().getParent()).dispose();
            new GameBoard();
        }

        if(e.getSource() == startButton){
            timer.start();
        }

        if(e.getSource() == pauseButton){
            timer.stop();
        }

        movingAcrossTheScreen();
    }

    /**
     * Painting the whole panel
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Method that calls for the creation of obstacles and the apple and sets the game state as running
     */
    private void startOfTheGame(){
        creatingObstacles();
        newApple();
        gameIsRunning = true;
    }

    /**
     * This method adds actionListener to the buttons and places them on the panel the way they don't interrupt
     * players experience
     */
    private void settingButtons(){
        upButton.addActionListener(this);
        downButton.addActionListener(this);
        rightButton.addActionListener(this);
        leftButton.addActionListener(this);
        restartButton.addActionListener(this);
        pauseButton.addActionListener(this);
        startButton.addActionListener(this);

        upButton.setBounds(10, 700, 100, 30);
        downButton.setBounds(140, 700, 100, 30);
        rightButton.setBounds(255, 700, 100, 30);
        leftButton.setBounds(380, 700, 100, 30);
        restartButton.setBounds(30, 740, 130, 30);
        pauseButton.setBounds(185, 740, 130, 30);
        startButton.setBounds(340, 740, 130, 30);
    }

    /**
     * Sets the coordinates for the apple on a random place in the array
     */
    private void newApple(){
        appleXCoordinates = rand.nextInt((int)GAME_PANEL_WIDTH/TILE_SIZE)*TILE_SIZE;
        appleYCoordinates = rand.nextInt((int)GAME_PANEL_HEIGHT/TILE_SIZE)*TILE_SIZE;
    }

    /**
     * Draws the snake after it was created
     * @param g
     */
    private void creatingNewApple(Graphics g){
        g.setColor(new Color(210, 20, 10));
        g.fillOval(appleXCoordinates, appleYCoordinates, TILE_SIZE, TILE_SIZE);
    }

    /**
     * Checks if "head" of the snakes collides with the apple, if that happens newApple() is called, the snake increases
     * it's body and you get 10 points
     */
    private void checkIfAppleIsEaten(){
        if((xCoordinate[0] == appleXCoordinates) && (yCoordinate[0] == appleYCoordinates)) {
            bodyOfTheSnake++;
            score+=10;
            newApple();
        }
    }

    /**
     * Drawing the snake on the panel
     * @param g
     */
    private void creatingSnake(Graphics g){
        for(int i = 0; i< bodyOfTheSnake; i++){
            if(i == 0){
                g.setColor(Color.green);
                g.fillRect(xCoordinate[i], yCoordinate[i], TILE_SIZE, TILE_SIZE);
            }
            else{
                g.setColor(new Color(45, 180, 0));
                g.fillRect(xCoordinate[i], yCoordinate[i], TILE_SIZE, TILE_SIZE);
            }
        }
    }

    /**
     * Creating an array that stores the coordinates of up to 10 obstacles
     */
    private void creatingObstacles(){
        int randomNumberOfObstacles = rand.nextInt(10);
        int obstacleX, obstacleY;
        int position = 0;

        obstaclesCoordinates = new int[randomNumberOfObstacles*2];
        while (randomNumberOfObstacles > 0){
            obstacleX = rand.nextInt((int)GAME_PANEL_WIDTH/TILE_SIZE)*TILE_SIZE;
            obstacleY = rand.nextInt((int)GAME_PANEL_HEIGHT/TILE_SIZE)*TILE_SIZE;

            obstaclesCoordinates[position] = obstacleX;
            obstaclesCoordinates[position + 1] = obstacleY;

            position +=2;
            randomNumberOfObstacles--;
        }
    }

    /**
     * Drawing all the obstacles on the panel
     * @param g
     */
    private void renderingObstacles(Graphics g){
        for (int i = 0; i < obstaclesCoordinates.length - 1; i+=2){
            g.setColor(new Color(141, 131, 131));
            g.fillRect(obstaclesCoordinates[i], obstaclesCoordinates[i+1], TILE_SIZE, TILE_SIZE);
        }
    }

    /**
     * Moving the snake across the array with a for loop, and turning the head of the snake
     */
    private void movingTheSnake(){
        for(int i = bodyOfTheSnake;i>0;i--){
            xCoordinate[i] = xCoordinate[i-1];
            yCoordinate[i] = yCoordinate[i-1];
        }

        if(directionOfTheSnake == 'U'){
            yCoordinate[0] = yCoordinate[0] - TILE_SIZE;
        }
        if(directionOfTheSnake == 'D'){
            yCoordinate[0] = yCoordinate[0] + TILE_SIZE;
        }
        if(directionOfTheSnake == 'L'){
            xCoordinate[0] = xCoordinate[0] - TILE_SIZE;
        }
        if(directionOfTheSnake == 'R'){
            xCoordinate[0] = xCoordinate[0] + TILE_SIZE;
        }
    }

    /**
     * This method checks for collisions and if the snake haven't left the board yet
     */
    private void checkingForCollisions(){
        //Checks if the snake hits itself
        for(int i = bodyOfTheSnake; i>0; i--){
            if((xCoordinate[0] == xCoordinate[i]) && (yCoordinate[0] == yCoordinate[i])){
                gameIsRunning = false;
            }
        }

        //The next 4 check if the snake moves outside of the panel, and puts it back in
        if(xCoordinate[0] < 0) xCoordinate[0] = GAME_PANEL_WIDTH - 1;
        if(xCoordinate[0] > GAME_PANEL_WIDTH - 1) xCoordinate[0] = 0;
        if(yCoordinate[0] < 0) yCoordinate[0] = GAME_PANEL_HEIGHT - 1;
        if(yCoordinate[0] > GAME_PANEL_HEIGHT - 1) yCoordinate[0] = 0;

        //Checks if the snake hits one of the obstacles
        for(int i = 0; i < obstaclesCoordinates.length - 1; i+=2){
            if((xCoordinate[0] == obstaclesCoordinates[i]) && (yCoordinate[0] == obstaclesCoordinates[i + 1])){
                gameIsRunning = false;
                break;
            }
        }

        if(!gameIsRunning){
            timer.stop();
        }
    }

    /**
     * Drawing all the pieces together, checks if win condition is completed
     * @param g
     */
    private void draw(Graphics g){
        if(score == 300){
            gameIsRunning = false;
            renderYouAreWinner(g);
        }

        else if(gameIsRunning){
            renderingYourScore(g);
            renderingObstacles(g);
            creatingNewApple(g);
            creatingSnake(g);
        }
        else renderGameOver(g);

    }

    /**
     * Renders your score all the time
     * @param g
     */
    private void renderingYourScore(Graphics g){
        g.setColor(new Color(76, 15, 76));
        g.setFont(new Font("Ink Free", Font.ITALIC, 25));
        g.drawString("Score: " + score, GAME_PANEL_WIDTH/2 - 210, 30);
    }

    /**
     * If this method pops on your screen you did something wrong
     * @param g
     */
    private void renderGameOver(Graphics g){
        g.setColor(new Color(121, 17, 17));
        g.setFont(new Font("Ink Free", Font.ITALIC, 75));
        g.drawString("Game Over", GAME_PANEL_WIDTH/2 - 183, GAME_PANEL_HEIGHT/2);
        renderingYourScore(g);
    }

    /**
     * If this method pops on your screen you did something right
     * @param g
     */
    private void renderYouAreWinner(Graphics g){
        g.setColor(new Color(127, 222, 36));
        g.setFont(new Font("Ink Free", Font.ITALIC, 45));
        g.drawString("You Are The Winner", GAME_PANEL_WIDTH/2 - 183, GAME_PANEL_HEIGHT/2);
    }

    /**
     * The core of the whole game, method that puts all the pieces together and repaints every step of the snake
     */
    private void movingAcrossTheScreen(){
        if(gameIsRunning){
            movingTheSnake();
            checkIfAppleIsEaten();
            checkingForCollisions();
        }
        repaint();
    }
}
