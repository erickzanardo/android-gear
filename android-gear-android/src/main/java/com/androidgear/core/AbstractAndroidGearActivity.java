package com.androidgear.core;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.androidgear.core.asset.AndroidAssetLoader;
import com.androidgear.core.asset.AssetManager;
import com.androidgear.core.dialog.AndroidDialogFactory;
import com.androidgear.core.dialog.DialogFactory;

public abstract class AbstractAndroidGearActivity extends Activity {

    private Game game;
    private int lastOrientation = -1;
    private boolean isPointerDown;
    private AndroidGameLoop androidGameLoop;

    public abstract Game getGame();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.game = getGame();

        GearCommands.prepare(AndroidGearCommands.class);
        ((AndroidGearCommands)GearCommands.instance()).setActivity(this);

        DialogFactory.prepare(AndroidDialogFactory.class);
        ((AndroidDialogFactory)DialogFactory.instance()).setActivity(this);

        // Asset Manager
        AndroidAssetLoader loader = new AndroidAssetLoader();
        AssetManager.setLoader(loader);

        super.onCreate(savedInstanceState);

        lastOrientation = getResources().getConfiguration().orientation;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        androidGameLoop = new AndroidGameLoop(this, game);
        setContentView(androidGameLoop);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if (lastOrientation != newConfig.orientation) {
            
            if (!game.screenChanged(androidGameLoop.getWidth(), androidGameLoop.getHeight())) {
                setRequestedOrientation(lastOrientation);
            } else {
                lastOrientation = newConfig.orientation;
            }
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int sleep = 50;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
            isPointerDown = true;
            game.onStartPointerEvent(event.getX(), event.getY());
            break;
        case MotionEvent.ACTION_UP:
            isPointerDown = false;
            game.onEndPointerEvent(event.getX(), event.getY());
            break;
        case MotionEvent.ACTION_MOVE:
            if (isPointerDown) {
                game.onPointerDragEvent(event.getX(), event.getY());
                sleep = 0;
            }
            break;
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return game.onBackPress();
        }

        return super.onKeyDown(keyCode, event);
    }
}
