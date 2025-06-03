package src.util;

import java.awt.Color;
import javax.swing.JFrame;

import src.ui.HomePage;

import javax.swing.ImageIcon;
public class GameFrame extends JFrame {
    public GameFrame() {
        // adding game logo
        ImageIcon logo = new ImageIcon("assets/images/logo.png");
        this.setIconImage(logo.getImage());

        this.setTitle("Nebula Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 600);
        this.setResizable(false);        
        this.setLocationRelativeTo(null); // making app  in center
        this.setVisible(true);
        this.add(new HomePage()); // rendering home page Pannel
    }
}
