package src.view;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import src.core.Player;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePage extends JPanel implements KeyListener{
    private Image backgroundImage;
    private Player player;
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
        }    
        player.move(dx, dy, getWidth(), getHeight());

        repaint(); 
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
