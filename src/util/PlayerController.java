package src.util;

import java.awt.event.KeyEvent;
import src.view.GamePage;

public class PlayerController {
    private final GamePage gamePage;

    public PlayerController(GamePage gamePage) {
        this.gamePage = gamePage;
    }

    public void handleKey(int key) {
        int dx = 0, dy = 0;
        int speed = 10;
        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> dx = -speed;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> dx = speed;
            case KeyEvent.VK_UP, KeyEvent.VK_W -> dy = -speed;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> dy = speed;
            case KeyEvent.VK_SPACE -> gamePage.firePlayerBullets();
        }
        gamePage.player.move(dx, dy, gamePage.getWidth(), gamePage.getHeight());
    }
}
