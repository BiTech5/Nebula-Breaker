package src.view;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import src.core.Player;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import src.core.Bullet;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Timer;

public class GamePage extends JPanel implements KeyListener {
    private Image backgroundImage;
    private Player player;
    private List<Bullet> bullets = new ArrayList<>();
    private Timer gameTimer;

    public GamePage(int fr_width, int fr_height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("assets/images/nebula_breaker_bg_img.png"));
            backgroundImage = originalImage.getScaledInstance(fr_width, fr_height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.print("Error loading background image");
            e.printStackTrace();
        }
        setLayout(null);
        setPreferredSize(new Dimension(fr_width, fr_height));

        player = new Player(125, 460, 100, 100);

        // game loop timer to move bullets and repaint
        gameTimer = new Timer(16, e -> {
            bullets.removeIf(Bullet::isOffScreen);
            for (Bullet bullet : bullets) {
                bullet.move();
            }
            repaint();
        });
        gameTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
        if (player != null) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        for (Bullet bullet : bullets) {
            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int dx = 0;
        int dy = 0;
        System.out.println("Key pressed: " + key);
        int speed = 10;
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = -speed;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = speed;
        } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = -speed;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = speed;
        } else if (key == KeyEvent.VK_SPACE) {
            int shipX = player.getX();
            int shipY = player.getY();
            int shipWidth = player.getImage().getWidth(null);
            int bulletWidth = 10;
            int bulletHeight = 30;

            int bullet1X = shipX + 10; 
            int bullet1Y = shipY - 20;

            int bullet2X = shipX + shipWidth - bulletWidth - 10; 
            int bullet2Y = shipY - 20;

            bullets.add(new Bullet(bullet1X, bullet1Y, bulletWidth, bulletHeight));
            bullets.add(new Bullet(bullet2X, bullet2Y, bulletWidth, bulletHeight));
        }
        player.move(dx, dy, getWidth(), getHeight());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
