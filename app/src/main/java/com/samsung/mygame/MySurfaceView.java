package com.samsung.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    boolean flag = true;
    float x1;
    float y1;
    float x2;
    float y2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        System.out.println(event);

        if (flag) {
            x1 = event.getX();
            y1 = event.getY();
        } else {
            x2 = event.getX();
            y2 = event.getY();
            draw();
        }

        flag = !flag;

        return true;
    }

    void draw() {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 255, 0));


        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.rgb(0, 0, 0));
        canvas.drawRect(x1, y1, x2, y2, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }

//    void draw(int x) {
//        Paint paint = new Paint();
//        paint.setColor(Color.rgb(255, 0, 0));
//
//
//        Canvas canvas = getHolder().lockCanvas();
//
//        canvas.drawRect(x, 1, x + 100, 100, paint);
//
//        getHolder().unlockCanvasAndPost(canvas);
//    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
