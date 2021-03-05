package board;

import javax.swing.*;

public class GameBoard extends JFrame {
    GamePanel gamePanel;

    /**
     * Constructor for the class GameBoard, that creates the application, sets it's name and makes it resizable
     */
    public GameBoard(){
        this.add(gamePanel = new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
