package src.util;

import javax.swing.JFrame;
import java.awt.Color;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setTitle("Nebula Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);        
        this.setSize(350, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
    }
}
