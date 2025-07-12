package src.view;

import src.core.PageNavigator;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class LeadershipBoard extends JPanel {
    private Image backgroundImage;

    public LeadershipBoard(int fr_width, int fr_height) {
        // Load background image
        try {
            BufferedImage originalImage = ImageIO.read(new File("assets/images/leadershipBoardBg.png"));
            backgroundImage = originalImage.getScaledInstance(fr_width, fr_height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error loading background image");
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(fr_width, fr_height));

        // Top panel with BACK TO HOME button
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(fr_width, 60));
        topPanel.setOpaque(false);

        // Define button
        JButton home = new JButton("<- BACK TO HOME");

        // Load arrow icon (optional)
        ImageIcon arrowIcon = null;
        try {
            BufferedImage arrowImage = ImageIO.read(new File("assets/images/left-arrow.png"));
            Image scaledArrow = arrowImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            arrowIcon = new ImageIcon(scaledArrow);
        } catch (Exception e) {
            System.out.println("Error loading arrow icon");
        }

        // Setup button style
        home.setBounds(95, 10, 160, 40);
        home.setForeground(Color.decode("#A0F8FF"));
        home.setBackground(Color.decode("#0D0D0D"));
        home.setFont(new Font("SansSerif", Font.BOLD, 15));
        home.setFocusPainted(false);
        home.setIcon(arrowIcon);
        home.setIconTextGap(8);
        home.setOpaque(true);
        home.setBorder(BorderFactory.createLineBorder(Color.decode("#00D8FF"), 2));

        // Add navigation logic
        home.addActionListener(e -> {
            PageNavigator.navigateTo(this, new HomePage(fr_width, fr_height));
            backgroundImage = null;
        });

        topPanel.add(home);
        add(topPanel, BorderLayout.NORTH);

        // Create an inner panel to hold the leaderboard boxes
        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(fr_width, 800));
        contentPanel.setOpaque(false);

        // Create and add the boxes
        contentPanel.add(createBox("Easy", 50, 50));
        contentPanel.add(createBox("Medium", 50, 280));
        contentPanel.add(createBox("Hard", 50, 510));

        // Add the content panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Add scroll pane to the main panel
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createBox(String title, int x, int y) {
        JPanel box = new JPanel(null);
        box.setBounds(x, y, 300, 200);
        box.setBackground(new Color(0, 0, 50, 180));

        JLabel label = new JLabel(title);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(10, 10, 200, 30);
        box.add(label);

        return box;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
