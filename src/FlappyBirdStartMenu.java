import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class FlappyBirdStartMenu extends JFrame implements ActionListener {
    private final JButton startButton;
    Image img;

    public FlappyBirdStartMenu() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(590, 800);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // create the start button
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(100, 50));
        panel.add(startButton, BorderLayout.SOUTH);

        // imports the image
        try {
            img = ImageIO.read(new File("src/Menu.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            panel.add(imageLabel, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
            panel.setBackground(new Color(0, 165, 220));
            JLabel titleLabel = new JLabel("Flappy Bird");
            titleLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 70));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(titleLabel, BorderLayout.CENTER);
        }

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
        new FlappyBirdStartMenu();// create a new JFrame for the start menu
    }
}
