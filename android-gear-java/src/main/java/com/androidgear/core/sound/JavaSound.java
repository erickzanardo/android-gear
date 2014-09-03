package com.androidgear.core.sound;

import java.applet.AudioClip;

public class JavaSound implements Sound{
    AudioClip audioClip;
	
    public JavaSound(AudioClip audioClip) {
        this.audioClip = audioClip;
    }

    @Override
    public void play() {
        this.audioClip.play();
    }

    @Override
    public void loop() {
        this.audioClip.loop();
    }

    @Override
    public void stop() {
        this.audioClip.stop();
    }

    @Override
    public void destroy() {
        this.audioClip = null;
    }
}
