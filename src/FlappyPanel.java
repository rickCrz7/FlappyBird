import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyPanel extends JPanel implements KeyListener, ActionListener {
    // set up the colors and dimensions of the game
    Color skyBlue = new Color(0, 165, 220);
    final int Width = 625;
    final int Height = 850;

    boolean gameOver = false;

    // set up the wall variables
    final int Wall_Velocity = 5;
    final int Wall_Width = 80;

    int wallX = Width + 50;
    int gap = (int)(Math.random() * Height); // random gap between 0 and Height

    // set up the bird variables
    int yAxis = Height/4;

    int velocity = 0;
    int acceleration = 8;
    int impulse = 1;

//    int [] wallX = new int [2];
//    int [][] gapX = new int [2][2];

    public FlappyPanel(){
        setSize(625,850); // set the size of the panel
        setFocusable(true);
        addKeyListener(this);

        setBackground(skyBlue); // set the background color of the panel
        new Timer(40, this).start(); // start the timer
    }
    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        if(!gameOver) {
            drawWall(g);
            drawBird(g);
        }else{
            gameOver();
        }
    }


    private void drawBird(Graphics g){

        if(wallX <= 100 && wallX + Wall_Width >= 100){
            if((yAxis + velocity) >= 0 && (yAxis + velocity) <= gap ||
                    (yAxis + velocity) >= gap + 100 && (yAxis + velocity) <= Height){
                gameOver = true;
            }
        }

        g.setColor(Color.YELLOW);
        g.fillOval(Width/4, yAxis + velocity, 25, 25);
    }
    private void drawWall(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(wallX, 0, Wall_Width, Height);
        g.setColor(skyBlue);
        g.fillRect(wallX, gap, Wall_Width, 100);
    }
    private void gameOver(){


    }


    public void actionPerformed(ActionEvent e){

        acceleration += impulse;
        velocity += acceleration;

        wallX -= Wall_Velocity;
        repaint();

    }


    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            acceleration = -8;
        }

    }
    public void keyReleased(KeyEvent e){

    }

}
