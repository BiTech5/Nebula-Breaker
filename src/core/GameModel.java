package src.core;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameModel {
    public int playerX = 160;
    public int playerY = 475;
    public final int playerWidth = 30;
    public final int playerHeight = 60;
    public int speed = 7;
    public int playerHealth = 3;
    public int score = 0;

    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;

    public ArrayList<Bullet> bullets = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Bullet> enemyBullets = new ArrayList<>();

    public static class Bullet {
        public int x, y, width = 5, height = 10, dy;
        public Bullet(int x, int y, int dy) {
            this.x = x;
            this.y = y;
            this.dy = dy;
        }
        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
    }

    public static class Enemy {
        public int x, y, width = 40, height = 40;
        private int dx = 2;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int panelWidth) {
            x += dx;
            if (x < 0 || x > panelWidth - width) {
                dx = -dx;
                y += 20;
            }
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }
    }

    public void initEnemies() {
        for (int i = 0; i < 10; i++) {
            enemies.add(new Enemy(50 + i * 100, 50));
        }
    }

    public Rectangle getPlayerBounds() {
        return new Rectangle(playerX, playerY, playerWidth, playerHeight);
    }
}