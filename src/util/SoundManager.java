package src.util;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {

    private static Clip clip; 

    public static void playBackgroundMusic(String path) {
        try {
            File musicPath = new File(path);
            if (musicPath.exists()) {
                
                if (clip != null && clip.isRunning()) {
                    return;
                }

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("File not found: " + path);
            }

        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }

    public static void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
