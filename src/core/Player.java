package src.core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Player {
    public int width, height;
    public Image playerImage;
    public int x, y;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            BufferedImage originalImage = ImageIO.read(new File("assets/images/spaceship/player_spaceship.png"));
            playerImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error loading player spaceship image");
            e.printStackTrace();
            playerImage = null;
        }
    }

    public Image getImage() {
        return playerImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int dx, int dy, int panelWidth, int panelHeight) {
        x += dx;
        y += dy;
        int topLimit =(panelHeight/2 )-30 ;
        if (x < 0)
            x = 0;
        if (x > panelWidth - playerImage.getWidth(null))
            x = panelWidth - playerImage.getWidth(null);
        if (y < 0)
            y = 0;
        if(y<topLimit)
            y = topLimit;
        if (y > panelHeight - playerImage.getHeight(null))
            y = panelHeight - playerImage.getHeight(null);
    }
    public Rectangle getBounds() {
         int padding = 10;
        return new Rectangle(x + padding,
        y + padding, width - 3 * padding,
        height - 2 * padding); 
    }
    
}
