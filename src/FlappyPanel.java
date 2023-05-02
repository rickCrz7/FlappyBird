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
    boolean restartClicked = false;
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
            if(!restartClicked) {
                gameOver(g);
            } else {
                restart();
                restartClicked = true;
            }
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
            g.fillRect(wallX[i], gap[i], Wall_Width, GAP_SIZE);
        }
    }
//    private void logic(){
//        for(int i = 0 ; i < 2; i++) {
//            if (wallX[i] <= 100 && wallX[i] + Wall_Width >= 100) {
//                if ((birdHeight + velocity) >= 0 && (birdHeight + velocity) <= gap[i]
//                        || (birdHeight + velocity + 25) >= gap[i] + 100 && (birdHeight + velocity + 25) <= Height) {
//                    gameOver = true;
//                }
//            }
//            if (wallX[i] + Wall_Width <=0){
//                wallX[i] = Width;
//                score ++;
//            }
//        }
//    }
    private void logic(){
        if (birdHeight + velocity >= Height || birdHeight + velocity <= 0){
            gameOver = true;
        }
        for (int i = 0; i < 2; i++) {
            if (wallX[i] <= 100 && wallX[i] + Wall_Width >= 100) {
                if ((birdHeight + velocity) >= 0 && (birdHeight + velocity) <= gap[i]
                        || (birdHeight + velocity + 25) >= gap[i] + 100 && (birdHeight + velocity + 25) <= Height) {
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
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Width,Height);
        g.setColor(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Game Over", 400/2, 800/2);
        g.drawString("Score: " + score, 400/2, 800/2 + 50);

//        // create a new button to restart the game
//        JButton restartButton = new JButton("Restart Game");
//        restartButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // restart the game
//                JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(restartButton);
//                frame.dispose(); // dispose of the current game JFrame
//                new FlappyBirdStartMenu(); // create a new JFrame for the game
//            }
//        });
//
//        // add the restart button to the game over screen
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(restartButton);
//        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void restart(){
        birdHeight = Height/4;
        velocity = 0;
        acceleration = 8;
        impulse = 2;
        wallX[0] = Width;
        wallX[1] = Width + Width/2;
        gap[0] = (int)(Math.random() * Height- 150);
        gap[1] = (int)(Math.random() * Height- 100);
        gameOver = false;
        score = 0;
    }

    public void actionPerformed(ActionEvent e){
        // update the bird's position
        acceleration += impulse;
        velocity += acceleration;
        //birdHeight += velocity;
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
    }
    public void keyReleased(KeyEvent e){

    }

}


