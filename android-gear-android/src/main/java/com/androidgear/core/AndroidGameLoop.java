package com.androidgear.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.androidgear.core.graphics.AndroidGraphics;

public class AndroidGameLoop extends SurfaceView implements
        SurfaceHolder.Callback {

    private Game game;
    private AndroidGraphics graphics;
    private AndroidGameLoopThread loopThread;
    private Bitmap bitmap;
    private Canvas canvas;
    public int expectedMilis;
    private int width;
    private int height;
    private Matrix identityMatrix;

    public AndroidGameLoop(Context context, Game game) {
        super(context);
        getHolder().addCallback(this);
        this.game = game;
        loopThread = new AndroidGameLoopThread(getHolder(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int arg1, int arg2,
            int arg3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        width = getWidth();
        height = getHeight();
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        identityMatrix = new Matrix();

        loopThread.setRunning(true);
        loopThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Game Render
        game.render(graphics);

        canvas.drawBitmap(bitmap, identityMatrix, null);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // simply copied from sample application LunarLander:
        // we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
        boolean retry = true;
        loopThread.setRunning(false);
        while (retry) {
            try {
                loopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class AndroidGameLoopThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private AndroidGameLoop loop;
        private boolean run = false;
        private long lastUpdate;

        public AndroidGameLoopThread(SurfaceHolder surfaceHolder,
                AndroidGameLoop loop) {
            this.surfaceHolder = surfaceHolder;
            this.loop = loop;
        }

        public void setRunning(boolean run) {
            this.run = run;
        }

        @Override
        public void run() {
            Canvas c;

            // Create Graphics
            graphics = new AndroidGraphics();

            graphics.setCanvas(canvas);
            game.init(graphics);

            c = null;
            expectedMilis = 1000 / game.getFrameRate();

            lastUpdate = System.currentTimeMillis();

            long lastRender = 0;

            // Game loop
            do {
                long update = System.currentTimeMillis();
                long delta = update - lastUpdate;

                if (lastUpdate - lastRender >= expectedMilis) {
                    // Game Render

                    try {
                        c = surfaceHolder.lockCanvas();
                        synchronized (surfaceHolder) {
                            loop.onDraw(c);
                        }
                    } finally {
                        // do this in a finally so that if an exception is
                        // thrown
                        // during the above, we don't leave the Surface in an
                        // inconsistent state
                        if (c != null) {
                            surfaceHolder.unlockCanvasAndPost(c);
                        }
                    }

                    lastRender = System.currentTimeMillis();
                } else {
                    // Game update
                    game.update(delta);
                    Thread.yield();
                }
                lastUpdate += delta;
            } while (run);

            // Game destroy
            game.destroy();
        }
    }
}
