package com.example.mygame;


import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class DrawView extends SurfaceView implements SurfaceHolder.Callback {

     public static int s;
     private DrawThread drawThread;

     private StatClass stat;
     Character player;
     Map map;



     DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
        stat = new StatClass(context);
        map = stat.map;
        player = stat.player;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        drawThread = new DrawThread(getContext(),getHolder());
       drawThread.start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
       drawThread.requestStop();

        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();

                retry = false;
            } catch (InterruptedException e) {

            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }



}