package src.view;

import src.view.LeadershipBoard;
import src.view.SettingsPage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;

import src.core.PageNavigator;
import src.view.GamePage;

public class HomePage extends JPanel {

    private Image backgroundImage;
    private JButton play, settings, exit, leadershipboard;
    // homepage constructor

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
        int x_lb = (fr_width - buttonWidth) / 2;
        int y_lb = fr_height - buttonHeight - 150;
        leadershipboard = new JButton("Leadership");
        leadershipboard.setBounds(x_lb, y_lb, buttonWidth, buttonHeight);
        leadershipboard.setBackground(Color.decode("#106193"));
        leadershipboard.setFont(new Font("Arial", Font.BOLD, 18));
        leadershipboard.setForeground(Color.WHITE);
        leadershipboard.setFocusPainted(false);
        add(leadershipboard);
        leadershipboard.addActionListener(e -> {
            PageNavigator.navigateTo(this, new LeadershipBoard(fr_width, fr_height));
            backgroundImage = null;
        });
        // play button
        int x_pl = (fr_width - buttonWidth) / 2;
        int y_pl = fr_height - buttonHeight - 200;

        play = new JButton("PLAY");
        play.setBounds(x_pl, y_pl, buttonWidth, buttonHeight);
        play.setBackground(Color.decode("#106193"));
        play.setFont(new Font("Arial", Font.BOLD, 18));
        play.setForeground(Color.WHITE);
        play.setFocusPainted(false);
        add(play);

        play.addActionListener(e -> {
            PageNavigator.navigateTo(this, new GamePage(fr_width, fr_height));
            backgroundImage = null;
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
            PageNavigator.navigateTo(this, new SettingsPage(fr_width, fr_height));
            backgroundImage = null;
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
        exit.addActionListener(e -> {
            JDialog dd = new JDialog((Frame) null, "Do you want to exit?", true);
            dd.setLayout(new FlowLayout());
            dd.setSize(250, 100);
            dd.setResizable(false);

            JButton yes = new JButton("Yes");
            JButton no = new JButton("No");

            yes.setFocusPainted(false);
            no.setFocusPainted(false);

            yes.addActionListener(event -> System.exit(0));
            no.addActionListener(event -> dd.dispose());

            dd.add(yes);
            dd.add(no);

            dd.setLocationRelativeTo(null);
            dd.setVisible(true);
        });

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