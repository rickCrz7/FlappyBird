import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyPanel extends JPanel implements KeyListener, ActionListener {
    // set up the colors and dimensions of the game
    Color skyBlue = new Color(0, 165, 220);
    Timer time = new Timer(30, this);
    private final int Width = 600;
    private final int Height = 800;

    // set up the bird variables
    private int birdHeight = Height/ 4; // the height where the bird starts at
    private int velocity = 0;
    private int acceleration = 8;
    private int impulse = 1;

    // set up the wall variables
    final int Wall_Velocity = 5; // the speed of the wall
    final int Wall_Width = 100; // how wide the wall is

    final int GAP_SIZE = 100; // the size of the gap between the walls
    final int MIN_GAP_POS = 150; // the minimum distance from the top and bottom of the screen
    final int MAX_GAP_POS = Height - MIN_GAP_POS - GAP_SIZE; // the maximum distance from the top and bottom of the screen

    int [] wallX =  {Width, Width + 350}; // the x position of the wall
    int [] gap = {(int) (Math.random() * (MAX_GAP_POS - MIN_GAP_POS) + MIN_GAP_POS) , (int) (Math.random() * (MAX_GAP_POS - MIN_GAP_POS) + MIN_GAP_POS)}; // the gap between the walls

    boolean gameOver = false;
    //boolean restartClicked = false;
    int score;


    public FlappyPanel(){
        setSize(Width,Height); // set the size of the panel
        setFocusable(true);
        addKeyListener(this);

        setBackground(skyBlue); // set the background color of the panel
        time.start(); // start the timer
    }
    public void paintComponent(Graphics g){
        //super.paintComponent(g);
        if(!gameOver) {
            logic();
            drawWall(g);
            drawBird(g);
            setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
            g.drawString("Score: " + score, 20, 20);
        }else{
            gameOver(g);
        }

    }

    private void drawBird(Graphics g){
        // draw the bird
        g.setColor(Color.YELLOW); // set the color of the bird
        g.fillOval(Width/4, birdHeight + velocity, 25, 25);
    }
    private void drawWall(Graphics g){
        for(int i = 0 ; i < 2; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(wallX[i], 0, Wall_Width, Height);
            g.setColor(skyBlue);
            g.fillRect(wallX[i], gap[i], Wall_Width, GAP_SIZE);
        }
    }
    private void logic(){
        if (birdHeight + velocity >= Height || birdHeight + velocity <= 0){
            gameOver = true;
        }
        for (int i = 0; i < 2; i++) {
            if (wallX[i] <= 175 && wallX[i] + Wall_Width >= 175) { // if the bird is in between the walls
                if ((birdHeight + velocity) >= 0 && (birdHeight + velocity) <= gap[i] // if the bird is in between the top wall and the gap
                        || (birdHeight + velocity) <= Height && (birdHeight + velocity) >= gap[i] + GAP_SIZE) { // if the bird is in between the bottom wall and the gap
                    gameOver = true;
                }
            }
            if (wallX[i] + Wall_Width <= 0) {
                wallX[i] = Width;
                score++;
            }
        }

    }
    private void gameOver(Graphics g){
        // draw the game over screen
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over", 400/2, 800/2);
        g.drawString("Score: " + score, 400/2, 800/2 + 50);

        g.drawString("Press R to restart", 250, 800/2 + 300);
    }

    public void actionPerformed(ActionEvent e){
        // update the bird's position
        acceleration += impulse; // add the impulse to the acceleration
        velocity += acceleration; // add the acceleration to the velocity
        // update the wall's position
        wallX[0] -= Wall_Velocity;
        wallX[1] -= Wall_Velocity;
        repaint();

    }
    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            acceleration = -10;
        }
        if(code == e.VK_S){
            time.start();
        }
        if(code == e.VK_R){
            time.stop();
            birdHeight = Height/ 4; // the height where the bird starts at
            velocity = 0;
            acceleration = 8;
            impulse = 1;
            wallX[0] = Width;
            wallX[1] = Width + 350;
            gap[0] = (int) (Math.random() * (MAX_GAP_POS - MIN_GAP_POS) + MIN_GAP_POS);
            gap[1] = (int) (Math.random() * (MAX_GAP_POS - MIN_GAP_POS) + MIN_GAP_POS);
            setBackground(skyBlue);
            gameOver = false;
            score = 0;
            repaint();
        }
    }
    public void keyReleased(KeyEvent e){

    }

}


