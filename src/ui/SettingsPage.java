package src.ui;
import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {
    private JLabel l1;
    public SettingsPage(int fr_width, int fr_height){
        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(fr_width, fr_height));
        l1=new JLabel("Setting Page");
        l1.setForeground(Color.BLACK);
        l1.setBounds(150,40,30,30);
        add(l1);
    }

}
