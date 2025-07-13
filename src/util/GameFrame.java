package src.util;
// import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import src.core.PlayMusic;
import src.view.HomePage;               
public class GameFrame extends JFrame {
    public int width=350;
    public int height = 600;
    public GameFrame() {
        // adding game logo
        
        ImageIcon logo = new ImageIcon("assets/images/logo.png");
        this.setIconImage(logo.getImage());

        PlayMusic musicPlayer = new PlayMusic(); 
        musicPlayer.play(true);
        this.setTitle("Nebula Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);        
        this.setLocationRelativeTo(null); // making app  in center
        this.add(new HomePage(width,height)); // rendering home page Pannel
        this.setVisible(true);
    }
}