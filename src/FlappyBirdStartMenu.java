import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlappyBirdStartMenu extends JFrame implements ActionListener {
    private JButton startButton;
    public FlappyBirdStartMenu() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        //setResizable(false);
        //setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // create the start button
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(100, 50));
        startButton.setBackground(Color.RED);
        panel.add(startButton, BorderLayout.SOUTH);

        // create the title label
        JLabel titleLabel = new JLabel("Flappy Bird");
        titleLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.CENTER);

        // create the background color
        panel.setBackground(new Color(0, 165, 220));

        // add the panel to the frame
        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // start the game
            dispose(); // dispose of the start screen JFrame
            new Frame(); // create a new JFrame for the game
        }
    }

    public static void main(String[] args) {
        new FlappyBirdStartMenu(); // create a new JFrame for the start menu
    }
}
