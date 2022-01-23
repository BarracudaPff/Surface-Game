package com.samsung.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class AnimatedSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public AnimatedSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    float curX = 100;
    float curY = 100;

    float toX = curX;
    float toY = curY;

    float e = 1;

    void drawRect(float x, float y) {
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
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }

        toX = event.getX();
        toY = event.getY();

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!(Math.abs(curX - toX) < e) || !(Math.abs(curY - toY) < e)) {
                        float step = 0.05f;
                        System.out.println(curX+":"+(toX / step));
                        curX = curX + toX * step;
                        curY = curY + toY * step;
                    }

                    drawRect(curX, curY);

                    try {
                        Thread.sleep(50);
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
