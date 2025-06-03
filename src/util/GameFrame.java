package src.util;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Nebula Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 600);
        this.setResizable(false);        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        ImageIcon logo = new ImageIcon("assets/images/logo.png");
        this.setIconImage(logo.getImage());
    }
}
