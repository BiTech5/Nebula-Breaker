package src.util;

import src.core.*;
import src.view.GameOver;
import src.util.HighScoreManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import src.view.GamePage;
import src.view.SettingsPage;
public class GameEngine {
    private final GamePage gamePage;
    private int enemyBulletDelay;
    private int enemySpawnDelay;
    private double fireBulletChance;

    public GameEngine(GamePage gamePage) {
        this.gamePage = gamePage;

        String difficultyLevel = SettingsPage.getSelectedLevel();
        if(difficultyLevel.equalsIgnoreCase("EASY")){
            enemyBulletDelay = 2000;
            enemySpawnDelay = 2000;
            fireBulletChance = 0.4;
        }else if(difficultyLevel.equalsIgnoreCase("MEDIUM")){
            enemyBulletDelay = 1500;
            enemySpawnDelay = 1500;
            fireBulletChance = 0.6;
        }else{
            enemyBulletDelay = 1000;
            enemySpawnDelay = 1000;
            fireBulletChance = 0.8;
        }
    }
    

    

    public void update() {
        gamePage.bullets.removeIf(PlayerBullet::isOffScreen);
        for (PlayerBullet bullet : gamePage.bullets) bullet.move();
        for (Enemy en : gamePage.enemies) en.move(gamePage.getWidth());

        Rectangle playerRect = gamePage.player.getBounds();
        List<EnemyBullet> enemyBulletsToRemove = new ArrayList<>();

        gamePage.enemyBullets.removeIf(b -> b.isOffScreen(gamePage.getHeight()));
        for (EnemyBullet eb : gamePage.enemyBullets) eb.move();

        for (EnemyBullet eb : gamePage.enemyBullets) {
            if (playerRect.intersects(eb.getBounds())) {
                enemyBulletsToRemove.add(eb);
                gamePage.lives--;
                if (gamePage.lives <= 1) {
                    gamePage.gameTimer.stop();
                    
                    // Save the score
                    String difficultyLevel = SettingsPage.getSelectedLevel();
                    boolean isNewHighScore = HighScoreManager.updateHighScore(difficultyLevel, gamePage.score);
                    
                    GameOver gameOverPanel = new GameOver(gamePage.score,
                        e1 -> gamePage.restartGame(),
                        e2 -> gamePage.goHome());
                    gameOverPanel.setBounds(0, 0, gamePage.getWidth(), gamePage.getHeight());
                    gamePage.add(gameOverPanel);
                    gamePage.revalidate();
                    gamePage.repaint();
                }
            }
        }
        gamePage.enemyBullets.removeAll(enemyBulletsToRemove);

        if (System.currentTimeMillis() - gamePage.lastEnemyFireTime > enemyBulletDelay) {
            for (Enemy en : gamePage.enemies) {
                if(Math.random()<fireBulletChance){
                    gamePage.enemyBullets.add(new EnemyBullet(en.getX() + 20, en.getY() + 50));
                }
            }
            gamePage.lastEnemyFireTime = System.currentTimeMillis();
        }

        List<Enemy> enemiesToRemove = new ArrayList<>();
        List<PlayerBullet> bulletsToRemove = new ArrayList<>();

        for (PlayerBullet bullet : gamePage.bullets) {
            Rectangle bulletRect = bullet.getBounds();
            for (Enemy enemy : gamePage.enemies) {
                Rectangle enemyRect = enemy.getBounds();
                if (bulletRect.intersects(enemyRect)) {
                    enemiesToRemove.add(enemy);
                    bulletsToRemove.add(bullet);
                    gamePage.score += 10;
                    gamePage.scoreLabel.setText("Score: " + gamePage.score);
                    break;
                }
            }
        }

        gamePage.bullets.removeAll(bulletsToRemove);
        gamePage.enemies.removeAll(enemiesToRemove);

        if (gamePage.enemies.size() < gamePage.maxEnemies &&
            System.currentTimeMillis() - gamePage.lastEnemySpawnTime > enemySpawnDelay) {
            int randX = (int) (Math.random() * (gamePage.getWidth() - 50));
            int randY = 20 + (int) (Math.random() * 80);
            gamePage.enemies.add(new Enemy(randX, randY));
            gamePage.lastEnemySpawnTime = System.currentTimeMillis();
        }

        gamePage.repaint();
    }
}
