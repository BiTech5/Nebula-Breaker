package src.ui;
import src.ui.SettingsPage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;
import src.ui.GamePage;
public class HomePage extends JPanel {

    private Image backgroundImage;
    private JButton play,settings,exit;

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

        int buttonWidth = 150;
        int buttonHeight = 40;
        // play button
        int x_pl = (fr_width - buttonWidth) / 2;
        int y_pl = fr_height - buttonHeight - 150;

        play = new JButton("PLAY");
        play.setBounds(x_pl, y_pl, buttonWidth, buttonHeight);
        play.setBackground(Color.decode("#106193"));
        play.setFont(new Font("Arial", Font.BOLD, 18));
        play.setForeground(Color.WHITE);
        play.setFocusPainted(false);
        add(play);

        play.addActionListener(e -> {
            removeAll();
            setLayout(new BorderLayout());
            add(new GamePage(fr_width, fr_height));
            backgroundImage = null;
            revalidate();
            repaint();
        });
        // setting button
        int x_se = (fr_width - buttonWidth) / 2;
        int y_se = fr_height - buttonHeight - 100;

        settings = new JButton("SETTINGS");
        settings.setBounds(x_se, y_se, buttonWidth, buttonHeight);
        settings.setBackground(Color.decode("#106193"));
        settings.setFont(new Font("Arial", Font.BOLD, 18));
        settings.setForeground(Color.WHITE);
        settings.setFocusPainted(false);
        add(settings);

        settings.addActionListener(e -> {
            removeAll();
            setLayout(new BorderLayout());
            add(new SettingsPage(fr_width, fr_height));
            backgroundImage = null;
            revalidate();
            repaint();
        });

        // exit button
        int x_ex = (fr_width - buttonWidth) / 2;
        int y_ex = fr_height - buttonHeight - 50;

        exit = new JButton("EXIT");
        exit.setBounds(x_ex, y_ex, buttonWidth, buttonHeight);
        exit.setBackground(Color.decode("#106193"));
        exit.setFont(new Font("Arial", Font.BOLD, 18));
        exit.setForeground(Color.WHITE);
        exit.setFocusPainted(false);
        add(exit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
