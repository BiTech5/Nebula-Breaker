package src.view;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import src.core.Player;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import src.core.PlayerBullet;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Timer;
import src.core.Enemy;
import src.core.EnemyBullet;
public class GamePage extends JPanel implements KeyListener {
    private Image backgroundImage;
    private Player player;
    private List<PlayerBullet> bullets = new ArrayList<>();
    private Timer gameTimer;
    private List<Enemy> enemies = new ArrayList<>();
    private List<EnemyBullet> enemyBullets = new ArrayList<>();
    private long lastEnemyFireTime = 0;


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
        for (int i = 0; i < 3; i++) {
            int randX = (int) (Math.random() * (fr_width - 50));
            int randY = 20 + (int) (Math.random() * 80); 
            enemies.add(new Enemy(randX, randY));
        }
        // game loop timer to move bullets and repaint
        gameTimer = new Timer(16, e -> {
            bullets.removeIf(PlayerBullet::isOffScreen);
            for (PlayerBullet bullet : bullets) {
                bullet.move();
            }
            for (Enemy en : enemies) {
                en.move(getWidth()); 
            }
            

            enemyBullets.removeIf(b -> b.isOffScreen(getHeight()));
            for (EnemyBullet eb : enemyBullets) eb.move();

            if (System.currentTimeMillis() - lastEnemyFireTime > 1000) {
                for (Enemy en : enemies) {
                    enemyBullets.add(new EnemyBullet(en.getX() + 20, en.getY() + 50));
                }
                lastEnemyFireTime = System.currentTimeMillis();
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

        for (PlayerBullet bullet : bullets) {
            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
        }

        for (Enemy enemy : enemies) {
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        }
        for (EnemyBullet b : enemyBullets)
            g.drawImage(b.getImage(), b.getX(), b.getY(), this);
        
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

            bullets.add(new PlayerBullet(bullet1X, bullet1Y, bulletWidth, bulletHeight));
            bullets.add(new PlayerBullet(bullet2X, bullet2Y, bulletWidth, bulletHeight));
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
