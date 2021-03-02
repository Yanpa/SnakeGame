package board;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public final int GAME_PANEL_WIDTH = 500;
    public final int GAME_PANEL_HEIGHT = 730;

    public GamePanel(){
        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

    public void renderGameOver(Graphics g){
        g.setColor(new Color(121, 17, 17));
        g.setFont(new Font("Ink Free", Font.ITALIC, 75));
        g.drawString("Game Over", GAME_PANEL_WIDTH/2 - 100, GAME_PANEL_HEIGHT/2);
    }
}
