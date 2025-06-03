package src.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class HomePage extends JPanel {

    private Image backgroundImage;
    private JButton play;

    public HomePage(int fr_width, int fr_height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("assets/images/home/home.png"));
            backgroundImage = originalImage.getScaledInstance(fr_width, fr_height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.print("Error loading background image");
            e.printStackTrace();
        }


        setLayout(null);
        setPreferredSize(new Dimension(fr_width, fr_height));

        // Button
        int buttonWidth = 100;
        int buttonHeight = 40;
        int x = (fr_width - buttonWidth) / 2;
        int y = fr_height - buttonHeight - 50;

        play = new JButton("Play");
        play.setBounds(x, y, buttonWidth, buttonHeight);
        play.setBackground(Color.decode("#DB2B39"));
        play.setFont(new Font("Arial", Font.BOLD, 18));
        play.setForeground(Color.WHITE);
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        add(play);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
