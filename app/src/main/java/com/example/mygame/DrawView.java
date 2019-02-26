package com.example.mygame;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    int a = 0;
    final SurfaceHolder surfaceHolder = getHolder();
    private volatile boolean running = true;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    Character player = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.character)
            ,100,150,50,150,5,6);

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new  Thread() {
            public void run(){
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        try {
                            canvas.drawColor(Color.BLUE);
                            if(a>=1)player.moveto();
                            player.update(System.currentTimeMillis());
                            player.draw(canvas);


                        } finally {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            player.setVec(event);
            a++;
        return super.onTouchEvent(event);
    }
}