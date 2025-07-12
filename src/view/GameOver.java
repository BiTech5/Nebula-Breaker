package src.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {

    public GameOver(int score, ActionListener onRetry, ActionListener onHome) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 150)); 

        JLabel title = new JLabel("Game Over");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        JButton retryButton = new JButton("Play Again");
        retryButton.setAlignmentX(CENTER_ALIGNMENT);
        retryButton.setFocusPainted(false);
        retryButton.addActionListener(onRetry);

        JButton homeButton = new JButton("Go Home");
        homeButton.setAlignmentX(CENTER_ALIGNMENT);
        homeButton.setFocusPainted(false);
        homeButton.addActionListener(onHome);

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(retryButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(homeButton);
        add(Box.createVerticalGlue());
    }
    
}
