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
    private JPanel mainBox;

    public SettingsPage(int fr_width, int fr_height){
        setLayout(null);
        setPreferredSize(new Dimension(fr_width, fr_height));

        try{
        BufferedImage bgImage = ImageIO.read(new File("assets/images/nebula_breaker_bg_img.png"));
        backgroundImage = bgImage.getScaledInstance(fr_width, fr_height, Image.SCALE_SMOOTH);
     } catch(IOException e){
        System.out.println("Error loading background image" + e.getMessage());
     }

        //back to home button
        home = new JButton();
        ImageIcon leftArrow = new ImageIcon("assets/images/left_arrow.png");
        Image arrowImg = leftArrow.getImage();
        Image scaleImage = arrowImg.getScaledInstance(18,18,Image.SCALE_SMOOTH);
        ImageIcon scaledArrow = new ImageIcon(scaleImage);

        int buttonWidth = 180;
        int buttonHeight = 40;

        
        int x_home = (fr_width - buttonWidth) / 2;
        int y_home = 30;

        home.setBounds(x_home, y_home, buttonWidth, buttonHeight);
        home.setText("BACK TO HOME");
        home.setForeground(Color.decode("#A0F8FF"));
        home.setBackground(Color.decode("#0D0D0D"));
        home.setFont(new Font("SansSerrif", Font.BOLD, 15));
        home.setFocusPainted(false);
        home.setIcon(scaledArrow);
        home.setIconTextGap(8);
        home.setOpaque(true);
        home.setBorder(BorderFactory.createLineBorder(Color.decode("#00D8FF"),2));


        //Title
        l1 = new JLabel();
        l1.setText("SETTINGS");
        l1.setForeground(Color.decode("#A0F8FF"));
        l1.setFont(new Font("SansSerrif", Font.BOLD, 26));
        int labelWidth = 150;
        int labelHeight = 40;
        int y_title = y_home + buttonHeight + 10; 
        int x_title = (fr_width - labelWidth) / 2;
        l1.setBounds(x_title, y_title, labelWidth, labelHeight);

        //Main box
        mainBox = new JPanel();
        int mainBoxWidth = 250;
        int mainBoxHeight = 400;
        int x_box = (fr_width - mainBoxWidth) / 2;
        int y_box = fr_height - mainBoxHeight - 70;
        mainBox.setBounds(x_box, y_box, mainBoxWidth, mainBoxHeight);
        mainBox.setOpaque(false);
        mainBox.setBorder(BorderFactory.createLineBorder(Color.decode("#00D8FF"), 2));
        mainBox.setBackground(Color.WHITE);

        add(l1);
        add(home);
        add(mainBox);

        
    }
    @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }

        }

}
