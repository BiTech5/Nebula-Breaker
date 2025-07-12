package src.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class EnemyBullet {
    private int x, y;
    private final int speed = 7;
    private final Image image;

    public EnemyBullet(int x, int y) {
        this.x = x;
        this.y = y;

        Image tempImage;
        try {
            BufferedImage original = ImageIO.read(new File("assets/images/bullet/enemy_bullet.png"));
            tempImage = original.getScaledInstance(20, 25, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Enemy bullet load error");
            e.printStackTrace();
            tempImage = null;
        }

        this.image = tempImage;
    }

    public void move() {
        y += speed;
    }

    public boolean isOffScreen(int panelHeight) {
        return y > panelHeight;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImage() { return image; }
}
