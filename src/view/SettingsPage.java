package src.view;
import java.awt.*;
import javax.swing.*;

public class SettingsPage extends JPanel {
    private JLabel l1;
    private JButton home;
    public SettingsPage(int fr_width, int fr_height){
        setLayout(null);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(fr_width, fr_height));
        l1=new JLabel("Setting Page");
        l1.setForeground(Color.WHITE);
        l1.setBounds(150,40,100,30);
        add(l1);
    }

}
