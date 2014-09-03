package ${package}.core;

import com.androidgear.core.Game;
import com.androidgear.core.asset.AssetManager;
import com.androidgear.core.asset.AssetNotFoundException;
import com.androidgear.core.graphics.Graphics;
import com.androidgear.core.graphics.Image;

public class ${GameClass} implements Game {

    private Image androidBuddy;

    @Override
    public void init(Graphics graphics) {
        try {
            androidBuddy = AssetManager
                    .loadImage("/com/androidgear/core/samples/asset/androidbuddy.png");
        } catch (AssetNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update(long delta) {
        
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(androidBuddy, 200, 50);
    }

    @Override
    public void onStartPointerEvent(float x, float y) {
    }

    @Override
    public void onEndPointerEvent(float x, float y) {
    }

    @Override
    public void onPointerDragEvent(float x, float y) {
    }

    @Override
    public int getFrameRate() {
        return 25;
    }

    @Override
    public boolean screenChanged(int newWidht, int newHeight) {
        return true;
    }

    @Override
    public boolean onBackPress() {
        return true;
    }
}
