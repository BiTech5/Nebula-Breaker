package src.view;

import src.model.GameModel;
import src.model.GameModel.Bullet;
import src.model.GameModel.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class GamePage extends JPanel implements ActionListener, KeyListener {
    private final GameModel model;
    private final Image background;
    private Timer gameTimer;
    private long lastFireTime = 0;
    private final long fireCooldown = 300;

    public GamePage(GameModel model, int width, int height) {
        this.model = model;
        setLayout(null);
        background = new ImageIcon("assets/images/nebula_breaker_bg_img.png").getImage();
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);
        SwingUtilities.invokeLater(this::requestFocusInWindow);

        model.initEnemies();
        gameTimer = new Timer(16, this);
        gameTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.upPressed && model.playerY >= 300) model.playerY -= model.speed;
        if (model.downPressed && model.playerY < 500) model.playerY += model.speed;
        if (model.leftPressed && model.playerX > 0) model.playerX -= model.speed;
        if (model.rightPressed && model.playerX < 300) model.playerX += model.speed;

        Iterator<Bullet> bulletIter = model.bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet b = bulletIter.next();
            b.y -= 8;
            if (b.y < 0) bulletIter.remove();
        }

        for (Enemy enemy : model.enemies) enemy.move(getWidth());

        for (Enemy enemy : model.enemies) {
            if (Math.random() < 0.01) {
                model.enemyBullets.add(new Bullet(enemy.x + enemy.width / 2 - 2, enemy.y + enemy.height, 5));
            }
        }

        Iterator<Bullet> enemyBulletIter = model.enemyBullets.iterator();
        while (enemyBulletIter.hasNext()) {
            Bullet eb = enemyBulletIter.next();
            eb.y += eb.dy;
            if (eb.y > getHeight()) enemyBulletIter.remove();
        }

        Rectangle playerRect = model.getPlayerBounds();
        enemyBulletIter = model.enemyBullets.iterator();
        while (enemyBulletIter.hasNext()) {
            Bullet eb = enemyBulletIter.next();
            if (eb.getBounds().intersects(playerRect)) {
                model.initEnemies();
                enemyBulletIter.remove();
                model.playerHealth--;
                if (model.playerHealth <= 0) {
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(this, "Game Over!");
                }
            }
        }

        Iterator<Bullet> bIter = model.bullets.iterator();
        while (bIter.hasNext()) {
            Bullet b = bIter.next();
            Iterator<Enemy> eIter = model.enemies.iterator();
            while (eIter.hasNext()) {
                Enemy enemy = eIter.next();
                if (b.getBounds().intersects(enemy.getBounds())) {
                    bIter.remove();
                    eIter.remove();
                    model.score += 10;
                    break;
                }
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        g.setColor(Color.WHITE);
        g.fillRect(model.playerX, model.playerY, model.playerWidth, model.playerHeight);

        g.setColor(Color.YELLOW);
        for (Bullet b : model.bullets) g.fillRect(b.x, b.y, b.width, b.height);

        g.setColor(Color.RED);
        for (Enemy e : model.enemies) g.fillRect(e.x, e.y, e.width, e.height);

        g.setColor(Color.CYAN);
        for (Bullet eb : model.enemyBullets) g.fillRect(eb.x, eb.y, eb.width, eb.height);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.score, 10, 20);
        g.drawString("Health: " + model.playerHealth, 10, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) model.upPressed = true;
        if (key == KeyEvent.VK_S) model.downPressed = true;
        if (key == KeyEvent.VK_A) model.leftPressed = true;
        if (key == KeyEvent.VK_D) model.rightPressed = true;
        if (key == KeyEvent.VK_SPACE) {
            long now = System.currentTimeMillis();
            if (now - lastFireTime > fireCooldown) {
                model.bullets.add(new Bullet(model.playerX + model.playerWidth / 2 - 2, model.playerY, -8));
                lastFireTime = now;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) model.upPressed = false;
        if (key == KeyEvent.VK_S) model.downPressed = false;
        if (key == KeyEvent.VK_A) model.leftPressed = false;
        if (key == KeyEvent.VK_D) model.rightPressed = false;
    }

    @Override public void keyTyped(KeyEvent e) {}
}
