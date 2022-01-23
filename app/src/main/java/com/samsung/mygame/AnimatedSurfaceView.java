package com.samsung.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class AnimatedSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public AnimatedSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    void drawRect(int x, int y) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 255));

        Canvas canvas = getHolder().lockCanvas();
        // clear screen
        canvas.drawColor(Color.rgb(0, 0, 0));
        // draw rect
        canvas.drawRect(x, y, x + 100, y + 100, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 500; i++) {
                        drawRect(i, 3 * i);
                    }


                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
