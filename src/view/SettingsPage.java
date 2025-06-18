package src.view;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SettingsPage extends JPanel {
    private JLabel l1;
    private JButton home;
    private Image backgroundImage;

    public SettingsPage(int fr_width, int fr_height){
        setLayout(null);
        setPreferredSize(new Dimension(fr_width, fr_height));
        l1=new JLabel("Settings Page");

        l1.setForeground(Color.WHITE);

        try{
        BufferedImage bgImage = ImageIO.read(new File("assets/images/nebula_breaker_bg_img.png"));
        backgroundImage = bgImage.getScaledInstance(fr_width, fr_height, Image.SCALE_SMOOTH);
     } catch(IOException e){
        System.out.println("Error loading background image" + e.getMessage());
     }



        l1.setBounds(150,40,100,30);
        add(l1);

        
    }
    @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }

        }

}
