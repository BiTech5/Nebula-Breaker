package src.util;

import javax.sound.sampled.*;
import java.io.File;


public class SoundManager {
    
    public static void playBackgroundMusic(String path){
        try {
            File musicPath=new File(path);
            if(musicPath.exists()){
                AudioInputStream audioInput=AudioSystem.getAudioInputStream(musicPath);
                Clip clip=AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                System.out.println("File not find");
            }
            
        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }
} 