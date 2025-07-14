package src.util;

import java.awt.event.KeyEvent;
import src.view.GamePage;

public class PlayerController {
    private final GamePage gamePage;

    private boolean left, right, up, down, space;
    private final int speed = 5;
    private long lastShotTime = 0;
    private final long shootDelay = 300; // delay for shots



    public PlayerController(GamePage gamePage) {
        this.gamePage = gamePage;
    }

    public void keyPressed(int key) {
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) left = true;
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) right = true;
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) up = true;
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) down = true;
        if (key == KeyEvent.VK_SPACE) space = true;
    }

    public void keyReleased(int key) {
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) left = false;
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) right = false;
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) up = false;
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) down = false;
        if (key == KeyEvent.VK_SPACE) space = false;
    }

    public void update() {
        int dx = 0, dy = 0;

        if (left) dx -= speed;
        if (right) dx += speed;
        if (up) dy -= speed;
        if (down) dy += speed;

    gamePage.player.move(dx, dy, gamePage.getWidth(), gamePage.getHeight());

    if (space) {
            long now = System.currentTimeMillis();
        if (now - lastShotTime >= shootDelay) {
            gamePage.firePlayerBullets();
            lastShotTime = now;
        }
    }
}
}
