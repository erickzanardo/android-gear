package com.androidgear.samples;

import com.androidgear.core.JavaGameLoop;
import com.androidgear.samples.core.SampleGame;

public class JavaSampleGame {
    public static void main(String[] args) {
        JavaGameLoop gameLoop = new JavaGameLoop(new SampleGame());
        gameLoop.run();
    }
}
