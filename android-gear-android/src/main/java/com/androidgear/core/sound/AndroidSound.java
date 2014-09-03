package com.androidgear.core.sound;

import android.media.MediaPlayer;

public class AndroidSound implements Sound{
    private MediaPlayer mediaPlayer;
    
    public AndroidSound(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
    
    @Override
    public void play() {
        mediaPlayer.start();
    }

    @Override
    public void loop() {
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void destroy() {
        mediaPlayer.release();
    }
}
