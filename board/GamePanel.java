package board;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener, ActionListener {
    public final int GAME_PANEL_WIDTH = 500;
    public final int GAME_PANEL_HEIGHT = 700;
    public final int TILE_SIZE = 10;
    public final int GAME_UNITS = (GAME_PANEL_HEIGHT*GAME_PANEL_WIDTH)/(TILE_SIZE*TILE_SIZE);
    private final int xCoordinate[] = new int[GAME_UNITS];
    private final int yCoordinate[] = new int[GAME_UNITS];
    private int bodyOfTheSnake = 2, score = 0, appleXCoordinates, appleYCoordinates;
    private boolean gameIsRunning = false;

    JButton upButton = new JButton("UP");
    JButton downButton = new JButton("DOWN");
    JButton rightButton = new JButton("RIGHT");
    JButton leftButton = new JButton("LEFT");
    JButton pauseButton = new JButton("PAUSE");
    JButton restartButton = new JButton("RESTART");
    JButton startButton = new JButton("START");

    Random rand = new Random();

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
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

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

    private void creatingNewApple(Graphics g){
        appleXCoordinates = rand.nextInt(GAME_PANEL_WIDTH);
        appleYCoordinates = rand.nextInt(GAME_PANEL_HEIGHT);

        g.setColor(new Color(210, 20, 10));
        g.fillOval(appleXCoordinates, appleYCoordinates, TILE_SIZE, TILE_SIZE);
    }

    private void draw(Graphics g){
        creatingNewApple(g);
    }

    private void renderGameOver(Graphics g){
        g.setColor(new Color(121, 17, 17));
        g.setFont(new Font("Ink Free", Font.ITALIC, 75));
        g.drawString("Game Over", GAME_PANEL_WIDTH/2 - 183, GAME_PANEL_HEIGHT/2);
    }

    private void renderYouAreWinner(Graphics g){
        g.setColor(new Color(127, 222, 36));
        g.setFont(new Font("Ink Free", Font.ITALIC, 75));
        g.drawString("You Are The Winner", GAME_PANEL_WIDTH/2 - 183, GAME_PANEL_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == upButton){
            System.out.println("Up button was pressed");
        }
        if(e.getSource() == rightButton){

        }
        if(e.getSource() == downButton){
            System.out.println("Down");
        }
        if(e.getSource() == leftButton){

        }
    }
}
