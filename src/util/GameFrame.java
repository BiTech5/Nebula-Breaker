package src.util;
import java.awt.Color;
import javax.swing.JFrame;
import src.view.HomePage;
import src.view.HomePage;
import src.util.SoundManager;
import javax.swing.ImageIcon;

public class GameFrame extends JFrame {
    public int width = 350;
    public int height = 600;

    public GameFrame() {
        // adding game logo
        SoundManager.playBackgroundMusic("assets/sounds/music/background_music.wav");
        ImageIcon logo = new ImageIcon("assets/images/logo.png");
        this.setIconImage(logo.getImage());

        this.setTitle("Nebula Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // center window
        this.add(new HomePage(width, height)); // load HomePage first
        this.setVisible(true);
    }
}
