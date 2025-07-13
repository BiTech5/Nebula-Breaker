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

import src.core.Enemy;
import src.core.EnemyBullet;
import src.core.PageNavigator;
import src.util.GameEngine;
import src.util.PlayerController;

public class GamePage extends JPanel implements KeyListener {

    public Image backgroundImage;
    public Player player;
    public List<PlayerBullet> bullets = new ArrayList<>();
    public Timer gameTimer;
    public List<Enemy> enemies = new ArrayList<>();
    public List<EnemyBullet> enemyBullets = new ArrayList<>();
    public long lastEnemyFireTime = 0;

    public JLabel scoreLabel;
    public JLabel livesLabel;
    public int score = 0;
    public int lives = 4;

    public long lastEnemySpawnTime = 0;
    public int maxEnemies = 5;

    public int fr_width;
    public int fr_height;

    private GameEngine engine;
    private PlayerController controller;

   
    public GamePage(int fr_width, int fr_height) {
        this.fr_width = fr_width;
        this.fr_height = fr_height;
       
        loadBackgroundImage();
        setupPanel();
        initializePlayerAndEnemies();
        setupLabels();
        setupGameTimer();

    }
    
    private void loadBackgroundImage() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("assets/images/nebula_breaker_bg_img.png"));
            backgroundImage = originalImage.getScaledInstance(fr_width, fr_height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.print("Error loading background image");
            e.printStackTrace();
        }
    }

    private void setupPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(fr_width, fr_height));
    }

    private void initializePlayerAndEnemies() {
        player = new Player(125, 460, 100, 100);
        for (int i = 0; i < 3; i++) {
            int randX = (int) (Math.random() * (fr_width - 50));
            int randY = 20 + (int) (Math.random() * 80);
            enemies.add(new Enemy(randX, randY));
        }
    }

    private void setupLabels() {
        scoreLabel = new JLabel("Score: ");
        scoreLabel.setForeground(Color.decode("#8c8c8c"));
        scoreLabel.setFont(new Font("SansSerrif", Font.BOLD, 16));
        scoreLabel.setBounds(10, 5, 150, 30);

        livesLabel = new JLabel("Lives:");
        livesLabel.setForeground(Color.decode("#8c8c8c"));
        livesLabel.setFont(new Font("SansSerrif", Font.BOLD, 16));
        livesLabel.setBounds(200, 5, 150, 30);

        add(scoreLabel);
        add(livesLabel);
    }

    private void setupGameTimer() {
        engine = new GameEngine(this);
        controller = new PlayerController(this);
        gameTimer = new Timer(16, e -> engine.update());
        gameTimer.start();
    }

    public void goHome() {
        PageNavigator.navigateTo(this, new HomePage(fr_width, fr_height));
        backgroundImage = null;
    }

    public void restartGame() {
        PageNavigator.navigateTo(this, new GamePage(fr_width, fr_height));
        backgroundImage = null;
    }

    public void firePlayerBullets() {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, this);
        if (player != null)
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        for (PlayerBullet bullet : bullets)
            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
        for (Enemy enemy : enemies)
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        for (EnemyBullet b : enemyBullets)
            g.drawImage(b.getImage(), b.getX(), b.getY(), this);

        for (int i = 0; i < lives; i++) {
            if (i >0) {
                g.setColor(Color.RED);
                g.fillRect(250 + i * 25, 15, 20, 15);
            } 
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
        controller.handleKey(e.getKeyCode());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
