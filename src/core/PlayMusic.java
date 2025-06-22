package src.core;

import src.util.SoundManager;

public class PlayMusic {
    public void play(boolean toggle) {
        if (toggle) {
            SoundManager.playBackgroundMusic("assets/sounds/music/background_music.wav");
        }
    }
    public void stop(){
        SoundManager.stopBackgroundMusic();
    }
}
