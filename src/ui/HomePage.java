package src.ui;
import javax.swing.*;
import java.awt.*;
public class HomePage extends JPanel {
    public HomePage(){
        this.setLayout(new FlowLayout());
        JLabel label=new JLabel("Welcome to the Home Page");
        label.setForeground(Color.WHITE); // making label color white
        add(label);
        this.setBackground(Color.BLACK); // making panel bg black
    }
}