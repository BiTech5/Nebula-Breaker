package src.core;

import src.util.SoundManager;

public class PlayMusic {
    public static boolean musicOn = true; //to remember current music state
    public void play(boolean toggle) {
        if (toggle) {
            SoundManager.playBackgroundMusic("assets/sounds/music/background_music.wav");
            musicOn = true;
        }
    }
    public void stop(){
        SoundManager.stopBackgroundMusic();
        musicOn = false;
    }
}
