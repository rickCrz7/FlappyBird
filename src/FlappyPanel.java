import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyPanel extends JPanel implements KeyListener, ActionListener {
    // set up the colors and dimensions of the game
    Color skyBlue = new Color(0, 165, 220);
    Timer time = new Timer(40, this);
    private JButton startButton;
    final int Width = 600;
    final int Height = 800;

    // set up the bird variables
    int birdHeight = Height/4;
    int velocity = 0;
    int acceleration = 8;
    int impulse = 1;

    // set up the wall variables
    final int Wall_Velocity = 5; // the speed of the wall
    final int Wall_Width = 80; // how wide the wall is

    //int wallX = Width + 50;
    //int gap = (int)(Math.random() * Height); // random gap between 0 and Height

    int [] wallX =  {Width, Width + Width/2};
    int [] gap = {(int)(Math.random() * Height- 150) , (int)(Math.random() * Height- 100)};

    boolean gameOver = false;



//    int [] wallX = new int [2];
//    int [][] gapX = new int [2][2];

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
            drawWall(g);
            logic();
            drawBird(g);
        }else{
            gameOver(g);
            //exit();
            //System.exit(0);
        }
    }


    private void drawBird(Graphics g){
        // draw the bird
        g.setColor(Color.YELLOW); // set the color of the bird
        //g.drawLine(wallX,gap, wallX + Wall_Width, gap + 100);
        g.fillOval(Width/4, birdHeight + velocity, 25, 25);
    }
    private void drawWall(Graphics g){
        for(int i = 0 ; i < 2; i++) {
            g.setColor(Color.GREEN);
            g.fillRect(wallX[i], 0, Wall_Width, Height);
            g.setColor(skyBlue);
            g.fillRect(wallX[i], gap[i], Wall_Width, 100);
        }
    }
    private void logic(){
        for(int i = 0 ; i < 2; i++) {
            if (wallX[i] <= 100 && wallX[i] + Wall_Width >= 100) {
                if ((birdHeight + velocity) >= 0 && (birdHeight + velocity) <= gap[i]
                        || (birdHeight + velocity + 25) >= gap[i] + 100 && (birdHeight + velocity + 25) <= Height) {
                    gameOver = true;
                }
            }
            if (wallX[i] + Wall_Width <=0){
                wallX[i] = Width;
            }
        }
    }
//private void checkCollision() {
//    for (int i = 0; i < wallX.length; i++) {
//        int currentWallX = wallX[i];
//        int currentGap = gap[i];
//
//        if (birdHeight + velocity <= 0 || birdHeight + velocity + 25 >= Height) {
//            // Bird hits the top or bottom of the screen
//            gameOver = true;
//        }
//
//        if (currentWallX <= 0) {
//            // Wall is off the screen, reset it with a new gap
//            currentWallX = Width;
//            currentGap = (int)(Math.random() * (Height - 100 - 50)) + 50;
//        }
//
//        if (currentWallX <= 100 && currentWallX + Wall_Width >= 100) {
//            // Bird is within the horizontal range of the wall
//            if ((birdHeight + velocity <= currentGap) || (birdHeight + velocity + 25 >= currentGap + 100)) {
//                // Bird hits the wall
//                gameOver = true;
//            }
//        }
//    }
//}
    private void gameOver(Graphics g){
        // draw the game over screen
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Width,Height);
        g.setColor(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over", 400/2, 800/2);
    }


    public void actionPerformed(ActionEvent e){
        // update the bird's position
        acceleration += impulse;
        velocity += acceleration;
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
            acceleration = -8;
        }
        if (code == KeyEvent.VK_S) {
            time.start();
        }
        if (code == KeyEvent.VK_R){
            time.stop();
        }

    }
    public void keyReleased(KeyEvent e){

    }

}
