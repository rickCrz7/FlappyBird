import javax.swing.JFrame;

public class Frame extends JFrame{
    public Frame(){
        add(new FlappyPanel());
        setSize(600, 800);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}