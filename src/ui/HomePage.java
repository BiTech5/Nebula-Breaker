package src.ui;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private JLabel label;
    private JButton start;

    public HomePage(int fr_width,int fr_height) {
        this.setLayout(null);
        this.setBackground(Color.decode("#212121")); // making panel bg black
        label = new JLabel("Welcome to the Home Page");
        label.setForeground(Color.WHITE); // making label color white
        add(label);

        start = new JButton("Start");
        int buttonWidth = 100;
        int buttonHeight = 40;
        int x = (fr_width - buttonWidth) / 2;  
        int y = fr_height - buttonHeight - 50;
        start.setBackground(Color.decode("#DB2B39"));
        start.setForeground(Color.WHITE);
        start.setBounds(x,y,buttonWidth,buttonHeight);
        add(start);
    }
}
