package src.view;

import src.core.PageNavigator;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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

        JButton home = new JButton(" BACK TO HOME");
        ImageIcon leftArrow = new ImageIcon("assets/images/left_arrow.png");
        Image arrowImg = leftArrow.getImage();
        Image scaleImage = arrowImg.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        ImageIcon scaledArrow = new ImageIcon(scaleImage);

        home.setBounds(95, 10, 160, 40);
        home.setForeground(Color.decode("#A0F8FF"));
        home.setBackground(Color.decode("#0D0D0D"));
        home.setFont(new Font("SansSerif", Font.BOLD, 15));
        home.setFocusPainted(false);
        home.setIcon(scaledArrow);
        home.setIconTextGap(8);
        home.setOpaque(true);
        home.setBorder(BorderFactory.createLineBorder(Color.decode("#00D8FF"), 2));

        home.addActionListener(e -> {
            PageNavigator.navigateTo(this, new HomePage(fr_width, fr_height));
            backgroundImage = null;
        });

        topPanel.add(home);
        add(topPanel, BorderLayout.NORTH);

        // Content panel to hold leaderboard boxes
        JPanel contentPanel = new JPanel(null);
        contentPanel.setPreferredSize(new Dimension(fr_width, 800));
        contentPanel.setOpaque(false);

        // Add leaderboard boxes
        contentPanel.add(createBox("Easy", 50, 50));
        contentPanel.add(createBox("Medium", 50, 280));
        contentPanel.add(createBox("Hard", 50, 510));

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createBox(String title, int x, int y) {
        JPanel box = new JPanel(null);
        box.setBounds(x, y, 300, 200);
        box.setBackground(new Color(0, 0, 50, 180));

        JLabel label = new JLabel(title + " Scores");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 22));
        label.setBounds(10, 10, 200, 30);
        box.add(label);

        if (title.equalsIgnoreCase("Easy")) {
            try {
                File file = new File("data/easy.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                ArrayList<Integer> scores = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null) {
                    try {
                        int score = Integer.parseInt(line.trim());
                        scores.add(score);
                    } catch (NumberFormatException e) {
                        // Ignore invalid lines
                    }
                }
                reader.close();

                // Sort scores descending
                Collections.sort(scores, Collections.reverseOrder());

                // Show top 3 scores (or all if less than 3)
                int yOffset = 50;
                int limit = Math.min(3, scores.size());
                for (int i = 0; i < limit; i++) {
                    JLabel scoreLabel = new JLabel((i + 1) + ". " + scores.get(i));
                    scoreLabel.setForeground(Color.WHITE);
                    scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                    scoreLabel.setBounds(20, yOffset, 250, 25);
                    box.add(scoreLabel);
                    yOffset += 30;
                }

            } catch (IOException e) {
                System.out.println("Error reading easy.txt");
                e.printStackTrace();
            }
        }

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
