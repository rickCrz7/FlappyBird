import javax.swing.JFrame;

public class Frame extends JFrame{

    public Frame(){
        add(new FlappyPanel());
        setSize(600, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Frame();
    }
}