package src.core;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Enemy {
    private int x, y;
    private int direction; 
    private final int speed =1;
    private final Image image;
    private final int width = 50, height = 50;

    public Enemy(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        this.direction = Math.random() < 0.5 ? -1 : 1; 

        Image temp;
        try {
            temp = ImageIO.read(new File("assets/images/spaceship/enemy_spaceship.png"))
                    .getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            e.printStackTrace();
            temp = null;
        }

        this.image = temp;
    }

    public void move(int panelWidth) {
        x += speed * direction;

        if (x <= 0 || x >= panelWidth - width) {
            direction *= -1;
        }
    }

    public Image getImage() { return image; }
    public int getX() { return x; }
    public int getY() { return y; }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50); 
    }
    
}
