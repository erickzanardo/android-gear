package com.androidgear.samples;

import com.androidgear.core.AbstractAndroidGearActivity;
import com.androidgear.core.Game;
import com.androidgear.samples.core.SampleGame;

public class SampleGameActivity extends AbstractAndroidGearActivity {

    @Override
    public Game getGame() {
        return new SampleGame();
    }

}