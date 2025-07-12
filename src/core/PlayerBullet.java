package src.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PlayerBullet {
    private int x, y;
    private final int speed = 15;
    private final Image bulletImage;
    public int width, height;

    public PlayerBullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        Image tempImage;
        try {
            BufferedImage originalImage = ImageIO.read(new File("assets/images/bullet/player_bullet.png"));
            tempImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error loading player spaceship image");
            e.printStackTrace();
            tempImage = null;
        }
        bulletImage = tempImage;
    }

    public void move() {
        y -= speed;
    }

    public boolean isOffScreen() {
        return y + bulletImage.getHeight(null) < 0;
    }

    public Image getImage() {
        return bulletImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, bulletImage.getWidth(null), bulletImage.getHeight(null));
    }
    
}
